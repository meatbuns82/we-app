package com.luwh.we.app.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lu.wh
 * @date 2023/10/17 11/53/02
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "rabbit")
public class RabbitMqProperties {
    private String address;
    private Integer port = 9001;
    private String user;
    private String password;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
