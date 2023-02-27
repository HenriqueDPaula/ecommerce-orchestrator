package com.api.ecommerce.driver.kafka.config.consumer;

import java.util.HashMap;
import java.util.Map;

import com.api.ecommerce.controller.sale.dto.SaleRequest;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Autowired
  private KafkaConsumerProperties kafkaConsumerProperties;

  @Bean
  public ConsumerFactory<String, SaleRequest> consumerFactory() {

    Map<String, Object> properties = new HashMap<>();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBootstrapServer());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId());
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  JsonDeserializer.class);
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConsumerProperties.getEnableAutoCommit());
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.getAutoOffsetReset());
//    properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaConsumerProperties.getMaxPollIntervalMs());

    return new DefaultKafkaConsumerFactory<>(
        properties, new StringDeserializer(),
        new JsonDeserializer<>(SaleRequest.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, SaleRequest> concurrentContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, SaleRequest> listener = new ConcurrentKafkaListenerContainerFactory<>();

    listener.setConsumerFactory(consumerFactory());
    listener.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    listener.getContainerProperties().setSyncCommits(Boolean.TRUE);

    return listener;
  }

}