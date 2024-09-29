package com.centaris.razemnazakupy.listeners;

import com.centaris.razemnazakupy.model.LikedPersonRQ;
import com.centaris.razemnazakupy.notifications.NotificationService;
import com.centaris.razemnazakupy.persistence.PersistenceService;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

@Service
public class PersonLikedListener {

    private final PersistenceService persistenceService;
    private final NotificationService notificationService;

    public PersonLikedListener(PersistenceService persistenceService, NotificationService notificationService) {
        this.persistenceService = persistenceService;
        this.notificationService = notificationService;
    }

    @Bean
    public MessageChannel pubSubInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "person-liked-subscription");
        adapter.setOutputChannel(pubSubInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "pubSubInputChannel")
    public MessageHandler messageReceiver() {
        return message -> {
            LikedPersonRQ likedPersonRQ = (LikedPersonRQ) message.getPayload();
            persistenceService.personLikedBy(likedPersonRQ.person().id(), likedPersonRQ.id());
            notificationService.notifyOther(likedPersonRQ.id(), likedPersonRQ.liked(), likedPersonRQ.person());
        };
    }
}