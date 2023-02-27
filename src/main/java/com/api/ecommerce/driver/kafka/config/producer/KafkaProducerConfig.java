package com.api.ecommerce.driver.kafka.config.producer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaProducerConfig {

  private KafkaProducerProperties kafkaProducerProperties;

  public KafkaProducerConfig(KafkaProducerProperties kafkaProducerProperties) {
    this.kafkaProducerProperties = kafkaProducerProperties;
  }

  @Bean
  public ProducerFactory<String, Object> configureProducer() {
    return new DefaultKafkaProducerFactory<>(Map.of(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrapServer(),
        ProducerConfig.ACKS_CONFIG, kafkaProducerProperties.getAcksConfig(),
        ProducerConfig.RETRIES_CONFIG, kafkaProducerProperties.getRetriesConfig(),
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProducerProperties.getKeySerializer().getName(),
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProducerProperties.getValueSerializer().getName()
    ));
  }

  @Bean
  public KafkaTemplate kafkaTemplate() {
    return new KafkaTemplate<>(configureProducer());
  }
}
