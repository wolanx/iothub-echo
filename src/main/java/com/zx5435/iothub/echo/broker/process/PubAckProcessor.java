package com.zx5435.iothub.echo.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;

/**
 * @author zx5435
 */
public class PubAckProcessor implements IProcessor<MqttPubAckMessage> {

    public static final PubAckProcessor INSTANCE = new PubAckProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttPubAckMessage msg) {
        System.out.println("msg = " + msg);

        // todo
    }

}
