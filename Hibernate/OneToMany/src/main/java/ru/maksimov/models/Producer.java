package ru.maksimov.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producers")
public class Producer {
    @Id
    @Column(name="producer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int producer_id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @OneToMany(mappedBy = "producer")
    private List<Movie> movies;

    public Producer() {
    }

    public Producer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "producer_id=" + producer_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", movies=" + movies +
                '}';
    }
}
