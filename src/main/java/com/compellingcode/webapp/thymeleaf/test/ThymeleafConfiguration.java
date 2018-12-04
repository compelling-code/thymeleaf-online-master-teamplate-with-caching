package com.compellingcode.webapp.thymeleaf.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;

@Configuration
@ControllerAdvice
public class ThymeleafConfiguration {
	@Value("${master.template.url}")
	String masterTemplate;

    @ModelAttribute("masterTemplate")
    public String addMasterTemplate() {
    	return masterTemplate;
    }

    @Autowired
    SpringResourceTemplateResolver springResourceTemplateResolver;

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        UrlTemplateResolver urlTemplateResolver = new UrlTemplateResolver();
        urlTemplateResolver.setCacheable(true);
        urlTemplateResolver.setCacheTTLMs(60000L);
        
        springResourceTemplateResolver.setPrefix("classpath:/templates/");
        springResourceTemplateResolver.setSuffix(".html");

        templateEngine.addTemplateResolver(urlTemplateResolver);
        templateEngine.addTemplateResolver(springResourceTemplateResolver);
        
        templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));

        return templateEngine;
    }
    
}
