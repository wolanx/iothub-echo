package com.wolanx.echo.iothub.model.dao;

import com.wolanx.echo.iothub.model.db.DeviceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author wolanx
 */
@Repository
public interface DeviceDAO extends JpaRepository<DeviceDO, Long> {

    Optional<DeviceDO> findByDeviceNameAndProductKey(String deviceName, String productKey);

}
