package com.lhc.datamodel.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Table(name = "Visiteurs")
@Entity(name = "Visiteurs")
public class Visiteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String reference;

}
