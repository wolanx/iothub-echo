package com.zx5435.iothub.echo.broker.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx5435
 */
public class SubscribeProcessor implements IProcessor<MqttSubscribeMessage> {

    public static final SubscribeProcessor INSTANCE = new SubscribeProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        List<MqttTopicSubscription> subs = msg.payload().topicSubscriptions();
        List<Integer> qosArr = new ArrayList<>();
        for (MqttTopicSubscription sub : subs) {
            String topic = sub.topicName();
            System.out.println("topic = " + topic);

            // todo

            qosArr.add(sub.qualityOfService().value());
        }

        MqttSubAckMessage nm = genAck(msg.variableHeader().messageId(), qosArr);
        ctx.channel().writeAndFlush(nm);
    }

    private MqttSubAckMessage genAck(int msgId, List<Integer> qosArr) {
        return new MqttSubAckMessage(
                new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(msgId),
                new MqttSubAckPayload(qosArr)
        );
    }

}
