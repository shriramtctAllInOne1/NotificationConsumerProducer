package com.notification.ConsumerProducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.ConsumerProducer.model.Notification;
import com.notification.ConsumerProducer.exception.ConsumerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "testTopic", groupId = "notification-group-id", containerFactory = "kakfaListenerContainerFactory")
    public void listenSenderEmail(String data) {

        Notification notification = fromJson(data, Notification.class);
        log.info("Consumed message: " + data);
        template.convertAndSend("/topic/testTopic", notification);

    }

    /**
     * Convert json to Object
     * @param json String json value
     * @param clazz Instances of the class
     * @param <T> Object Class
     * @return Object class
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new ConsumerException(e.getMessage());
        }
    }
}