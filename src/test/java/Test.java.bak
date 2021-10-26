import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;

public class Test implements MqttCallback {

    public static void main(String[] args) throws MqttException {
        MqttClient client = new MqttClient("tcp://localhost:1883", "clientId");

        MqttConnectOptions options = new MqttConnectOptions();
        options.setConnectionTimeout(3000);
        options.setKeepAliveInterval(20);
        options.setCleanSession(true);
        options.setUserName("test");
        options.setPassword("test".toCharArray());

        client.connect(options);
        client.subscribe("test-topic");

        client.publish("test-topic", new MqttMessage("hehe".getBytes(StandardCharsets.UTF_8)));

        boolean connected = client.isConnected();
        System.out.println("connected = " + connected);
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

}
