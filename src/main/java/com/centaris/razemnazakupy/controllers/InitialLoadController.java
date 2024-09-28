package com.centaris.razemnazakupy.controllers;

import com.centaris.razemnazakupy.model.InitialLoadRQ;
import com.centaris.razemnazakupy.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InitialLoadController {
    @GetMapping("/initialLoad")
    public List<Person> getInitialLoad(@RequestBody InitialLoadRQ initialLoadRQ) {
        return List.of(new Person("Mocked", "Mocked", initialLoadRQ.gender()));
    }
}
