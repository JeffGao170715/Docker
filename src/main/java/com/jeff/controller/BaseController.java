package com.jeff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * Controller 的基类
 * Created by Jeff on 2018/5/25.
 */
public class BaseController {
    protected final static Logger logger = LoggerFactory.getLogger("JeffController");

    protected DeferredResult<String> processRequest(Runnable runnable){
        DeferredResult<String> result = new DeferredResult<>(10000L);
        result.onTimeout(runnable);
        return result;
    }


    protected WebAsyncTask processRequest(Callable<Object> callable){
        return  new WebAsyncTask(10000, callable);      // 超时10秒
    }


    protected void testAsync(long time) throws Exception{
        Thread.sleep(time);
    }
}
