package com.centaris.razemnazakupy.controllers;

import com.centaris.razemnazakupy.model.LikedPersonRQ;
import com.centaris.razemnazakupy.notifications.NotificationService;
import com.centaris.razemnazakupy.persistence.PersistenceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikedController {
    private final PersistenceService persistenceService;
    private final NotificationService notificationService;

    public LikedController(PersistenceService persistenceService, NotificationService notificationService) {
        this.persistenceService = persistenceService;
        this.notificationService = notificationService;
    }

    @PostMapping("/like")
    public void likePerson(@RequestBody LikedPersonRQ likedPersonRQ) {
        persistenceService.personLikedBy(likedPersonRQ.person().id(), likedPersonRQ.id());
        notificationService.notifyOther(likedPersonRQ.id(), likedPersonRQ.liked(), likedPersonRQ.person());
    }
}
