package org.transformers;

import io.confluent.developer.User;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.opensmartgridplatform.adapter.kafka.MeterReading;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
//@CommonsLog(topic = "Consumer Logger")
public class Consumer {

  @Value("${topic.name}")
  private String topicName;

  @KafkaListener(topics = "meter-reading-internal-temperature", groupId = "group_id")
  public void consume(ConsumerRecord<String, MeterReading> record) {
    System.out.println(String.format("Consumed message -> %s", record.value()));
    //log.info(String.format("Consumed message -> %s", record.value()));
  }

}
