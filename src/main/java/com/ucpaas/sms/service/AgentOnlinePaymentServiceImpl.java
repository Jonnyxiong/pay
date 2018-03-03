package com.ucpaas.sms.service;


import com.ucpaas.sms.dto.ResultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title
 * @description 云用户在线支付流水
 * @author huangwenjie
 * @date 2017-02-23
 */
@Service
public class AgentOnlinePaymentServiceImpl implements AgentOnlinePaymentService {

	private static Logger logger = LoggerFactory.getLogger(AgentOnlinePaymentServiceImpl.class);
//	@Autowired
//	private JsmsSaleCreditService jsmsSaleCreditService;

	/*@Autowired
	private AgentOnlinePaymentMapper agentOnlinePaymentMapper;*/



	@Override
	@Transactional
	public ResultVO queryList() {
//		agentOnlinePaymentMapper.queryList();
		return ResultVO.successDefault("asdf");
	}



}
