package com.lhc.mapper.mapperHandler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class VisitorsMapperTest {

    @Test
    public void mapListVisitorIntoString() {

        VisitorsMapper visitorsMapper = new VisitorsMapper();
        List<String> stringList = new ArrayList<>();
        stringList.add("Gauthier_1");
        stringList.add("Gauthier_2");
        stringList.add("Gauthier_3");
        stringList.add("Gauthier_4");

        String actual_1 = visitorsMapper.mapListVisitorIntoString(stringList);
        assertThat(actual_1).contains("Gauthier_4");

        List<String> actual_2 = visitorsMapper.mapStringVisitorIntoList(actual_1);

        assertThat(actual_2.size()).isEqualTo(4);

    }

}