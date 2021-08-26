package com.zx5435.iothub.echo.broker.process;

import com.zx5435.iothub.echo.service.DeviceStateService;
import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
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
