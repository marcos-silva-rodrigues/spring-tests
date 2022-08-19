package com.example.unit.tests.services;

import com.example.unit.tests.models.Person;

import java.util.List;

public interface PeopleService {

    public Person create(Person person);

    public List<Person> findAll();
}
