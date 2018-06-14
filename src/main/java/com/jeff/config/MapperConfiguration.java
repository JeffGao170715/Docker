package com.jeff.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ResourceLoader;
import tk.mybatis.mapper.autoconfigure.ConfigurationCustomizer;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;
import tk.mybatis.mapper.autoconfigure.MybatisProperties;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Mybatis 通用 Mapper 相关配置
 * Created by Jeff on 2018/6/13.
 */
@SpringBootConfiguration
@MapperScan(basePackages = "com.jeff.service.*")
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MapperConfiguration extends MapperAutoConfiguration {
    /**
     * 构造方法用来屏蔽启动警告：no mybatis mapper was found in '[***.***.*** ......]'package
     * @param properties
     * @param interceptorsProvider
     * @param resourceLoader
     * @param databaseIdProvider
     * @param configurationCustomizersProvider
     */
    public MapperConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }
}
