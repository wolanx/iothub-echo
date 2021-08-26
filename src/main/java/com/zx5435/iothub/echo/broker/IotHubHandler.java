package com.zx5435.iothub.echo.broker;

import com.zx5435.iothub.echo.broker.process.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class IotHubHandler extends SimpleChannelInboundHandler<MqttMessage> {

    @Resource
    ConnectProcessor connectProcessor;

    @Resource
    DisconnectProcessor disconnectProcessor;

    @Resource
    PingReqProcessor pingReqProcessor;

    @Resource
    PublishProcessor publishProcessor;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) throws Exception {
        MqttMessageType mqttMessageType = msg.fixedHeader().messageType();

        log.info(mqttMessageType.toString());

        switch (mqttMessageType) {
            case CONNECT:
                if (msg instanceof MqttConnectMessage) {
                    connectProcessor.process(ctx, (MqttConnectMessage) msg);
                } else {
                    connectProcessor.processOther(ctx, msg);
                }
                break;
            case DISCONNECT:
                disconnectProcessor.process(ctx, msg);
                break;
            case PINGREQ:
                pingReqProcessor.process(ctx, msg);
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
                publishProcessor.process(ctx, (MqttPublishMessage) msg);
                break;
            default:
                log.error("todo = " + mqttMessageType);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught: " + cause.getMessage());
        //super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ctx.close();
    }

}
