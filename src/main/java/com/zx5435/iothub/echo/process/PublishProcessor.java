package com.zx5435.iothub.echo.process;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;

/**
 * @author zx5435
 */
public class PublishProcessor implements IProcessor<MqttPublishMessage> {

    public static final PublishProcessor INSTANCE = new PublishProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttPublishMessage msg) {
        String topic = msg.variableHeader().topicName();
        String msgRaw = parseMsg(msg.payload());

        System.out.printf("topic=%s msg=%s\n", topic, msgRaw);

        // AT_MOST_ONCE no ack
        // AT_LEAST_ONCE MqttPubAckMessage
        // EXACTLY_ONCE PubRec

        // todo
    }

    private String parseMsg(ByteBuf payload) {
        byte[] payloadContent = new byte[payload.readableBytes()];
        int mark = payload.readerIndex();
        payload.readBytes(payloadContent);
        payload.readerIndex(mark);
        return new String(payloadContent);
    }

}
