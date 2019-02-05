package com.lhc.datamodel.entities.competition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Table(name = "Trainings")
@Entity(name = "Trainings")
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne
    private Competition competition;

    private Training(Long id, String reference, LocalDate date, LocalTime time, Competition competition) {
        this.id = id;
        this.reference = reference;
        this.date = date;
        this.time = time;
        this.competition = competition;
    }

    public static Training training(String reference, LocalDate date, LocalTime time, Competition competition){
        return new Training(null, reference, date, time, competition);
    }
}
