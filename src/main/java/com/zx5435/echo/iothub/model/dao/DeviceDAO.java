package com.zx5435.echo.iothub.model.dao;

import com.zx5435.echo.iothub.model.db.DeviceDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author zx5435
 */
public interface DeviceDAO extends JpaRepository<DeviceDO, Long> {

    Optional<DeviceDO> findByDeviceNameAndProductKey(String deviceName, String productKey);

}
