package ru.maksimov.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToMany
    @JoinTable(
            name = "actors_movies",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<Movie> movies;


    public Actor() {
    }

    public Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addMovie(Movie movie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return getId() == actor.getId() && getAge() == actor.getAge() && Objects.equals(getName(), actor.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge());
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", movies=" + movies +
                '}';
    }
}
