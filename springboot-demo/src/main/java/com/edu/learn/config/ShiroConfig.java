package com.edu.learn.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public  CustomRealm getRealm(){
     return new CustomRealm();
    }


    @Bean
    public  DefaultWebSecurityManager getSecurityManager(@Qualifier("getRealm") CustomRealm realm){
        DefaultWebSecurityManager securityManager  = new DefaultWebSecurityManager(realm);
        securityManager.setRealm(realm);
        return  securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFactory(@Qualifier("getSecurityManager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean =  new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        /**
         * anon :不用授权
         * authe：需要授权
         * user
         * perms
         * role
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/rest/edu/index","anon");
        filterMap.put("/rest/edu/update","authc");
        filterMap.put("/rest/edu/add","perms[yh:add]");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setLoginUrl("/rest/edu/login");
        //未授权设置
        factoryBean.setUnauthorizedUrl("/rest/edu/unauthorize");
        return  factoryBean;
    }

    /**
     * SHIRO整合thymeleaf配置
     */
    @Bean
    public ShiroDialect getThymeleaf(){
        return  new ShiroDialect();
    }


}
