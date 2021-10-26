package com.zx5435.echo.iothub.model.dao;

import com.zx5435.echo.iothub.model.db.DeviceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author zx5435
 */
@Repository
public interface DeviceDAO extends JpaRepository<DeviceDO, Long> {

    Optional<DeviceDO> findByDeviceNameAndProductKey(String deviceName, String productKey);

}
