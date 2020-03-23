package com.bridgelabz.census.analyser.test;

import com.bridgelabz.census.analyser.CSVStates;
import com.bridgelabz.census.analyser.StateCensusAnalyser;
import com.bridgelabz.census.analyser.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    //CONSTANT

    private static final String STATE_CENSUS_DATA_PATH = "./src/test/rescue/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/rescue/StateCensusData.csv";
    private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cv";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongDelimiterStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "C:./src/test/resources/WrongHeaderStateCensusData.csv";
    private static final String INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StateCode.csv";
    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();
    CSVStates csvStates = new CSVStates();

    //TEST CASE 1.1
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws StateCensusAnalyserException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    //TEST CASE 1.2
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecordWrong_ThrowCustomException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    //TEST CASE 1.3
    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    //TEST CASE 1.4
    @Test
    public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 1.5
    @Test
    public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 2.1
    @Test
    public void givenIndianStateCodeCsvFile_WhenProper_ShouldReturnCorrectRecordCount() throws StateCensusAnalyserException {
        int numberOfRecord = csvStates.loadIndianStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
        Assert.assertEquals(37, numberOfRecord);
    }
}
