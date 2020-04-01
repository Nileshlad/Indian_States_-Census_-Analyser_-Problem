package com.service;

import com.adapter.CensusAdapter;
import com.adapter.CensusAdapterFactory;
import com.dao.CensusDAO;
import com.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    Map<String, CensusDAO> censusMap = new HashMap<>();
    COUNTRY country;
    public enum COUNTRY {INDIA, US}

    public StateCensusAnalyser() {

        this.censusMap = new HashMap<>();
    }

    public StateCensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    //GENERIC METHOD LOADING EVERY FILE DATA
    public int loadCensusData(String... csvFilePath) throws StateCensusAnalyserException {
        CensusAdapter censusLoader = CensusAdapterFactory.getCensusData(country);
        censusMap = censusLoader.loadCensusData(csvFilePath);
        return censusMap.size();
    }

    //METHOD TO GET EXTENSION OF CSV FILE
    public static String getFileExtension(String file) {
        String extension = "";
        try {
            if (file != null) {
                extension = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    //METHOD TO GET COUNT OF RECORDS
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    public String getSortedCensusStateData() throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    public String getSortedStateCodeData() throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census state code data not found");
        }
        Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(stateCodeComparator, censusList);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    public String getPopulationDensityWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA, "No census data");
        }
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.areaInSqKm);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT CSV DATA
    private void sortCSVData(Comparator<CensusDAO> csvComparator, List<CensusDAO> censusList) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                CensusDAO census1 = censusList.get(j);
                CensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}