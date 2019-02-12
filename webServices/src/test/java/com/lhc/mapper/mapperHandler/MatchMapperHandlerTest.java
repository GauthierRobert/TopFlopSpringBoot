package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.competition.Match;
import com.lhc.dto.MatchDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.dto.builder.MatchDtoBuilder.aMatchDto;
import static org.assertj.core.api.Assertions.assertThat;


public class MatchMapperHandlerTest {

    private static List<String> spectators = new ArrayList<>();
    private static String LINKEBEEK = "Linkebeek";
    private static String WATERLOO = "Waterloo";
    private static String GAUTHIER = "Gauthier";



    @Test
    public void createEntityFromDTO() {

        spectators.add(GAUTHIER);


        MatchDto matchDto = aMatchDto()
                .withSpectators(spectators)
                .inCompetiton(LINKEBEEK)
                .withHomeTeam(LINKEBEEK)
                .finallyScore(2)
                .withAwayTeam(WATERLOO)
                .finallyScore(3)
                .build();

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        Match actual = matchMapperHandler.createEntityFromDTO(matchDto);

        assertThat(actual.getDetails().getHomeTeam()).isEqualTo(LINKEBEEK);
        assertThat(actual.getVisitors()).contains(GAUTHIER);

        MatchDto actual_2 = matchMapperHandler.createDTOFromEntity(actual);

        assertThat(actual_2.getVisitors().size()).isEqualTo(1);
    }
}