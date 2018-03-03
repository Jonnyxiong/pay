package com.ucpaas.sms.service;


import com.jsmsframework.common.dto.JsmsPage;
import com.jsmsframework.finance.entity.JsmsAgentAccount;
import com.jsmsframework.finance.entity.JsmsOnlinePayment;

import com.jsmsframework.finance.entity.JsmsOnlinePaymentLog;
import com.jsmsframework.finance.enums.PaymentType;

import com.jsmsframework.finance.mapper.JsmsOnlinePaymentLogMapper;
import com.jsmsframework.finance.mapper.JsmsOnlinePaymentMapper;
import com.jsmsframework.finance.service.JsmsAgentAccountService;

import com.jsmsframework.finance.service.JsmsOnlinePaymentService;
import com.jsmsframework.sale.credit.constant.SysConstant;
import com.jsmsframework.sale.credit.service.JsmsSaleCreditService;
import com.jsmsframework.user.entity.JsmsAgentInfo;
import com.jsmsframework.user.entity.JsmsUser;
import com.jsmsframework.user.service.JsmsAgentInfoService;
import com.jsmsframework.user.service.JsmsUserService;
import com.ucpaas.sms.constant.RechargeSMSConstant;
import com.ucpaas.sms.dto.ResultVO;
import com.ucpaas.sms.util.HttpUtils;
import com.ucpaas.sms.util.SendSMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @title
 * @description 云用户在线支付流水
 * @author huangwenjie
 * @date 2017-02-23
 */
@Service
public class PayNotifyServiceImpl implements PayNotifyService {

	private static Logger logger = LoggerFactory.getLogger(PayNotifyServiceImpl.class);
	private final String SUCCESS="success";


	@Autowired
	private JsmsSaleCreditService jsmsSaleCreditService;

	@Autowired
	private JsmsAgentInfoService jsmsAgentInfoService;
	@Autowired
	private JsmsAgentAccountService jsmsAgentAccountService;
	@Autowired
	private JsmsUserService jsmsUserService;


	@Autowired
	private JsmsOnlinePaymentService jsmsOnlinePaymentService;



	@Autowired
	private JsmsOnlinePaymentMapper onlinePaymentMapper;

    @Autowired
    private JsmsOnlinePaymentLogMapper jsmsOnlinePaymentLogMapper;


	@Override
	public int updateState(String paymentId,Integer paymentState,String remark) {

		JsmsPage page=new JsmsPage();
		page.getParams().put("paymentId",paymentId);


		JsmsOnlinePayment jsmsOnlinePayment=new JsmsOnlinePayment();
		JsmsPage jsmsPage=jsmsOnlinePaymentService.queryList(page);
		List<JsmsOnlinePayment> jsmsOnlinePayments=jsmsPage.getData();
		if (CollectionUtils.isEmpty(jsmsOnlinePayments)){
			return 0;
		}
		//jsmsOnlinePaymentLog
		jsmsOnlinePayment= jsmsOnlinePayments.get(0);
		jsmsOnlinePayment.setPaymentState(paymentState);
        jsmsOnlinePayment.setRemark(remark);
		jsmsOnlinePayment.setAdminId(0L);
        onlinePaymentLog(paymentId,paymentState,remark,0L);
        logger.info(jsmsOnlinePayment.getAdminId().toString());
        logger.info("保存数据为：{}",jsmsOnlinePayment);
		return this.jsmsOnlinePaymentService.updateSelective(jsmsOnlinePayment);

	}
	@Override
	public int updateState(String paymentId,Integer paymentState,String remark,String bankTransId) {

		JsmsPage page=new JsmsPage();
		page.getParams().put("paymentId",paymentId);

		JsmsOnlinePayment jsmsOnlinePayment=new JsmsOnlinePayment();
		JsmsPage jsmsPage=jsmsOnlinePaymentService.queryList(page);
		List<JsmsOnlinePayment> jsmsOnlinePayments=jsmsPage.getData();
		if (CollectionUtils.isEmpty(jsmsOnlinePayments)){
			return 0;
		}
		//jsmsOnlinePaymentLog
		jsmsOnlinePayment= jsmsOnlinePayments.get(0);
		jsmsOnlinePayment.setPaymentState(paymentState);
		jsmsOnlinePayment.setFlowId(bankTransId);
		jsmsOnlinePayment.setPayTime(new Date());
        jsmsOnlinePayment.setRemark(remark);
		jsmsOnlinePayment.setAdminId(0L);
        onlinePaymentLog(paymentId,paymentState,remark,0L);
        logger.info(jsmsOnlinePayment.getAdminId().toString());
        logger.info("保存数据为：{}",jsmsOnlinePayment);
		return this.jsmsOnlinePaymentService.updateSelective(jsmsOnlinePayment);


	}

