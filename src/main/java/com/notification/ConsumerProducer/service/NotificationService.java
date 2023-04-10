package com.notification.ConsumerProducer.service;

import com.notification.ConsumerProducer.model.Notification;

public interface NotificationService {

    /**
     * Send notification
     * @param notification model of notification
     */
    void send(Notification notification);

}
