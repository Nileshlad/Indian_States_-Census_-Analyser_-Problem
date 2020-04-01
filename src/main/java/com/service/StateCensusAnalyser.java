package com.service;

import com.adapter.CensusAdapter;
import com.adapter.CensusAdapterFactory;
import com.dao.CensusDAO;
import com.exception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    Map<String, CensusDAO> censusMap = new HashMap<>();
    COUNTRY country;

    //ENUM FOR COUNTRY SELECTION
    public enum COUNTRY {INDIA, US}

    //ENUM FOR Sorting MODE
    public enum SORTING_MODE {STATE, POPULATION, DENSITY, AREA, STATECODE}

    public StateCensusAnalyser() {
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

    //METHOD TO SORT STATE CENSUS DATA
    public String getSortCensusData(SORTING_MODE mode) throws StateCensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        ArrayList censusList = censusMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusList);
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
    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}