	@Override
	public JsmsOnlinePayment getOnlinePayment(String paymentId) {

		JsmsPage page=new JsmsPage();
		page.getParams().put("paymentId",paymentId);

		JsmsOnlinePayment jsmsOnlinePayment=new JsmsOnlinePayment();
		JsmsPage jsmsPage=jsmsOnlinePaymentService.queryList(page);
		List<JsmsOnlinePayment> jsmsOnlinePayments=jsmsPage.getData();
		if (CollectionUtils.isEmpty(jsmsOnlinePayments)){
			return null;
		}
		return jsmsOnlinePayments.get(0);


	}

    private int onlinePaymentLog(String paymentId,Integer paymentState,String remark,Long adminId) {

        JsmsOnlinePaymentLog jsmsOnlinePaymentLog=new JsmsOnlinePaymentLog();
        jsmsOnlinePaymentLog.setPaymentId(paymentId);
        jsmsOnlinePaymentLog.setPaymentState(paymentState);
        jsmsOnlinePaymentLog.setRemark(remark);
        jsmsOnlinePaymentLog.setAdminId(adminId);
        return jsmsOnlinePaymentLogMapper.insert(jsmsOnlinePaymentLog);


    }

	@Override
    @Transactional
	public ResultVO balanceSave(Map<String, String> params, HttpServletRequest request) {

		ResultVO result = ResultVO.failure();
		// 需要用户Id

		params.put("userId", "0");
		params.put("pageUrl", request.getRequestURI());
		params.put("ip", HttpUtils.getIpAddress(request));

		Map data = null;
		try {
			//data = agentFinanceService.balanceAction(params);
			//	data=this.agentBalanceForOpreation(params);

			data=jsmsSaleCreditService.agentBalanceForOpreation(params);
			logger.info("充值成功！");
			//充值成功，发送短信至归属销售
			if(Objects.equals(data.get("result"),SUCCESS) && ((Objects.equals(com.jsmsframework.common.enums.PaymentType.在线充值.getDesc(),params.get("operateType")))||(Objects.equals(PaymentType.充值.getDesc(),params.get("operateType"))))){
				logger.info("进入充值成功，发送短信！");
				List<String> params1=new ArrayList<>();
				JsmsAgentInfo agent=jsmsAgentInfoService.getByAgentId(Integer.valueOf(params.get("agent_id")));
				JsmsAgentAccount agentNew = jsmsAgentAccountService.getByAgentId(agent.getAgentId());
				JsmsUser sale=jsmsUserService.getById2(agent.getBelongSale());
				if(sale==null){
					data.put("result", SysConstant.FAIL);
					data.put("msg", "归属销售不存在，无法发送充值短信！");
					logger.error("归属销售不存在，无法发送充值短信");
					result.setFail("fail".equals(data.get("result").toString()));
					result.setMsg(data.get("msg").toString());
					return result;
				}
				params1.add(0,sale.getRealname());
				params1.add(1,agent.getAgentId()+"-"+agent.getAgentName());
				params1.add(2,String.valueOf(agentNew.getBalance().add(agentNew.getCurrentCredit())));
				String mobile=sale.getMobile();
				String result1= SendSMSUtil.sendRechargeSMS(RechargeSMSConstant.client_sms_recharge_template,mobile,params1);
				logger.debug("充值成功调用发送短信接口,返回内容={}",result1);
			}

		} catch (Exception e) {
			data = new HashMap<String, Object>();
			data.put("result", SysConstant.FAIL);
			data.put("msg", "余额操作失败");
			logger.debug("余额" + params.get("operateType") + "操作失败---------> 运行错误信息", e.getMessage());
		}

		result.setSuccess("success".equals(data.get("result").toString()));
		result.setMsg(data.get("msg").toString());
		return result;

	}



}
