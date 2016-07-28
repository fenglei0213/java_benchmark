package org.wind3stone.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wind3stone.benchmark.util.WebResponse;

/**
 * Created by fenglei on 2016/7/28.
 */
@Controller
public class EchoController {

    private static Logger logger = LoggerFactory.getLogger(EchoController.class);

    /**
     * getDsData getDsData
     *
     * @return
     */
    @ResponseBody
    @RequestMapping
    public WebResponse echo() {
        WebResponse webResponse = new WebResponse();
//        Threads.sleep();
        logger.info("echo is");
        return webResponse;
    }
}
