package com.api.ecommerce.driver.kafka.consumer;

import com.api.ecommerce.controller.sale.dto.SaleRequest;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

public class KafkaMessageDeserializer extends ErrorHandlingDeserializer<SaleRequest>
    implements Deserializer<SaleRequest> {


}

