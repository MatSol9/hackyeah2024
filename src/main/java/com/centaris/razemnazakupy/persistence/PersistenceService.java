package com.centaris.razemnazakupy.persistence;

import com.centaris.razemnazakupy.model.MatchCriteria;
import com.centaris.razemnazakupy.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistenceService {
    public void personLikedBy(String likedPersonId, String userId) {

    }

    public List<Person> getPeopleMatchingCriteria(MatchCriteria matchCriteria) {
        return List.of();
    }

    public List<Person> getPeopleLikedBy(String personId) {
        return List.of();
    }

    public List<Person> getPeopleLiking(String personId) {
        return List.of();
    }
}
