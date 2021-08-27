package com.zx5435.iothub.echo.manager;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zx5435
 * http://events.jianshu.io/p/67bcc64f8dc9
 */
@Component
public class InfluxdbManager {

    @Resource
    InfluxDB influxdb;

    public static String TABLE = "sensor";

    public void write(String sn) {
        Point point = Point.measurement(TABLE)
                //.tag("deviceId", "sensor1")
                .tag("sn", sn)
                .addField("temp", 3)
                .addField("voltage", 145)
                .addField("A1", "4i")
                .addField("A2", "4i")
                .build();
        influxdb.write(point);
    }

    public List<Object> read(String sql) {
        List<Object> ret = new ArrayList<>();
        QueryResult queryResult = influxdb.query(new Query(sql));
        queryResult.getResults().forEach(result -> {
            result.getSeries().forEach(row -> {
                List<String> columns = row.getColumns();
                int fieldSize = columns.size();
                row.getValues().forEach(value -> {
                    Map<String, Object> one = new HashMap<>();
                    for (int i = 0; i < fieldSize; i++) {
                        one.put(columns.get(i), value.get(i));
                    }
                    ret.add(one);
                });
            });
        });
        return ret;
    }

}
