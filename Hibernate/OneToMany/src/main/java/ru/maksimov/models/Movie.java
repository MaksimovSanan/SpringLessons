package ru.maksimov.models;


import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    @ManyToOne()
    @JoinColumn(name = "producer_id", referencedColumnName = "producer_id")
    private Producer producer;

    @Column(name = "name")
    String name;

    @Column(name = "year_of_production")
    int yearOfProduction;

    public Movie() {
    }

    public Movie(Producer producer, String name, int yearOfProduction) {
        this.producer = producer;
        this.name = name;
        this.yearOfProduction = yearOfProduction;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", producer=" + producer.name +
                ", name='" + name + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
