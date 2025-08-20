package com.codingshuttle.linkedin.notification_service.service;

import com.codingshuttle.linkedin.notification_service.consumer.PostsServiceConsumer;
import com.codingshuttle.linkedin.notification_service.entity.Notification;
import com.codingshuttle.linkedin.notification_service.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendNotification {
    private static final Logger log = LoggerFactory.getLogger(SendNotification.class);
    private final NotificationRepository notificationRepository;

    public SendNotification(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void send(Long userId,String message){
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);

        notificationRepository.save(notification);
        log.info("Notification saved for user: {}", userId);
    }
}
