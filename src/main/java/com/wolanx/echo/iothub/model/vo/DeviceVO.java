package com.wolanx.echo.iothub.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author wolanx
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
