package org.feuyeux.mqtt.service.config;

import org.feuyeux.mqtt.config.MqttSettings;
import org.feuyeux.mqtt.service.TempMessageHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;

//TODO MQTT v3.1 protocol - MQTT v5
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
        MqttPahoMessageDrivenChannelAdapter messageProducer =
                new MqttPahoMessageDrivenChannelAdapter("mqtt-service", mqttClientFactory, topic);
        //new Mqttv5PahoMessageDrivenChannelAdapter("mqtt-service", mqttClientFactory, topic);
        return IntegrationFlow.from(messageProducer)
                .handle(tempMessageHandler)
                .get();
    }
}
