package com.lhc.datamodel.entities.competition;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatchTest {

    @Test
    public void addVisitors() {

        Match match = Match.match();
        match.setVisitors("GTTED § ");
        match.addVisitors("zrtzetert");
        match.addVisitors("ddghdg");

        System.out.println(match.getVisitors());

    }
}