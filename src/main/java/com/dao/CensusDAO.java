package com.dao;

import com.dto.CSVstateCensus;
import com.dto.IndianStateCode;
import com.dto.USCensusCSV;

public class CensusDAO {
    public int population;
    public int areaInSqKm;
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
        areaInSqKm = usCensusCSV.getTotalArea();
        densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        population = usCensusCSV.getPopulation();
    }
}