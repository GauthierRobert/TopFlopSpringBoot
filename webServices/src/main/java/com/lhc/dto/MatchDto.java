package com.lhc.dto;


import com.lhc.datamodel.entities.SystemData;
import com.lhc.datamodel.entities.competition.embedded.MatchDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.lhc.datamodel.entities.SystemData.systemData;

@Data
public class MatchDto {

    private SystemData systemData;

    private MatchDetails details;
    
    private String competition_ref;

    private String date;

    private String status;

    private List<String> visitors;

    private List<BallotDto> ballotDtos;

    private MatchDto(String competition_ref, MatchDetails details, String createdBy, List<String> visitors) {
        this.systemData = systemData(null, createdBy);
        this.competition_ref = competition_ref;
        this.details = details;
        this.visitors = visitors;
    }

    private MatchDto() {
    }

    public static MatchDto matchDto(String competition_ref, MatchDetails details, String createdBy, List<String> visitors){
        return new MatchDto(competition_ref, details, createdBy, visitors);
    }

    public static MatchDto matchDto(){
        return new MatchDto();
    }



}
