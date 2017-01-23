package com.haoche.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@ImportResource({"classpath:applicationContext.xml", "classpath:applicationContext-mvc.xml"})
public class Application extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new SpringApplicationBuilder().sources(Application.class).web(true).run(args);
        logger.info("项目启动!");
    }
}
