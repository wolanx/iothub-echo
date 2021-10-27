package com.zx5435.echo.iothub.service;

import com.zx5435.echo.iothub.model.biz.DeviceTuple2;
import com.zx5435.echo.iothub.model.dao.DeviceDAO;
import com.zx5435.echo.iothub.model.db.DeviceDO;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;

/**
 * @author zx5435
 */
@Slf4j
@Singleton
public class DeviceStateService {

    @Inject
    DeviceDAO deviceDAO;

//    @Inject
//    StringRedisTemplate stringRedisTemplate;

    public Boolean isOnline(String username) {
//        return stringRedisTemplate.hasKey("device:online:" + username);
        return false;
    }

    public void markOnline(String username) {
        log.info(username);
//        ValueOperations<String, String> r = stringRedisTemplate.opsForValue();
//        r.set("device:online:" + username, String.valueOf(System.currentTimeMillis()), Duration.ofSeconds(300));

        DeviceTuple2 d2 = DeviceTuple2.of(username);
        DeviceDO device = deviceDAO.findByDeviceNameAndProductKey(d2.getName(), d2.getPk()).orElse(null);
        if (device != null) {
            device.setLastOnlineTs(new Date());
            deviceDAO.save(device);
        }
    }

    public void markOffline(String username) {
        log.info(username);
//        stringRedisTemplate.delete("device:online:" + username);
    }

}
