package com.zagar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by naayadaa on 04.05.17.
 */
//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration/terragram").setViewName("regterra");
        registry.addViewController("/registration/instaleads").setViewName("reginsta");
        registry.addViewController("/question/terragram").setViewName("queterra");
        registry.addViewController("/question/instaleads").setViewName("queinsta");

    }
}
