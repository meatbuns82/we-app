package com.luwh.we.app.core.http;

import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * http 请求的内容实体
 *
 * @author lu.wh
 * @date 2023/10/19 12/00/24
 * @description
 */
public class HttpRequestContent {

    private String url;

    private HttpMethod method;

    private Map<String, Object> params;

    private String requestId;

    public static HttpRequestContent build(String url, HttpMethod method, Map<String, Object> params){
        HttpRequestContent content = new HttpRequestContent();
        content.url = url;
        content.method = method;
        content.params = params;
        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
