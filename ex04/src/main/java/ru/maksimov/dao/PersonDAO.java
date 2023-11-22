package ru.maksimov.dao;

import org.springframework.stereotype.Component;
import ru.maksimov.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNTER = 0;
    private final List<Person> people = new ArrayList<>();

    {
        people.add(new Person(++PEOPLE_COUNTER, "ABOBA", 38, "ABOBA@mail.ru"));
        people.add(new Person(++PEOPLE_COUNTER, "ABOBUS", 48, "ABOBUS@abobusmail.ru"));
        people.add(new Person(++PEOPLE_COUNTER, "VOVA",40, "YESIAMVOVA@VOVAmail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNTER);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
