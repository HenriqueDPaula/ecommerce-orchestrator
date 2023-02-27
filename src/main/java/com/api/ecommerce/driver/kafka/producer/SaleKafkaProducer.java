package com.api.ecommerce.driver.kafka.producer;

import com.api.ecommerce.controller.sale.dto.SaleRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SaleKafkaProducer extends KafkaAbstractProducer {

  private KafkaTemplate<String, Object> kafkaTemplate;

  @Autowired
  public SaleKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, @Value("${spring.kafka.producer.topic}") String topic,
                           @Value("${spring.kafka.producer.groupId}") String groupId) {
    super(kafkaTemplate, topic, groupId);
  }

  public void execute(SaleRequest saleRequest) {
    this.send(saleRequest);
  }
}
