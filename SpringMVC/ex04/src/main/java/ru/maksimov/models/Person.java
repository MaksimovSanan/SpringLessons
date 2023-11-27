package ru.maksimov.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    int id;
    @NotEmpty(message = "Name should not be empty")
    String name;
    @Min(value = 0, message = "Age should be greater than 0")
    Integer age;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "Email should be valid")
    String email;

    public Person(){}

    public Person(int id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
