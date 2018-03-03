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
@MapperScan(basePackages = DataSourceFirstConfig.PACKAGE, sqlSessionFactoryRef = "firstSqlSessionFactory")
public class DataSourceFirstConfig {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.ucpaas.sms.mapper";
    static final String MAPPER_LOCATION = "classpath*:com/ucpaas/sms/mapper/*.xml";
//    static final String PACKAGE = "com.ucpaas.sms.mapper";
//    static final String MAPPER_LOCATION = "classpath:com/ucpaas/sms/mapper/*.xml";
    //static final String MAPPER_LOCATION = "classpath:com/ucpaas/sms/mapper/*.xml";



    @Bean(name = "firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "firstTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "firstSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("firstDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceFirstConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
