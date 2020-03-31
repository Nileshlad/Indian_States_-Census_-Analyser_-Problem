package com.dao;

import com.bridgelabz.census.analyser.CSVstateCensus;
import com.bridgelabz.census.analyser.IndianStateCode;
import com.bridgelabz.census.analyser.USCensusCSV;

public class CensusDAO {
    public String population;
    public String areaInSqKm;
    public String densityPerSqKm;
    public String tin;
    public String srNo;
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
        areaInSqKm = usCensusCSV.getTotalArea();
        densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        population = usCensusCSV.getPopulation();
    }
}