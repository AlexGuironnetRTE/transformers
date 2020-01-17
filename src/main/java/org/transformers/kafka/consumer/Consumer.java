/* Copyright (c) 2020, RTE (http://www.rte-france.com)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */



package org.transformers.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.lfenergy.operatorfabric.cards.model.Card;
import org.lfenergy.operatorfabric.cards.model.CardCreationReport;
import org.opensmartgridplatform.adapter.kafka.MeterReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.transformers.operatorfabric.CardPublisher;

//TODO Make logs work (Slf4j)

@Service
//@CommonsLog(topic = "Consumer Logger")
public class Consumer {

  @Value("${topic.name}")
  private String topicName;

  @Autowired
  CardPublisher cardPublisher;

  @KafkaListener(topics = "meter-reading-internal-temperature")
  public void consume(ConsumerRecord<String, MeterReading> record) {
    System.out.println(String.format("Consumed message -> %s", record.value()));

    MeterReading meterReading = record.value();

    Card card = cardPublisher.createMeterReadingCard(meterReading);

    System.out.println("Created card: "+card.toString());

    CardCreationReport report = cardPublisher.publishCard(card);

    System.out.println("Card publication report: "+report.toString());

    //log.info(String.format("Consumed message -> %s", record.value()));
  }

}
