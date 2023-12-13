package org.feuyeux.mqtt.sensor.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.feuyeux.mqtt.config.MqttSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

import java.util.UUID;

@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class MqttSensorConfiguration {

    @Bean
    public String mqttClientId() {
        return "temp-" + UUID.randomUUID().toString().replace("-", "");
    }

    @Bean
    public IntegrationFlow mqttOutboundFlow(MqttSettings settings,
                                            @Qualifier("mqttClientId") String mqttClientId,
                                            MqttPahoClientFactory mqttClientFactory) {
        String topic = settings.getTopic();
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(mqttClientId, mqttClientFactory);
        mqttPahoMessageHandler.setDefaultTopic(topic);
        return f -> f.handle(mqttPahoMessageHandler);
    }
}
