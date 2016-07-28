package org.wind3stone.benchmark.core.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;

/**
 * Created by fenglei on 2016/7/28.
 */
@Service
public class HttpPressureService {

    private static Logger logger = LoggerFactory.getLogger(HttpPressureService.class);

    public void httpPressure(int concurrentThreadNum) {
        Executor executor = Executors.newFixedThreadPool(concurrentThreadNum);
        for (int i = 0; i < concurrentThreadNum; i++) {
            executor.execute(new HttpPressureProcessor());
        }
    }
}
