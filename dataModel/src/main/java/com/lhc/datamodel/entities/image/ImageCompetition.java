package com.lhc.datamodel.entities.image;

import com.lhc.datamodel.entities.competition.Competition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "Images_Competition")
@Entity(name = "Images_Competition")
public class ImageCompetition implements Serializable {

    public static String NO_IMAGE = "NO_IMAGE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "MEDIUMTEXT" , name = "asBase64")
    private String asBase64;

    @Column(unique=true)
    private String competition_ref;


    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Competition competition;

    private ImageCompetition() {
    }

    public ImageCompetition(Long id, String asBase64, String competition_ref, Competition competition) {
        this.id = id;
        this.asBase64 = asBase64;
        this.competition_ref = competition_ref;
        this.competition = competition;
    }

    public static ImageCompetition imageCompetition(String asBase64, String competition_ref, Competition competition){
        return new ImageCompetition(null, asBase64, competition_ref, competition);
    }

    public static ImageCompetition imageCompetition(){
        return new ImageCompetition();
    }

}
