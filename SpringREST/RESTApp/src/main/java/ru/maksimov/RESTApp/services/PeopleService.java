package ru.maksimov.RESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.RESTApp.models.Person;
import ru.maksimov.RESTApp.repositories.PeopleRepository;
import ru.maksimov.RESTApp.util.exceptions.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        List<Person> people = peopleRepository.findAll();
        return people;
    }

    public Person findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }
    
    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(Person person) {
        peopleRepository.delete(person);
    }
}
