package com.luwh.we.app.core.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 *
 * 请求上下文
 * @author lu.wh
 * @date 2023/10/19 12/00/44
 * @description
 */
public class RequestContext {
    private static volatile RequestContext requestContext;
    private transient CloseableHttpClient httpClient;

    public RequestContext() {
        httpClient = HttpClientBuilder.create().build();
    }

    public static RequestContext getInstance(){
        if(requestContext == null){
            synchronized (RequestContext.class){
                if(requestContext == null){
                    requestContext = new RequestContext();
                }
            }
        }
        return requestContext;
    }

    public void request(HttpRequestContent content){
        preRequest();
        HttpUriRequest request = prepareRequest(content);
        doRequest(request, content);
        afterRequest(content);
    }

    protected void preRequest(){

    }

    private HttpUriRequest prepareRequest(HttpRequestContent content) {
        HttpMethod method = content.getMethod();
        HttpUriRequest request = null;
        switch (method){
            case GET:
                buildUrlParams(content);
                HttpGet getRequest = new HttpGet(content.getUrl());
                request = getRequest;
                break;
            case DELETE:
                buildUrlParams(content);
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

    private void buildUrlParams(HttpRequestContent content) {
        Map<String, Object> params = content.getParams();
        Set<String> strings = params.keySet();
        String url = content.getUrl();
        url = url + "?";
        for (String key : strings) {
            url = url + key + "=" + params.get(key)  + "&";
        }
        content.setUrl(url);
    }

    protected void doRequest(HttpUriRequest request, HttpRequestContent content) {
        try {
            CloseableHttpResponse execute = httpClient.execute(request);
            String body = EntityUtils.toString(execute.getEntity(), "UTF-8");
            if(execute.getStatusLine().getStatusCode() / 100 >= 2 && execute.getStatusLine().getStatusCode() / 100 < 3){
                // 成功
               content.setResultBody(body);
               content.setSuccess(true);
            }else {
                String errorMsg = String.format("http execute [%s], msg:[%s]", execute.getStatusLine().getStatusCode(), body);
                content.setSuccess(false);
                content.setErrorMsg(errorMsg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void afterRequest(HttpRequestContent content){

    }
}
