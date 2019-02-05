package com.lhc.datamodel.entities.image;

import com.lhc.datamodel.entities.competition.Competition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "Images_Competition")
@Entity(name = "Images_Competition")
public class ImageCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String asBase64;

    @Column(unique=true)
    private String competition_ref;

    @OneToOne(fetch = FetchType.LAZY)
    private Competition competition;

    private ImageCompetition() {
    }

    private ImageCompetition(Long id, String asBase64, String competition_ref, Competition competition) {
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
