package com.wolanx.echo.iothub.util;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author wolanx
 */
public class ChanUtil {

    private static final AttributeKey<String> CLIENTID = AttributeKey.valueOf("clientId");
    private static final AttributeKey<String> USERNAME = AttributeKey.valueOf("userName");

    public static void setClientId(Channel channel, String s) {
        channel.attr(CLIENTID).set(s);
    }

    public static void setUsername(Channel channel, String s) {
        channel.attr(USERNAME).set(s);
    }

    public static String getClientId(Channel channel) {
        return channel.attr(CLIENTID).get();
    }

    public static String getUsername(Channel channel) {
        return channel.attr(USERNAME).get();
    }

}
