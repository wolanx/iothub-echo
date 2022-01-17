package com.wolanx.echo.iothub.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wolanx
 */
@Configuration
public class InfluxdbConfig {

    @Value("${spring.influx.url}")
    private String url;

    @Value("${spring.influx.database}")
    private String database;

    @Value("${spring.influx.user}")
    private String user;

    @Value("${spring.influx.password}")
    private String password;

    @Bean
    public InfluxDB influxdb() {
        InfluxDB conn = InfluxDBFactory.connect(url, user, password);
        conn.setDatabase(database);
        //.enableBatch(100,1000 * 60, TimeUnit.MILLISECONDS);
        //设置默认策略
        //conn.setRetentionPolicy("sensor_retention");
        //设置日志输出级别
        //conn.setLogLevel(InfluxDB.LogLevel.BASIC);
        return conn;
    }

}


