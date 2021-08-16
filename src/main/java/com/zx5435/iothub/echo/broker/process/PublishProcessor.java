package com.zx5435.iothub.echo.broker.process;

import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zx5435
 */
@Component
public class PublishProcessor implements IProcessor<MqttPublishMessage> {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void process(ChannelHandlerContext ctx, MqttPublishMessage msg) {
        Channel channel = ctx.channel();
        String topic = msg.variableHeader().topicName();
        String msgRaw = parseMsg(msg.payload());

        String clientId = ChanUtil.getClientId(channel);
        String username = ChanUtil.getUsername(channel);
        System.out.println("clientId = " + clientId);
        System.out.println("username = " + username);

        System.out.printf("topic=%s msg=%s\n", topic, msgRaw);

        // AT_MOST_ONCE no ack
        // AT_LEAST_ONCE MqttPubAckMessage variableHeader().packetId()
        // EXACTLY_ONCE when pubRel send PubRec variableHeader().packetId()

        StreamOperations<String, String, String> stream = stringRedisTemplate.opsForStream();
        Map<String, String> a = new HashMap<String, String>(2) {{
            put("SNO", username);
            put("topic", topic);
            put("a", msgRaw);
        }};
        stream.add("x:topic:all", a);
    }

    private String parseMsg(ByteBuf payload) {
        byte[] payloadContent = new byte[payload.readableBytes()];
        int mark = payload.readerIndex();
        payload.readBytes(payloadContent);
        payload.readerIndex(mark);
        return new String(payloadContent);
    }

}
