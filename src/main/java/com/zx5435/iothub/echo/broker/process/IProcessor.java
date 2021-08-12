package com.zx5435.iothub.echo.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author zx5435
 */
interface IProcessor<T extends MqttMessage> {

    /**
     * do
     * @param ctx ctx
     * @param msg msg
     */
    void process(ChannelHandlerContext ctx, T msg);

}
