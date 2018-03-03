package com.ucpaas.sms.service;


import com.jsmsframework.finance.entity.JsmsOnlinePayment;
import com.ucpaas.sms.dto.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface PayNotifyService {


    int updateState(String paymentId,Integer paymentState,String remart);

    int updateState(String paymentId, Integer paymentState, String remark, String bankTransId);

    JsmsOnlinePayment getOnlinePayment(String paymentId);

    /**
     * 充值
     * @param params
     * @return
     */
    public ResultVO balanceSave(Map<String, String> params,HttpServletRequest request);
    
}
