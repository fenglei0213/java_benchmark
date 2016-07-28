package org.wind3stone.benchmark.core.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wind3stone.benchmark.util.PropertyConfigurer;
import org.wind3stone.benchmark.util.http.HttpClientUtils;

/**
 * Created by fenglei on 2016/7/28.
 */
public class HttpPressureProcessor implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(HttpPressureProcessor.class);

    public final static String url = PropertyConfigurer.getProperty("http.url.test");
    public final static int timeout = Integer.valueOf(PropertyConfigurer.getProperty("http.url.test"));

    public void run() {
        String data = "";
        try {
            HttpClientUtils.post(url, null, data, HttpClientUtils.configHeaderJsonMap(), timeout);
        } catch (Exception e) {
            logger.error("HttpPressureProcessor Exception:", e);
        }
    }
}
