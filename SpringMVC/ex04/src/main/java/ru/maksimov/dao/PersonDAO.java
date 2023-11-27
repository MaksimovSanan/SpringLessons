package ru.maksimov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maksimov.models.Person;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny();
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?", new Object[]{email},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
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

    public void batchUpdate(int quantity) {
        List<Person> personList = new ArrayList<>();
        for(int i = 0; i < quantity; ++i) {
            personList.add(new Person(i, "name" + i, 30, "email" + i + "@mail.ru"));
        }
        jdbcTemplate.batchUpdate("INSERT INTO users(name, age, email) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setString(1, personList.get(i).getName());
                        preparedStatement.setInt(2, personList.get(i).getAge());
                        preparedStatement.setString(3, personList.get(i).getEmail());
                    }
                    @Override
                    public int getBatchSize() {
                        return personList.size();
                    }
                }
        );
    }

    public void batchDelete(int begin, int end) {
        jdbcTemplate.batchUpdate("DELETE FROM users WHERE id = ?",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, i + begin);
                    }

                    @Override
                    public int getBatchSize() {
                        return end - begin;
                    }
                }
        );
    }
}
