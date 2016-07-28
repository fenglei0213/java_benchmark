/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wind3stone.benchmark.util;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author fenglei
 */
public class InitSpringContext {

    private static String INIT_CONFIG_XML = "";
    private static ApplicationContext context = null;
    private static InitSpringContext contextHolder = null;

    private InitSpringContext() {
        // load Spring config
        List<String> args = Lists.newArrayList(INIT_CONFIG_XML);
        context = new ClassPathXmlApplicationContext(StringUtils.toStringArray(args));
    }

    /**
     * init init
     */
//    public static void init() {
//        InitSpringContext.initSpringContextHolder();
//    }

    /**
     * init init
     *
     * @param initXmlConfigFile
     */
    public static void init(String initXmlConfigFile) {
        INIT_CONFIG_XML = initXmlConfigFile;
        InitSpringContext.initSpringContextHolder();
    }

    public static void initSpringContextHolder() {
        if (InitSpringContext.isEmpty()) {
            contextHolder = new InitSpringContext();
        }
    }

    public static Object getBean(String beanName) {
        if (InitSpringContext.isEmpty()) {
            contextHolder = new InitSpringContext();
        }
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class clazz) {
        if (InitSpringContext.isEmpty()) {
            contextHolder = new InitSpringContext();
        }
        return (T) context.getBean(beanName, clazz);
    }

    public static boolean isEmpty() {
        return (contextHolder == null) ? true : false;
    }
}
