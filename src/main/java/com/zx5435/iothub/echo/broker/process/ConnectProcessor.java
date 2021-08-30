package com.zx5435.iothub.echo.broker.process;

import com.zx5435.iothub.echo.model.biz.DeviceTuple2;
import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import com.zx5435.iothub.echo.service.DeviceStateService;
import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zx5435
 * a1p9xMXq5Nd1.iot-as-mqtt.cn-shanghai.aliyuncs.com
 */
@Slf4j
@Component
public class ConnectProcessor implements IProcessor<MqttConnectMessage> {

    @Resource
    DeviceDAO deviceDAO;

    @Resource
    DeviceStateService deviceStateService;

    @Override
    public void process(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        Channel channel = ctx.channel();
        String username = msg.payload().userName();
        byte[] password = msg.payload().passwordInBytes();

        if (!checkAccess(username, password)) {
            channel.writeAndFlush(genDeny());
            channel.close();
            return;
        }

        ChanUtil.setClientId(channel, msg.payload().clientIdentifier());
        ChanUtil.setUsername(channel, username);

        boolean cleanSession = msg.variableHeader().isCleanSession();
        channel.writeAndFlush(genOk(!cleanSession));

        deviceStateService.markOnline(username);
    }

    public void processOther(ChannelHandlerContext ctx, MqttMessage msg) {
        ctx.channel().writeAndFlush(genDeny());
        ctx.channel().close();
    }

    private MqttConnAckMessage genOk(boolean sessionPresent) {
        return new MqttConnAckMessage(
                new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, sessionPresent)
        );
    }

    private MqttConnAckMessage genDeny() {
        return new MqttConnAckMessage(
                new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD, false)
        );
    }

    private boolean checkAccess(String username, byte[] passwordByte) {
        if (username == null || passwordByte == null) {
            return false;
        }
        String password = new String(passwordByte);
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        DeviceTuple2 d2 = DeviceTuple2.of(username);
        DeviceDO device = deviceDAO.findByDeviceNameAndProductKey(d2.getName(), d2.getPk()).orElse(null);

        if (device == null) {
            return false;
        }
        if (password.equals(device.getDeviceSecret())) {
            log.info("secret right");
        } else {
            log.error("secret error");
        }
        return true;
    }

}
