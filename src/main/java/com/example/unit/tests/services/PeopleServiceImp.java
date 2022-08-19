package com.example.unit.tests.services;

import com.example.unit.tests.models.Person;
import com.example.unit.tests.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Person> byId = this.personRepository.findById(id);

        byId.ifPresent(person -> this.personRepository.deleteById(person.getId()));
    }
}
