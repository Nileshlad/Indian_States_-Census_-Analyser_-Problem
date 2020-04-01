package com.dao;

import com.dto.CSVstateCensus;
import com.dto.USCensusCSV;
import com.service.StateCensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public int population;
    public Double areaInSqKm;
    public double densityPerSqKm;
    public int tin;
    public int srNo;
    public String name;
    public String state;
    public String stateCode;


    public CensusDAO(CSVstateCensus csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.areaInSqKm = csvStateCensus.getAreaInSqKm();
        this.densityPerSqKm = csvStateCensus.getDensityPerSqKm();
    }

    public CensusDAO(IndianStateCode indianStateCode) {
        this.srNo=indianStateCode.getSrNo();
        this.state=indianStateCode.getState();
        this.name=indianStateCode.getName();
        this.tin=indianStateCode.getTin();
        this.stateCode = indianStateCode.getStateCode();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.getState();
        areaInSqKm = (double) usCensusCSV.getTotalArea();
        densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        population = usCensusCSV.getPopulation();
    }

    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyser.SORTING_MODE mode) {
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.DENSITY))
            return Comparator.comparing(census -> census.densityPerSqKm);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.AREA))
            return Comparator.comparing(census -> census.areaInSqKm);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new IndianStateCode(state, stateCode, population, areaInSqKm, densityPerSqKm);
        return new USCensusCSV(stateCode, state, population, areaInSqKm, population);
    }
}