package com.zx5435.iothub.echo.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;

/**
 * @author zx5435
 */
public class PingReqProcessor implements IProcessor<MqttMessage> {

    public static final PingReqProcessor INSTANCE = new PingReqProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        ctx.channel().writeAndFlush(new MqttMessage(
                new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0)
        ));
    }

}
