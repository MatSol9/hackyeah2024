package com.centaris.razemnazakupy.model;

import java.util.List;

public record InitialLoadRS(List<Person> potentialMatches,
                            List<Person> likedPeople,
                            List<Person> likingPeople) {
}
