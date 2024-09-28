package com.centaris.razemnazakupy.notifications;

import com.centaris.razemnazakupy.model.LikedNotification;
import com.centaris.razemnazakupy.model.Person;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final PubSubClient pubSubClient;

    public NotificationService(PubSubClient pubSubClient) {
        this.pubSubClient = pubSubClient;
    }

    public void notifyOther(String id, boolean liked, Person person) {
        pubSubClient.send(new LikedNotification(id, liked, person));
    }
}
