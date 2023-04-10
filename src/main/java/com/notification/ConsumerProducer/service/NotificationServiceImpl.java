package com.notification.ConsumerProducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.ConsumerProducer.model.Notification;
import com.notification.ConsumerProducer.exception.ProducerException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final KafkaProducer brokerProducerService;
    private final Environment env;

    public NotificationServiceImpl(KafkaProducer brokerProducerService, Environment env) {
        this.brokerProducerService = brokerProducerService;
        this.env = env;
    }

    @Override
    public void send(Notification notification) {
        brokerProducerService.sendMessage(env.getProperty("producer.kafka.topic-name"), toJson(notification));
    }


    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new ProducerException(e.getMessage());
        }
    }
}