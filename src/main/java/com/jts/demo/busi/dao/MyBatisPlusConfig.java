package com.jts.demo.busi.dao;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@MapperScans(value = {@MapperScan("com.jts.demo.busi.dao")})
public class MyBatisPlusConfig {

    @Value("classpath:sql/init.sql")
    private Resource dbScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbScript);

        DataSourceInitializer init = new DataSourceInitializer();
        init.setDataSource(dataSource);
        init.setDatabasePopulator(populator);
        return init;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor (){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    /*@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisConfiguration conf = new MybatisConfiguration();
        conf.setLogImpl(Log4j2Impl.class);
        conf.setDefaultExecutorType(ExecutorType.SIMPLE);
        conf.setCacheEnabled(false);
        conf.setLocalCacheScope(LocalCacheScope.STATEMENT);

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfiguration(conf);
        sqlSessionFactory.setPlugins(mybatisPlusInterceptor());
        //sqlSessionFactory.setPlugins(new PaginationInterceptor());
        return sqlSessionFactory.getObject();
    }*/
}
