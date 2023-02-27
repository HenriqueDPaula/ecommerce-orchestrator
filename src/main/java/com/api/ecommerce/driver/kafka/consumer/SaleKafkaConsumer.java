package com.api.ecommerce.driver.kafka.consumer;

import com.api.ecommerce.controller.sale.dto.SaleRequest;
import com.api.ecommerce.usecase.saleauthorize.SaleService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SaleKafkaConsumer {

  private SaleService saleService;

  @Autowired
  public SaleKafkaConsumer(SaleService saleService) {
    this.saleService = saleService;
  }

  @KafkaListener(
      groupId = "${spring.kafka.consumer.groupId}",
      topics = "${spring.kafka.consumer.topic}",
      containerFactory = "concurrentContainerFactory"
  )
  public void consume(final ConsumerRecord<String, SaleRequest> consumerRecord, Acknowledgment ack) {
    try {
      log.info("m=consume l=RECEIVED topic={} offset={}", consumerRecord.topic(), consumerRecord.offset());
      this.saleService.processSaleAuthorize(consumerRecord.value());

      ack.acknowledge();
    } catch (Exception e) {
      log.error("m=consume l=CONSUMER_FAIL", e);
    }
  }
}
