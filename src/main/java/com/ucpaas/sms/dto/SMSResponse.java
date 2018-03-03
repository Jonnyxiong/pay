package com.ucpaas.sms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class SMSResponse {

	private static final long serialVersionUID = 3947127755167515787L;

	private int total_fee;
	
	private List<Map<String, String>> data;
	
	public SMSResponse(){
		super();
	}
	
	public SMSResponse(int total_fee, List<Map<String, String>> data){
		super();
		this.total_fee = total_fee;
		this.data = data;
	}
	

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
	
	
	

}
