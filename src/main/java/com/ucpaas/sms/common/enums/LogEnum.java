package com.ucpaas.sms.common.enums;

public enum LogEnum {
	首页("1"),
	短信管理("2"),
	客户管理("3"),
	员工管理("4"),
	系统管理("5");

    private final String value;

    LogEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
