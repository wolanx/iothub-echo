package com.wolanx.echo.iothub.broker.process;

import com.wolanx.echo.iothub.manager.InfluxdbManager;
import com.wolanx.echo.iothub.model.biz.DeviceTuple2;
import com.wolanx.echo.iothub.util.ChanUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wolanx
 */
@Slf4j
@Component
public class PublishProcessor implements IProcessor<MqttPublishMessage> {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    InfluxdbManager influxdbManager;

    @Override
    public void process(ChannelHandlerContext ctx, MqttPublishMessage msg) {
        Channel channel = ctx.channel();
        String topic = msg.variableHeader().topicName();
        String msgRaw = parseMsg(msg.payload());

        //String clientId = ChanUtil.getClientId(channel);
        String username = ChanUtil.getUsername(channel);
        System.out.println("username = " + username);
        System.out.println("topic = " + topic);
        System.out.println("msgRaw = " + msgRaw);

        // AT_MOST_ONCE no ack
        // AT_LEAST_ONCE MqttPubAckMessage variableHeader().packetId()
        // EXACTLY_ONCE when pubRel send PubRec variableHeader().packetId()

        DeviceTuple2 d2 = DeviceTuple2.of(username);

        // /a1p9xMXq5Nd/iot-echo-903-913332/user/update
        String topicUserUpdate = "/" + d2.getPk() + "/" + d2.getName() + "/user/update";

        if (topic.equals(topicUserUpdate)) {
            StreamOperations<String, String, String> stream = stringRedisTemplate.opsForStream();
            Map<String, String> a = new HashMap<String, String>(2) {{
                put("SNO", d2.getName());
                put("a", msgRaw);
            }};
            stream.add("x:topic:all", a); // todo maxlen

            influxdbManager.write(d2.getName());
        } else {
            log.error("error topic");
        }
    }

    private String parseMsg(ByteBuf payload) {
        byte[] payloadContent = new byte[payload.readableBytes()];
        int mark = payload.readerIndex();
        payload.readBytes(payloadContent);
        payload.readerIndex(mark);
        return new String(payloadContent);
    }

}
