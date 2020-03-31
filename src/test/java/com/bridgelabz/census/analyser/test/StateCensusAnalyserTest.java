package com.bridgelabz.census.analyser.test;

import com.bridgelabz.census.analyser.CSVstateCensus;
import com.bridgelabz.census.analyser.CensusDAO;
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
    private static final String WRONG_TYPE_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StateCode.cv";
    private static final String WRONG_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StatCode.csv";
    private static final String WRONG_DELIMITER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongDelimiterStateCodeData.csv";
    private static final String WRONG_HEADER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongHeaderStateCodeData.csv";
    private static final String US_CENSUS_DATA_PATH = "./src/test/resources/USCensusData.csv";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws StateCensusAnalyserException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    //TEST CASE 1.2
    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    //TEST CASE 1.3
    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    //TEST CASE 1.4
    @Test
    public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 1.5
    @Test
    public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 2.1
    @Test
    public void givenIndianStateCodeCsvFile_WhenProper_ShouldReturnCorrectRecordCount() throws StateCensusAnalyserException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
        Assert.assertEquals(37, numberOfRecord);
    }

    //TEST CASE 2.2
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproper_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    //TEST CASE 2.4
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    //TEST CASE 3.1
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 3.2
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    //TEST CASE 3.3
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            CSVstateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVstateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 4.1
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 4.2
    @Test
    public void givenIndianCensusData_WhenImproperFile_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    //TEST CASE 4.3
    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnFirstSortedResult() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("AD", stateCSV[0].getStateCode());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 4.4
    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("WB", stateCSV[36].getStateCode());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    //TEST CASE 4.5
    @Test
    public void givenIndianStateCodeData_WhenImproperFile_ShouldThrowException() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("WB", stateCSV[36].getStateCode());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    //TEST CASE 5.1
    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341, censusCSV[0].population);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    //TEST CASE 6.1
    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationDensityWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, censusCSV[0].densityPerSqKm);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    //TEST CASE 7.1
    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getAreaWiseSortedCensusData();
            CensusDAO[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].areaInSqKm);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    //TEST CASE 7.2
    @Test
    public void givenTheUSStateCensusData_WhenProper_ShouldReturnCorrectRecordCount() {
        try {
            int numberOfRecords = censusAnalyserProblem.loadIndiaCensusData(US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }
}
