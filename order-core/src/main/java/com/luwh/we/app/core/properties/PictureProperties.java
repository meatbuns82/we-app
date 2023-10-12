package com.luwh.we.app.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lu.wh
 * @date 2023/10/12 14/36/35
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "picture")
public class PictureProperties {
    private String cachePath = System.getProperty("user.dir") + "/" + "cache/picture";


    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }
}
