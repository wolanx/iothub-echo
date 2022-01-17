package com.wolanx.echo.iothub.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author wolanx
 */
interface IProcessor<T extends MqttMessage> {

    /**
     * do
     * @param ctx ctx
     * @param msg msg
     */
    void process(ChannelHandlerContext ctx, T msg);

}
