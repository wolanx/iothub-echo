package com.zx5435.iothub.echo.process;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

import java.util.List;

/**
 * @author zx5435
 */
public class SubscribeProcessor implements IProcessor<MqttSubscribeMessage> {

    public static final SubscribeProcessor INSTANCE = new SubscribeProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        List<MqttTopicSubscription> subs = msg.payload().topicSubscriptions();
        for (MqttTopicSubscription sub : subs) {
            String topic = sub.topicName();
            System.out.println("topic = " + topic);

            MqttPublishMessage nm = asdf(topic, sub.qualityOfService(), msg.variableHeader().messageId());
            ctx.channel().writeAndFlush(nm);
        }
    }

    private MqttPublishMessage asdf(String topic, MqttQoS mqttQoS, int i) {
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, mqttQoS, true, 0);
        MqttPublishVariableHeader variableHeader = new MqttPublishVariableHeader(topic, i); // messageId
        return new MqttPublishMessage(fixedHeader, variableHeader, Unpooled.buffer().writeBytes("hehe".getBytes()));
    }

}
