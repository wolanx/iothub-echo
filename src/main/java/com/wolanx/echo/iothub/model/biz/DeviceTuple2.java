package com.wolanx.echo.iothub.model.biz;

/**
 * @author wolanx
 */
public class DeviceTuple2 {

    private String pk;
    private String name;

    public static DeviceTuple2 of(String username){
        String[] uArr = username.split("&");
        DeviceTuple2 ret = new DeviceTuple2();
        ret.pk = uArr[1];
        ret.name = uArr[0];
        return ret;
    }

    public String getPk() {
        return pk;
    }

    public String getName() {
        return name;
    }

}
