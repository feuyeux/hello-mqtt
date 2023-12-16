package io.emqx.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Slf4j
public class SubscribeSample {
    public static void main(String[] args) {

        String broker = "tcp://localhost:1883";
        String topic = "mqtt/test";
        String clientId = "java_subscribe_client";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
            // 设置连接参数
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
//            options.setUserName(username);
//            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // 设置回调
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    log.info("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    log.info("topic: " + topic);
                    log.info("Qos: " + message.getQos());
                    log.info("message content: " + new String(message.getPayload()));

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    log.info("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            client.subscribe(topic, qos);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
