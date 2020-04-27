package com.poetryappreciation.util;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientUtil {

    // 连接主机超时（60s）
    public static final int HTTP_CONNECT_TIMEOUT_60S = 60 * 1000;

    // 从主机读取数据超时（60s）
    public static final int HTTP_READ_TIMEOUT_60S = 60 * 1000;
    /*
     Http协议GET请求
     */
    public static String httpGet(String url) throws Exception {

        //初始化HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpGet
        HttpGet httpGet = new HttpGet(url);

        //超时时间设置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_CONNECT_TIMEOUT_60S)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT_60S).build();

        httpGet.setConfig(requestConfig);


        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取请求状态码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;

    }

    /*
      Http协议Post请求
     */
    public static String httpPost(String url, String json) throws Exception {
        //初始HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post对象
        HttpPost httpPost = new HttpPost(url);
        //设置Content-Type
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        //写入JSON数据
        httpPost.setEntity(new StringEntity(json,"utf-8"));

        //超时时间设置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_CONNECT_TIMEOUT_60S)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT_60S).build();

        httpPost.setConfig(requestConfig);

        //发起请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取请求码
        //response.getStatusLine().getStatusCode();
        //获取返回数据实体对象
        HttpEntity entity = response.getEntity();
        //转为字符串
        String result = EntityUtils.toString(entity, "UTF-8");
        return result;

    }


    public static String httpsGet(String url) throws Exception {
        CloseableHttpClient hp = createSSLClientDefault();
        HttpGet hg = new HttpGet(url);

        //超时时间设置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_CONNECT_TIMEOUT_60S)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT_60S).build();

        hg.setConfig(requestConfig);


        CloseableHttpResponse response = hp.execute(hg);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "UTF-8");
        hp.close();
        return content;
    }

    public static String httpsPost(String url, String json) throws Exception {

        CloseableHttpClient hp = createSSLClientDefault();
        HttpPost httpsPost = new HttpPost(url);
        httpsPost.setHeader("Content-Type", "application/json");
        httpsPost.setEntity(new StringEntity(json));
        //超时时间设置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_CONNECT_TIMEOUT_60S)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT_60S).build();

        httpsPost.setConfig(requestConfig);
        CloseableHttpResponse response = hp.execute(httpsPost);
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity, "UTF-8");
        hp.close();
        return content;
    }


    public static CloseableHttpClient createSSLClientDefault() throws Exception {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            //信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    //微信二维码生成
    public static InputStream getInputStream(String url,String json) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);  // 接口
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity entity;
        entity = new StringEntity(json);
        entity.setContentType("image/png");
        //超时时间设置
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(HTTP_CONNECT_TIMEOUT_60S)
                .setConnectTimeout(HTTP_CONNECT_TIMEOUT_60S).build();
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        InputStream inputStream = response.getEntity().getContent();

        return inputStream;
    }


}
