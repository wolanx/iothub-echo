package com.wolanx.echo.iothub.broker.process;

import com.wolanx.echo.iothub.service.DeviceStateService;
import com.wolanx.echo.iothub.util.ChanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wolanx
 */
@Component
public class PingReqProcessor implements IProcessor<MqttMessage> {

    @Resource
    DeviceStateService deviceStateService;

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        Channel channel = ctx.channel();
        channel.writeAndFlush(new MqttMessage(
                new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0)
        ));

        String username = ChanUtil.getUsername(channel);
        deviceStateService.markOnline(username);
    }

}
