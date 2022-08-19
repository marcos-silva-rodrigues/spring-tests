package com.example.unit.tests.controllers;

import com.example.unit.tests.models.Person;
import com.example.unit.tests.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return this.peopleService.create(person);
    }

    @GetMapping
    public List<Person> findAll() {
        return this.peopleService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.peopleService.delete(id);
    }



}
