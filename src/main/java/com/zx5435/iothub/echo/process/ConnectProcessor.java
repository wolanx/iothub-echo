package com.zx5435.iothub.echo.process;

import com.zx5435.iothub.echo.util.ChanUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.*;

/**
 * @author zx5435
 * a1p9xMXq5Nd1.iot-as-mqtt.cn-shanghai.aliyuncs.com
 */
public class ConnectProcessor implements IProcessor<MqttConnectMessage> {

    public static final ConnectProcessor INSTANCE = new ConnectProcessor();

    @Override
    public void process(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        Channel channel = ctx.channel();
        String username = msg.payload().userName();
        String password = new String(msg.payload().passwordInBytes());

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

    private boolean checkAccess(String username, String password) {
        if (username == null) {
            return false;
        }
        if (username.equals(password)) {
            return true;
        }
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return false;
    }

}
