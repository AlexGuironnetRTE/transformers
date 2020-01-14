package org.transformers.kafka.producer;

import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
//@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name}")
  private String TOPIC;

  private final KafkaTemplate<String, User> kafkaTemplate;

  @Autowired
  public Producer(KafkaTemplate<String, User> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  void sendMessage(User user) {
    this.kafkaTemplate.send(this.TOPIC, user.getName(), user);
    System.out.println(String.format("Produced user -> %s", user));
    //log.info(String.format("Produced user -> %s", user));
  }
}
