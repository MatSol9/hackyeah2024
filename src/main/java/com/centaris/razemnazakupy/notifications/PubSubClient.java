package com.centaris.razemnazakupy.notifications;

import com.centaris.razemnazakupy.model.LikedNotification;

public interface PubSubClient {

    void send(LikedNotification likedNotification);
}
