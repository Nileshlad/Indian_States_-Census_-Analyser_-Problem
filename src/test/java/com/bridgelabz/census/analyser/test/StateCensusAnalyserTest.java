package com.bridgelabz.census.analyser.test;


import com.dto.CSVstateCensus;
import com.service.StateCensusAnalyser;
import com.dao.CensusDAO;
import com.dto.IndianStateCode;
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


    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws StateCensusAnalyserException {
        StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
        int numberOfRecord = censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenProper_ShouldReturnCorrectRecordCount() throws StateCensusAnalyserException {
        StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
        int numberOfRecord = censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_INFORMATION_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproper_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_TYPE_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_DELIMITER_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(WRONG_HEADER_INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATE);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATE);
            CSVstateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVstateCensus[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenImproperFile_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATE);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            int numberOfRecord = censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("AP", stateCSV[0].getStateCode());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("WB", stateCSV[28].getStateCode());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenImproperFile_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH, WRONG_INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData, IndianStateCode[].class);
            Assert.assertEquals("WB", stateCSV[28].getStateCode());
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Rajasthan", csvStateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheUSStateCensusData_WhenProper_ShouldReturnCorrectRecordCount() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            int numberOfRecords = censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheUSStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheUSStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();

        }
    }

    @Test
    public void givenTheUSStateCensusData_WhenSortedOnStateArea_ShouldReturnSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSAndIndiaStateCensusData_WhenSortedPopulationAndDensity_ShouldReturnSortedList_2() throws StateCensusAnalyserException {
        try {
            StateCensusAnalyser usCensusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            usCensusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedUSCensusData = usCensusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] UScensusCSV = new Gson().fromJson(sortedUSCensusData, CensusDAO[].class);
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(true, (String.valueOf(censusCSV[0].densityPerSqKm)).compareToIgnoreCase(String.valueOf(UScensusCSV[0].densityPerSqKm)) < 0);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }


}
