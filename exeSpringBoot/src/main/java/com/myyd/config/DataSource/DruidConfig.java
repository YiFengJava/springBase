package com.myyd.config.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.support.http.WebStatFilter;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfig {

    @Autowired
    private DataProperties dataProperties;

    @Bean   //声明其为Bean实例
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataProperties.getUrl());
        datasource.setUsername(dataProperties.getUsername());
        datasource.setPassword(dataProperties.getPassword());
        datasource.setDriverClassName(dataProperties.getDriverClassName());

        //configuration
        datasource.setInitialSize(dataProperties.getInitialSize());
        datasource.setMinIdle(dataProperties.getMinIdle());
        datasource.setMaxActive(dataProperties.getMaxActive());
        datasource.setMaxWait(dataProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataProperties.getValidationQuery());
        datasource.setTestWhileIdle(dataProperties.getTestWhileIdle());
        datasource.setTestOnBorrow(dataProperties.getTestOnBorrow());
        datasource.setTestOnReturn(dataProperties.getTestOnReturn());
        datasource.setPoolPreparedStatements(dataProperties.getPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dataProperties.getFilters());
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        datasource.setConnectionProperties(dataProperties.getConnectionProperties());
        return  datasource;
    }


    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单
        servletRegistrationBean.addInitParameter("deny","192.168.0.2");
        //设置控制台管理用户__登录用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
