package com.notification.ConsumerProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerProducerApplication.class, args);
	}

}
