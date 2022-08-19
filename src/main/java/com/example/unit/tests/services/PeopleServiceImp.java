package com.example.unit.tests.services;

import com.example.unit.tests.models.Person;
import com.example.unit.tests.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PeopleServiceImp implements PeopleService {

    @Autowired
    private PersonRepository personRepository;

    public PeopleServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return this.personRepository.save(person);
    }
}