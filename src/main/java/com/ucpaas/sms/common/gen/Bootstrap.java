package com.ucpaas.sms.common.gen;

import com.ucpaas.sms.common.gen.db.ConnHelper;
import com.ucpaas.sms.common.gen.dto.PackageDTO;
import com.ucpaas.sms.common.gen.parse.ParseConfigXml;
import com.ucpaas.sms.common.gen.parse.ParseTable;
import com.ucpaas.sms.common.gen.parse.ParseTableMysqlImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * 启动程序
 * 
 * @author huangwenjie
 *
 */
public class Bootstrap {/*

	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

	public static void main(String[] args) {

		logger.debug("1.连接数据库");
		Connection conn = ConnHelper.getConnection();
		if (conn == null) {
			logger.error("获取数据库连接失败，请检查db.properties的配置");
			return;
		}

		ParseTable parseTable = new ParseTableMysqlImpl();

		// 将XML文件转换为Java对象
		logger.debug("2.读取Java对象配置config.xml");
		PackageDTO packageDTO = ParseConfigXml.parseConfigXml();
		if (packageDTO == null) {
			logger.debug("读取配置文件config.xml失败");
			return;
		}

		// 将数据库的表信息添加到Java对象中
		try {
			packageDTO = parseTable.generatePojoByTable(conn, packageDTO);
		} catch (Exception e) {
			logger.debug("解析数据库信息失败", e);
			return;
		}

		Generate generate = new SpringMVCGenerate();

		// 模板路径
		String template = Bootstrap.class.getClassLoader().getResource("").getFile();
		generate.generate(packageDTO, template);

		System.out.println("Code generation,the generated files directory:" + packageDTO.getDestDir());
	}*/
}
