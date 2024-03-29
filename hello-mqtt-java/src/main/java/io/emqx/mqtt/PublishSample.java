package io.emqx.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PublishSample {

    public static void main(String[] args) {

        String broker = "tcp://localhost:1883";
        String topic = "mqtt/test";
        String clientId = "java_publish_client";
        String content = "Hello MQTT";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
            // 连接参数
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置用户名和密码
//            options.setUserName(username);
//            options.setPassword(password.toCharArray());
            options.setKeepAliveInterval(60);
            options.setConnectionTimeout(60);
            // 连接
            client.connect(options);
            // 创建消息并设置 QoS
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            // 发布消息
            for (int i = 0; i < 10000; i++) {
                client.publish(topic, message);
                log.info("Message published");
                log.info("topic: {}", topic);
                log.info("message content: {}", content);
                TimeUnit.SECONDS.sleep(1);
            }
            // 断开连接
            client.disconnect();
            // 关闭客户端
            client.close();
        } catch (MqttException | InterruptedException e) {
            log.error("", e);
        }
    }
}
