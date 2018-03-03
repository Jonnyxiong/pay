package com.ucpaas.sms.common.gen.parse;

import com.ucpaas.sms.common.gen.dto.PackageDTO;

import java.sql.Connection;

/**
 * 解析数据库中的表
 * 
 * @author huangwenjie
 * @date 2017-01-24
 */
public interface ParseTable {

	/**
	 * @title 获取表的信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	public PackageDTO generatePojoByTable(Connection conn, PackageDTO packageDTO) throws Exception;

}
