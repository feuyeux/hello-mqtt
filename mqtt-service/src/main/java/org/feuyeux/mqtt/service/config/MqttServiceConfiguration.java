package org.feuyeux.mqtt.service.config;

import org.feuyeux.mqtt.config.MqttSettings;
import org.feuyeux.mqtt.service.TempMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class MqttServiceConfiguration {
    @Bean
    public IntegrationFlow mqttInbound(MqttSettings settings,
                                       MqttPahoClientFactory mqttClientFactory,
                                       TempMessageHandler tempMessageHandler) {
        String topic = settings.getTopic();
        MqttPahoMessageDrivenChannelAdapter messageProducer = new MqttPahoMessageDrivenChannelAdapter("mqtt-service", mqttClientFactory, topic);
        return IntegrationFlows.from(messageProducer)
                .handle(tempMessageHandler)
                .get();
    }
}
