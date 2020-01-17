/* Copyright (c) 2020, RTE (http://www.rte-france.com)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */



package org.transformers;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//TODO Add tests

@SpringBootApplication
public class Application {

	@Value("${topic.name}")
	private String topicName;

	/*@Value("${topic.partitions-num}")
	private Integer partitions;

	@Value("${topic.replication-factor}")
	private short replicationFactor;*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	NewTopic moviesTopic() {
		return new NewTopic(topicName, 1, (short) 1);
	}
}
