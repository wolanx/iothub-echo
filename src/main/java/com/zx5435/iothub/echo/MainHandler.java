package com.zx5435.iothub.echo;

import com.zx5435.iothub.echo.process.PubAckProcessor;
import com.zx5435.iothub.echo.process.PublishProcessor;
import com.zx5435.iothub.echo.process.SubscribeProcessor;
import com.zx5435.iothub.echo.process.UnsubscribeProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zx5435
 */
@Slf4j
public class MainHandler extends SimpleChannelInboundHandler<MqttMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) throws Exception {
        MqttMessageType mqttMessageType = msg.fixedHeader().messageType();

        log.info(mqttMessageType.toString());

        switch (mqttMessageType) {
            case CONNECT:
                ctx.channel().writeAndFlush(new MqttConnAckMessage(
                        new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                        new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, false)
                ));
                break;
            case PINGREQ:
                ctx.channel().writeAndFlush(new MqttMessage(
                        new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0)
                ));
                break;
            case SUBSCRIBE:
                SubscribeProcessor.INSTANCE.process(ctx, (MqttSubscribeMessage) msg);
                break;
            case UNSUBSCRIBE:
                UnsubscribeProcessor.INSTANCE.process(ctx, (MqttUnsubscribeMessage) msg);
                break;
            case PUBACK:
                PubAckProcessor.INSTANCE.process(ctx, (MqttPubAckMessage) msg);
                break;
            case PUBLISH:
                PublishProcessor.INSTANCE.process(ctx, (MqttPublishMessage) msg);
                break;
            default:
                log.error("todo = " + mqttMessageType);
        }
    }

}
