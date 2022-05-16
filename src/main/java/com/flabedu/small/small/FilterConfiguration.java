package com.flabedu.small.small;

import com.flabedu.small.small.web.LoginBlockFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<LoginBlockFilter> loginBlock(){
        var regBean = new FilterRegistrationBean<LoginBlockFilter>();
        regBean.setFilter(new LoginBlockFilter());
        regBean.addUrlPatterns("/items/product");

        return regBean;
    }
}
