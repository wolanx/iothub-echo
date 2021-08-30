package com.zx5435.iothub.echo.service;

import com.zx5435.iothub.echo.model.biz.DeviceTuple2;
import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.time.Duration;

/**
 * @author zx5435
 */
@Slf4j
@Service
public class DeviceStateService {

    @Resource
    DeviceDAO deviceDAO;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public Boolean isOnline(String username) {
        return stringRedisTemplate.hasKey("device:online:" + username);
    }

    public void markOnline(String username) {
        log.info(username);
        ValueOperations<String, String> r = stringRedisTemplate.opsForValue();
        r.set("device:online:" + username, String.valueOf(System.currentTimeMillis()), Duration.ofSeconds(300));

        DeviceTuple2 d2 = DeviceTuple2.of(username);
        DeviceDO device = deviceDAO.findByDeviceNameAndProductKey(d2.getName(), d2.getPk()).orElse(null);
        if (device != null) {
            device.setLastOnlineTs(new Date());
            deviceDAO.save(device);
        }
    }

    public void markOffline(String username) {
        log.info(username);
        stringRedisTemplate.delete("device:online:" + username);
    }

}
