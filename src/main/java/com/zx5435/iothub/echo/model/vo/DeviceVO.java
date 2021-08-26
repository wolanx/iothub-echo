package com.zx5435.iothub.echo.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zx5435
 */
@Data
public class DeviceVO {

    private Long id;

    private Integer type;

    private String productKey;

    private String deviceName;

    private String deviceSecret;

    private Date lastOnlineTs;

    // other

    private Boolean isOnline;

}
