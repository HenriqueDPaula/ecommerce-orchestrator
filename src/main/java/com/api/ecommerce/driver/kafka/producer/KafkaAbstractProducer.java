package com.api.ecommerce.driver.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaAbstractProducer{

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final String topic;
  private final String groupId;

  public KafkaAbstractProducer(KafkaTemplate<String, Object> kafkaTemplate, String topic, String groupId) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
    this.groupId = groupId;
  }

  /**
   * Send to kafka with listener
   * @param e abstract message
   */
  protected void send(Object message) {
    this.kafkaTemplate.send(this.topic, this.groupId, message);

  }
}
