package com.bridgelabz.census.analyser.test;

import com.bridgelabz.census.analyser.IndianStateCode;
import com.bridgelabz.census.analyser.StateCensusAnalyser;
import com.exception.StateCensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cv";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongDelimiterStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongHeaderStateCensusData.csv";
    private static final String INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StatCode.csv";
    private static final String WRONG_DELIMITER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongDelimiterStateCodeData.csv";
    private static final String WRONG_HEADER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongHeaderStateCodeData.csv";

    //OBJECT
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    //TEST CASE 1.1
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws StateCensusAnalyserException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    //TEST CASE 1.2
    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldThrowException() throws StateCensusAnalyserException {
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
        int numberOfRecord = censusAnalyserProblem.loadIndianStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
        Assert.assertEquals(37, numberOfRecord);
    }

    //TEST CASE 2.2
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproper_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    //TEST CASE 2.3
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperType_ShouldThrowException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_DELIMITER_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 2.4
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperHeader_ShouldThrowException() throws StateCensusAnalyserException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_HEADER_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 3.1
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnFirstSortedResult() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData(STATE_CENSUS_DATA_PATH);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 3.2
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData(STATE_CENSUS_DATA_PATH);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 5.1
    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndianStateCodeData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationWiseSortedCensusData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedCensusData,IndianStateCode[].class);
            Assert.assertEquals(199812341, stateCSV[0].getPopulation());
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
}