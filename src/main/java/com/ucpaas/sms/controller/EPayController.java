package com.ucpaas.sms.controller;


import com.jsmsframework.common.entity.JsmsParam;
import com.jsmsframework.common.enums.PaymentType;
import com.jsmsframework.common.service.JsmsParamService;
import com.jsmsframework.finance.entity.JsmsOnlinePayment;
import com.jsmsframework.finance.service.JsmsAgentAccountService;
import com.jsmsframework.user.entity.JsmsAgentInfo;
import com.jsmsframework.user.mapper.JsmsAgentInfoMapper;
import com.ucpaas.sms.common.annotation.IgnoreAuth;
import com.ucpaas.sms.common.exception.BusinessException;
import com.ucpaas.sms.dto.EpayNotify;
import com.ucpaas.sms.dto.ResultVO;
import com.ucpaas.sms.enum4sms.PaymentState;
import com.ucpaas.sms.service.PayNotifyService;
import com.ucpaas.sms.util.ConfigUtils;
import com.ucpaas.sms.util.JsonUtils;
import com.ucpaas.sms.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/epay")
public class EPayController {

	private static Logger logger = LoggerFactory.getLogger(EPayController.class);


	@Autowired
	private PayNotifyService payNotifyService;
//	@Autowired
//	private JsmsAgentAccountService jsmsAgentAccountService;

	@Autowired
	private Environment env;
	@Autowired
	private JsmsAgentInfoMapper agentInfoMapper;
	@Autowired
	private JsmsParamService jsmsParamService;



	/**
	 * 给epay的主动回调的地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/notify")
	@ResponseBody
	@IgnoreAuth
	public String notify(@RequestParam Map<String, String> params,HttpServletRequest request) {

		logger.info("支付回调："+request.getParameter("orderId"));
		//jsmsOnlinePaymentLogService.getById(1);

		String repResult = "fail";
		String merId = request.getParameter("merId");
		String orderId = request.getParameter("orderId");
		String payAmount = request.getParameter("payAmount");
		String result = request.getParameter("result");
		String merData = request.getParameter("merData");
		String bankTransId = request.getParameter("bankTransId");
		String cardinfo = request.getParameter("cardinfo");
		String sign = request.getParameter("sign");
		logger.info("请求的支付结果为空");
		if (!env.getProperty("epay_merId").equals(merId)) {
			logger.debug("商户id无法识别,请求的merId={},系统配置merId={}", merId, env.getProperty("epay_merId"));
			return repResult;
		}
		if (StringUtil.isEmpty(orderId)) {
			logger.debug("请求的订单号为空");
			return repResult;
		}
		if (StringUtil.isEmpty(payAmount)) {
			logger.debug("请求的实际支付金额为空");
			return repResult;
		}
		if (StringUtil.isEmpty(result)) {
			logger.debug("请求的支付结果为空");
			return repResult;
		}

		if (StringUtil.isEmpty(sign)) {
			sign = "";
		}
		if (StringUtil.isEmpty(merData)) {
			merData = "";
		}
		if (StringUtil.isEmpty(bankTransId)) {
			bankTransId = "";
		}
		if (StringUtil.isEmpty(cardinfo)) {
			cardinfo = "";
		}

		String requestParams = merId + orderId + payAmount + result + merData + bankTransId + cardinfo+env.getProperty("epay_key");

		String encode;
		try {
			encode = StringUtil.getMD5(requestParams);
		} catch (NoSuchAlgorithmException e) {
			logger.debug("MD5编码失败", e);
			return repResult;
		}
		if (!sign.equals(encode)) {
			logger.debug("请求不合法");
			logger.info("回调参数："+requestParams);
			logger.info("回调参数加密后："+encode);
			logger.info("回调加密串："+sign);
			return repResult;
		}


		ResultVO resultVO = null;

		try {

			JsmsOnlinePayment jsmsOnlinePayment=payNotifyService.getOnlinePayment(params.get("orderId"));
			if(jsmsOnlinePayment==null){
				logger.debug("订单号不存在");
				return repResult;
			}
			if(jsmsOnlinePayment.getPaymentState()!=0&&jsmsOnlinePayment.getPaymentState()!=1){
				logger.debug("订单号不是未支付或者支付已提交状态，不能充值");
				return repResult;
			}
			payNotifyService.updateState(params.get("orderId"), PaymentState.paid.getValue(),"",bankTransId);
			if(jsmsOnlinePayment.getSubmitTime()==null){
				jsmsOnlinePayment.setSubmitTime(new Date());
			}
			logger.info("提交时间："+jsmsOnlinePayment.getSubmitTime().getTime()+"当前时间："+new Date().getTime());
			if(new Date().getTime()- (jsmsOnlinePayment.getSubmitDeadline().getTime())<getOverTime()){
				logger.info("进入账户充值充值");
				params.put("agent_id",jsmsOnlinePayment.getAgentId().toString());
				params.put("operateAmount",jsmsOnlinePayment.getPaymentAmount().toString());

				String remark=jsmsOnlinePayment.getPaymentMode()==1?("在线支付-微信-"+jsmsOnlinePayment.getPaymentAmount().toString()):("在线支付-支付宝-"+jsmsOnlinePayment.getPaymentAmount().toString());
				logger.info("支付类型："+jsmsOnlinePayment.getPaymentState());
				logger.info("支付备注："+remark);

				params.put("remark",remark);
				params.put("clientId","");
				params.put("operateType","");
				params.put("operateType", PaymentType.在线充值.getDesc());
				params.put("paymentId", jsmsOnlinePayment.getPaymentId());
				logger.info("充值参数{}",params);
				resultVO=payNotifyService.balanceSave(params,request);
			}else {
				payNotifyService.updateState(params.get("orderId"), 6,"第三方支付状态超时，金额已到账，短信平台未充值，请联系人工充值",bankTransId);
			}
		} catch (BusinessException b) {
			logger.debug("回调失败，原因============={}"+b.getMessage());
			payNotifyService.updateState(params.get("orderId"), 5,"支付金额已到账，短信平台充值失败，请联系人工充值",bankTransId);

			resultVO = ResultVO.failure();
		}catch (Exception e) {
			logger.debug("回调失败，原因============={}"+e.getMessage());
			resultVO = ResultVO.failure();
		}

		if (resultVO.isSuccess()) {
			logger.info("主动回调成功, resultVO={}", JsonUtils.toJson(resultVO));

			repResult = "success";
		} else {
			logger.info("epay notify接口调用失败, resultVO={}", JsonUtils.toJson(resultVO));
			repResult = "fail";
		}

		return repResult;
	}

	public Long getOverTime() {
		List<JsmsParam> jsmsParams=jsmsParamService.getByParamKey("ONLINE_PAYMENT_OVERTIME");
		String cancelTime="";
		if(CollectionUtils.isEmpty(jsmsParams)){
			cancelTime="1440";
		}else {

			cancelTime=jsmsParams.get(0).getParamValue().split("\\|")[1];
		}
		return  Long.parseLong(cancelTime) * 60 * 1000;

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
//
	}
}
