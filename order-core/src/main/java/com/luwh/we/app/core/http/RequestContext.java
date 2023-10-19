package com.luwh.we.app.core.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;

import java.nio.charset.StandardCharsets;

/**
 *
 * 请求上下文
 * @author lu.wh
 * @date 2023/10/19 12/00/44
 * @description
 */
public abstract class RequestContext {
    private CloseableHttpClient httpClient;

    public RequestContext() {
        httpClient = HttpClientBuilder.create().build();
    }

    public void request(HttpRequestContent content){
        preRequest();
        prepareParams(content);
        doRequest();
        afterRequest();
    }

    protected void preRequest(){

    }

    private HttpUriRequest prepareParams(HttpRequestContent content) {
//        Map<String, Object> params = content.getParams();
        HttpMethod method = content.getMethod();
        HttpUriRequest request = null;
        switch (method){
            case GET:
                HttpGet getRequest = new HttpGet(content.getUrl());
                request = getRequest;
                break;
            case DELETE:
                HttpDelete deleteRequest = new HttpDelete(content.getUrl());
                request = deleteRequest;
                // 拼到路径上
                break;
            case PUT:
                HttpPut httpPut = new HttpPut(content.getUrl());
                httpPut.setEntity(new StringEntity(JSONObject.toJSONString(content.getParams(), SerializerFeature.WriteMapNullValue), StandardCharsets.UTF_8.name()));
                break;
            case POST:
                HttpPost httpPost = new HttpPost(content.getUrl());
                httpPost.setEntity(new StringEntity(JSONObject.toJSONString(content.getParams(), SerializerFeature.WriteMapNullValue), StandardCharsets.UTF_8.name()));
                break;
                // 请求体
            default:
                throw new RuntimeException("unsupport http request method:[{" + method + "}]");
        }
        return request;
    }

    protected void doRequest() {
//        httpClient.ex
    }
    protected void afterRequest(){

    }
}
