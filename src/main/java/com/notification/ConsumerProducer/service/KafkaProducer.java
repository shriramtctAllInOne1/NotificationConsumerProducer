package com.notification.ConsumerProducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <h2>BrokerProducerService</h2>
 */
@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("unable to send message='{}'", message, ex);
            }
        });
    }
}