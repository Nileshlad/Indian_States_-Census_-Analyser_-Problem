package com.dao;

<<<<<<< HEAD
import com.dto.CSVstateCensus;
import com.dto.IndianStateCode;

import com.service.StateCensusAnalyser;
import com.dto.CSVstateCensus;

import com.dto.USCensusCSV;

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
        this.srNo = indianStateCode.getSrNo();
        this.state = indianStateCode.getState();
        this.name = indianStateCode.getName();
        this.tin = indianStateCode.getTin();
        this.stateCode = indianStateCode.getStateCode();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.getState();
        areaInSqKm = (double) usCensusCSV.getTotalArea();
        densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        population = usCensusCSV.getPopulation();
    }

    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new CSVstateCensus(state, population, areaInSqKm, densityPerSqKm);
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new USCensusCSV(stateCode, state, population, areaInSqKm, population);
        return null;
    }
}