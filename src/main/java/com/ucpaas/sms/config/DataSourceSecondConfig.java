package com.ucpaas.sms.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = DataSourceSecondConfig.PACKAGE, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class DataSourceSecondConfig {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.jsmsframework.**.mapper";
    static final String MAPPER_LOCATION = "classpath*:/mapper/**/*Mapper.xml";
    //static final String MAPPER_LOCATION = "classpath:com/ucpaas/sms/mapper/*.xml";


    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource masterDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "message")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("secondDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceSecondConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
