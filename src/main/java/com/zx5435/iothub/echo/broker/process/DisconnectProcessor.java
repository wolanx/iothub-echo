package com.zx5435.iothub.echo.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author zx5435
 */
public class DisconnectProcessor implements IProcessor<MqttMessage> {

    public static final DisconnectProcessor INSTANCE = new DisconnectProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        ctx.channel().flush().close();
    }

}
