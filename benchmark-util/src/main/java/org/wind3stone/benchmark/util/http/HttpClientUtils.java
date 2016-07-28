/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wind3stone.benchmark.util.http;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author fenglei
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * get get
     *
     * @param url
     * @param paramMap
     * @param httpTimeout
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> paramMap,
                             int httpTimeout) throws Exception {
        List<NameValuePair> params = Lists.newArrayList();
        for (Map.Entry<String, String> mapItem : paramMap.entrySet()) {
            NameValuePair nameValuePair = new BasicNameValuePair(mapItem.getKey(),
                    mapItem.getKey());
            params.add(nameValuePair);
        }
        String body = "";
        CloseableHttpResponse httpresponse = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(httpTimeout).
                    setConnectTimeout(httpTimeout).build();
            httpget.setConfig(requestConfig);

            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));

            httpresponse = httpClient.execute(httpget);

            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (ParseException e) {
            logger.error("HttpInvokeUtils.get ParseException", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("HttpInvokeUtils.get UnsupportedEncodingException", e);
            throw e;
        } catch (IOException e) {
            logger.error("HttpInvokeUtils.get IOException", e);
            throw e;
        } catch (URISyntaxException e) {
            logger.error("HttpInvokeUtils.get URISyntaxException", e);
            throw e;
        } finally {
            IOUtils.closeQuietly(httpClient);
            IOUtils.closeQuietly(httpresponse);
        }
        return body;
    }

    /**
     * post post
     *
     * @param url
     * @param paramMap
     * @param httpTimeout
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> paramMap, String data,
                              Map<String, String> headerMap, int httpTimeout) throws Exception {
        List<NameValuePair> params = Lists.newArrayList();
//        for (Map.Entry<String, String> paramEntry : paramMap.entrySet()) {
//            NameValuePair nameValuePair = new BasicNameValuePair(paramEntry.getKey(),
//                    paramEntry.getValue());
//            params.add(nameValuePair);
//        }

        String body = "";
        CloseableHttpResponse httpresponse = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httppost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(httpTimeout).
                    setConnectTimeout(httpTimeout).build();
            httppost.setConfig(requestConfig);

//            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httppost.setURI(new URI(httppost.getURI().toString()));
//            httppost.setEntity(new UrlEncodedFormEntity(params));
            for (Map.Entry<String, String> headerItemMap : headerMap.entrySet()) {
                httppost.setHeader(headerItemMap.getKey(), headerItemMap.getValue());
            }
//            httppost.addHeader("Content-type","application/json; charset=utf-8");
//            httppost.setHeader("Accept", "application/json");
            httppost.setEntity(new StringEntity(data, Charset.forName("UTF-8")));
//            String transJson = jsonObject.toString();
//            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");

            httpresponse = httpClient.execute(httppost);

            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (ParseException e) {
            logger.error("HttpInvokeUtils.get ParseException", e);
            throw e;
        } catch (UnsupportedEncodingException e) {
            logger.error("HttpInvokeUtils.get UnsupportedEncodingException", e);
            throw e;
        } catch (IOException e) {
            logger.error("HttpInvokeUtils.get IOException", e);
            throw e;
        } catch (URISyntaxException e) {
            logger.error("HttpInvokeUtils.get URISyntaxException", e);
            throw e;
        } finally {
            IOUtils.closeQuietly(httpClient);
            IOUtils.closeQuietly(httpresponse);
        }
        return body;
    }

    /**
     * setHeaderJson setHeaderJson
     *
     * @return
     */
    public static Map<String, String> configHeaderJsonMap() {
        Map<String, String> headerMap = Maps.newHashMap();
        //            httppost.addHeader("Content-type","application/json; charset=utf-8");
//            httppost.setHeader("Accept", "application/json");
        headerMap.put("Accept", "application/json");
        headerMap.put("Content-type", "application/json; charset=utf-8");
        return headerMap;
    }

}
