package com.api.ecommerce.driver.kafka.config.consumer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(
    prefix = "spring.kafka.consumer"
)
@Data
@NoArgsConstructor
public class KafkaConsumerProperties {

  private String groupId;

  private String bootstrapServer;

  private String enableAutoCommit;

  private String autoOffsetReset;

  private String maxPollIntervalMs;

  private final Class<?> keySerializer = StringDeserializer.class;

  private final Class<?> valueSerializer = JsonDeserializer.class;

}