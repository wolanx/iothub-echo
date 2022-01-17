package com.wolanx.echo.iothub.broker.process;

import com.wolanx.echo.iothub.service.DeviceStateService;
import com.wolanx.echo.iothub.util.ChanUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wolanx
 */
@Component
public class DisconnectProcessor implements IProcessor<MqttMessage> {

    @Resource
    DeviceStateService deviceStateService;

    @Override
    public void process(ChannelHandlerContext ctx, MqttMessage msg) {
        ctx.channel().flush().close();

        String username = ChanUtil.getUsername(ctx.channel());
        deviceStateService.markOffline(username);
    }

}
