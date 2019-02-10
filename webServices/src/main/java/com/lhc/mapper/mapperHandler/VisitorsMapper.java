package com.lhc.mapper.mapperHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VisitorsMapper {

    private static final String SEPARATOR = " § ";

    public VisitorsMapper() {

    }

    public String mapListVisitorIntoString(List<String> visitorsList) {

        StringBuilder visitors = new StringBuilder();
        if(visitorsList!=null) {
            for (String visitor : visitorsList) {
                visitors.append(visitor).append(SEPARATOR);
            }
        }
        return visitors.toString();

    }


    public List<String> mapStringVisitorIntoList(String visitors) {
        if(visitors !=null) {
            String[] visitorsArray = visitors.split(SEPARATOR);
            return new ArrayList<>(Arrays.asList(visitorsArray));
        }
        return Collections.emptyList();
    }
}
