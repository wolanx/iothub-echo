package com.zx5435.echo.iothub.model.db;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zx5435
 */
@Entity
@Table(name = "iot_device")
@Data
public class DeviceDO implements Serializable {

    private static final long serialVersionUID = 1743314721757240445L;

    @Id
    private Long id;

    @Column(nullable = false)
    private Integer type;

    private String productKey;

    @Column(unique = true)
    private String deviceName;

    private String deviceSecret;

    private Date lastOnlineTs;

}
