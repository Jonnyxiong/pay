package com.ucpaas.sms.mapper;

import com.ucpaas.sms.dto.Page;
import entity.agent.CloudOnlinePayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


public interface AgentOnlinePaymentMapper {


	/*@Select("select * from t_sms_cloud_online_payment where user_id=#{name}")
	public List<CloudOnlinePayment> getByGradeNm(String name);*/
	//List<CloudOnlinePayment> queryList(Page<CloudOnlinePayment> page);
	List<CloudOnlinePayment> queryList();
	


}