package com.zx5435.iothub.echo.iot;

import com.zx5435.iothub.echo.process.*;
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
                if (msg instanceof MqttConnectMessage) {
                    ConnectProcessor.INSTANCE.process(ctx, (MqttConnectMessage) msg);
                } else {
                    ConnectProcessor.INSTANCE.processOther(ctx, msg);
                }
                break;
            case DISCONNECT:
                DisconnectProcessor.INSTANCE.process(ctx, msg);
                break;
            case PINGREQ:
                PingReqProcessor.INSTANCE.process(ctx, msg);
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
