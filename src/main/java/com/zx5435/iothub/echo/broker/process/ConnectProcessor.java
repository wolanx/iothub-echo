package com.zx5435.iothub.echo.broker.process;

import com.zx5435.iothub.echo.model.dao.DeviceDAO;
import com.zx5435.iothub.echo.model.db.DeviceDO;
import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zx5435
 * a1p9xMXq5Nd1.iot-as-mqtt.cn-shanghai.aliyuncs.com
 */
@Service
public class ConnectProcessor implements IProcessor<MqttConnectMessage> {

    @Resource
    DeviceDAO deviceDAO;

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

        ChanUtil.setClientId(channel, "asd");
        ChanUtil.setUsername(channel, username);

        boolean cleanSession = msg.variableHeader().isCleanSession();
        channel.writeAndFlush(genOk(!cleanSession));
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
//        if (username.equals(password)) {
//            return true;
//        }
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        Optional<DeviceDO> device = deviceDAO.findByDeviceNameAndProductKey("iot-echo-903-913332", "a1p9xMXq5Nd");

        if (!device.isPresent()) {
            return false;
        }

        System.out.println("device = " + device);
        return true;
    }

}
