package com.zx5435.iothub.echo.broker.process;

import com.zx5435.iothub.echo.service.DeviceStateService;
import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
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
