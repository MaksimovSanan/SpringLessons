package ru.maksimov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maksimov.models.Person;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(
                "INSERT INTO users(name, age, email) VALUES(?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getEmail()
        );
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(
                "UPDATE users SET name = ?, age = ?, email = ? WHERE id = ?",
                person.getName(),
                person.getAge(),
                person.getEmail(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?",
                id
        );
    }
}
