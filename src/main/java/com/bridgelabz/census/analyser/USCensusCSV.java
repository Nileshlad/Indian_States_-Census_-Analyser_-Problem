package com.bridgelabz.census.analyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = " State Id", required = true)
    private String stateId;
    @CsvBindByName(column = "State", required = true)
    private String state;
    @CsvBindByName(column = "Population", required = true)
    private String population;
    @CsvBindByName(column = "Total area", required = true)
    private String totalArea;
    @CsvBindByName(column = "Population Density", required = true)
    private double densityPerSqKm;

    public String getStateId() {
        return stateId;
    }

    public String getState() {
        return state;
    }

    public String getPopulation() {
        return population;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }
}