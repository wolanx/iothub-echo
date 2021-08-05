package com.zx5435.iothub.echo.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author zx5435
 */
interface IProcessor<T extends MqttMessage> {

    /**
     * @param ctx ctx
     * @param message msg
     */
    void process(ChannelHandlerContext ctx, T msg);

}
