package com.centaris.razemnazakupy.controllers;

import com.centaris.razemnazakupy.model.InitialLoadRQ;
import com.centaris.razemnazakupy.model.InitialLoadRS;
import com.centaris.razemnazakupy.model.MatchCriteria;
import com.centaris.razemnazakupy.persistence.PersistenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialLoadController {
    private final PersistenceService persistenceService;

    public InitialLoadController(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    @GetMapping("/initialLoad")
    public InitialLoadRS getInitialLoad(@RequestBody InitialLoadRQ initialLoadRQ) {
        return new InitialLoadRS(persistenceService.getPeopleMatchingCriteria(
                new MatchCriteria(initialLoadRQ.gender())),
                persistenceService.getPeopleLikedBy(initialLoadRQ.id()),
                persistenceService.getPeopleLiking(initialLoadRQ.id()));
    }
}
