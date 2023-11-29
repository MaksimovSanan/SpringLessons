package ru.maksimov.models;

import javax.persistence.*;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;

    @Column(name = "school_number")
    private int schoolNumber;

    public School() {
    }

    public School(Director director, int schoolNumber) {
        this.director = director;
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getSchoolNUmber() {
        return schoolNumber;
    }

    public void setSchoolNUmber(int schoolNUmber) {
        this.schoolNumber = schoolNUmber;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", director=" + director.getName() +
                ", schoolNUmber=" + schoolNumber +
                '}';
    }
}
