package com.luwh.we.app.server;

import com.luwh.we.app.common.constants.Constants;
import com.luwh.we.app.core.properties.PictureProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;


/**
 * @author lu.wh
 * @date 2023/09/11 14/36/43
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.luwh.we.app")
@MapperScan(basePackages = "com.luwh.we.app.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class BabyOrderApplication {
    private static final String FILE_PATH = "/conf/application.properties";
    private static final String APPLICATION_HOME = Constants.APPLICATION_HOME;
    private static final String APP_HOME = "home.dir";
    private static final String serverHome = System.getProperty(APP_HOME, System.getenv(APPLICATION_HOME));
    @Resource
    private PictureProperties properties;
    public static void main(String[] args) {
        System.setProperty(APPLICATION_HOME, serverHome);
        String applicationFile = serverHome + FILE_PATH;
//        System.setProperty("spring.config.location", applicationFile);
        SpringApplication.run(BabyOrderApplication.class, args);
    }
}