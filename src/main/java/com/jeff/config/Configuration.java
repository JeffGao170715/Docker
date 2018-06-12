package com.jeff.config;

import com.jeff.interceptor.JeffServlet;
import io.undertow.UndertowOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 系统相关的配置
 * Created by Jeff on 2018/5/23.
 */
@SpringBootConfiguration
public class Configuration implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(Configuration.class);

    /**
     * Undertow 的相关配置
     * @return
     */
    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory(){
        logger.info("<======加载Undertow的配置======>");
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        int cpuNum = Runtime.getRuntime().availableProcessors();
        factory.setIoThreads(cpuNum);
        factory.setWorkerThreads(cpuNum << 3);      // 工作线程数
//        factory.setBufferSize(1024);
//        factory.setUseDirectBuffers(true);      // 分配的直接内存(NIO直接分配的堆外内存)
        factory.addBuilderCustomizers(builder -> builder.setServerOption(
                UndertowOptions.ENABLE_HTTP2, true      // 支持 HTTP2 协议
        ));
        return factory;
    }

    /**
     * 添加拦截器及拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JeffServlet()).addPathPatterns("/**");
    }

    // ******************* 配置异步线程池 ****************************** //
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);               // 最少线程数
        taskExecutor.setMaxPoolSize(100);               // 最大线程数
        taskExecutor.setQueueCapacity(1000);            // 缓冲队列的容量
        taskExecutor.setKeepAliveSeconds(60);           // 线程允许的存活时间，60s
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setThreadNamePrefix("Jeff-task-");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor(){
        return new TimeoutCallableProcessingInterceptor();
    }

    /**
     * 配置线程池
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setDefaultTimeout(20000L);       // 默认20秒超时
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());

        configurer.setTaskExecutor(threadPoolTaskExecutor());
    }
    // ********************************************************************************* //

    // ******************** 装载 RabbitMq 消息队列 ************************************** //
//    @Bean
//    @Lazy
//    @RabbitListener
//    public void test(){
//
//    }

    // ******************************************************************************** //


    // ********************* Redis 配置 ************************************************ //
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        return new JedisConnectionFactory();
//    }
    // ******************************************************************************** //

}
