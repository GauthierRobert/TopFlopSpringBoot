package com.lhc.mapper.mapperHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitorsMapper {

    private static final String SEPARATOR = " § ";

    public VisitorsMapper() {

    }

    public String mapListVisitorIntoString(List<String> visitorsList) {

        String visitors = "";
        for (String visitor : visitorsList) {
            visitors += visitor + SEPARATOR;
        }

        return visitors;

    }


    public List<String> mapStringVisitorIntoList(String visitors) {

        String[] visitorsArray = visitors.split(SEPARATOR);

        return new ArrayList<>(Arrays.asList(visitorsArray));
    }
}
