package com.centaris.razemnazakupy.listeners;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PersonLikedSubscriber {

    @Value("${gcp.project-id}")
    private String projectId;

    @Value("${gcp.pubsub.person-liked-subscription-id}")
    private String subscriptionId;

    private Subscriber subscriber;

    @PostConstruct
    public void init() {
        subscribeAsync();
    }

    public void subscribeAsync() {
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);

        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> {
                    System.out.println("Message Id: " + message.getMessageId());
                    System.out.println("Message Data: " + message.getData().toStringUtf8());

                    consumer.ack();
                };

        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
        } catch (Exception e) {
            System.err.println("Error starting the Pub/Sub subscriber: " + e.getMessage());
        }
    }

    @PreDestroy
    public void shutdown() {
        if (subscriber != null) {
            try {
                subscriber.stopAsync().awaitTerminated(5, TimeUnit.SECONDS);
                System.out.println("Subscriber successfully shut down.");
            } catch (TimeoutException e) {
                System.err.println("Error shutting down subscriber: " + e.getMessage());
            }
        }
    }
}