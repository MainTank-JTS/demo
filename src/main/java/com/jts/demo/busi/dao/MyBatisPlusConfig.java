package com.jts.demo.busi.dao;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@MapperScans(value = {@MapperScan("com.jts.demo.busi.dao")})
public class MyBatisPlusConfig {

    @Value("classpath:sql/init-dev.sql")
    private Resource dbScriptDev;

    @Value("classpath:sql/init-shardingsphere.sql")
    private Resource dbScriptShardingsphere;

    @Bean("DatabasePopulator")
    @ConditionalOnProperty(value = "spring.profiles.active",havingValue = "dev",matchIfMissing = true)
    public DatabasePopulator populatorByDev(){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbScriptDev);
        return populator;
    }

    @Bean("DatabasePopulator")
    @ConditionalOnProperty(value = "spring.profiles.active",havingValue = "shardingsphere")
    public DatabasePopulator populatorByShardingsphere(){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbScriptShardingsphere);
        return populator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource,final DatabasePopulator populator){
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
