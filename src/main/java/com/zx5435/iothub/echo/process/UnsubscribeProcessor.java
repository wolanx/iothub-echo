package com.zx5435.iothub.echo.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;

import java.util.List;

/**
 * @author zx5435
 */
public class UnsubscribeProcessor implements IProcessor<MqttUnsubscribeMessage> {

    public static final UnsubscribeProcessor INSTANCE = new UnsubscribeProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttUnsubscribeMessage msg) {
        List<String> topics = msg.payload().topics();
        for (String topic : topics) {
            System.out.println("topic = " + topic);
        }
    }

}
