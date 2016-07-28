package org.wind3stone.benchmark.core.biz;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by fenglei on 2016/7/28.
 */
public class HttpPressureService {

    public void httpPressure(int concurrentThreadNum) {

        Executor executor = Executors.newFixedThreadPool(concurrentThreadNum);
        for (int i = 0; i < concurrentThreadNum; i++) {
            executor.execute(new HttpPressureProcessor());
        }
    }
}
