/*
 * Baidu.com Inc.
 * Copyright (c) 2000-2014 All Rights Reserved.
 */
package org.wind3stone.benchmark.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @title SpringContextHolder
 * @description 获得applicationContext。目前主要在AOP中获得
 * @author fenglei (fenglei@baidu.com)
 * @date Mar 10, 2016
 * @version 1.0
 */
@Service
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     */
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     */
    public static void clearHolder() {
        logger.debug("clear SpringContextHolder's ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("set ApplicationContext to SpringContextHolder:{}", applicationContext);

        if (SpringContextHolder.applicationContext != null) {
            logger.warn("SpringContextHolder ApplicationContext covered, primary ApplicationContext is:"
                    + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext; //NOSONAR
    }

    /**
     */
//    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicaitonContext properties set, please definition SpringContextHolder in applicationContext.xml.");
    }
}
