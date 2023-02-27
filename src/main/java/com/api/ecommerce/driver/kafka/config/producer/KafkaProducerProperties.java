package com.api.ecommerce.driver.kafka.config.producer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(
    prefix = "spring.kafka.producer"
)
@Data
@NoArgsConstructor
public class KafkaProducerProperties {

  private String bootstrapServer;
  private String acksConfig;
  private String retriesConfig;
  private final Class<?> keySerializer = StringSerializer.class;
  private final Class<?> valueSerializer = JsonSerializer.class;
}
