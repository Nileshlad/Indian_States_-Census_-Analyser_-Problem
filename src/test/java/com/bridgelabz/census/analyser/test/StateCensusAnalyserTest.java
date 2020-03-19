package com.bridgelabz.census.analyser.test;

import com.bridgelabz.census.analyser.StateCensusAnalyser;
import com.bridgelabz.census.analyser.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/rescue/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/rescue/StateCensusData.csv";
    private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cv";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    //TEST CASE 1.1
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws StateCensusAnalyserException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    //TEST CASE 1.2
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecordWrong_ThrowCustomException() throws StateCensusAnalyserException{
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
            }catch (StateCensusAnalyserException e){
        Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_FILE_FOUND,e.type);
    }
    }

    //TEST CASE 1.3
    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() throws StateCensusAnalyserException{
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29,numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE,e.type);
        }
    }

}