package com.bridgelabz.census.analyser.test;

import com.bridgelabz.census.analyser.StateCensusAnalyser;
import org.junit.Assert;
        import org.junit.Test;

public class StateCensusAnalyserTest{

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29,numberOfRecord);
    }
}