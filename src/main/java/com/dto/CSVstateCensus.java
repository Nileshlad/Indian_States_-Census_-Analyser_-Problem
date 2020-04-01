package com.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVstateCensus {
    public CSVstateCensus(String state, String stateCode, int population, Double areaInSqKm, Double densityPerSqKm) {
        this.state = state;
        this.stateCode = stateCode;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    public CSVstateCensus() {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(Double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public Double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(Double densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    private int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private Double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private Double densityPerSqKm;

    private String stateCode = new IndianStateCode().getStateCode();

    @Override
    public String toString() {
        return "CSVstateCensus{" +
                "state='" + state + '\'' +
                ", population='" + population + '\'' +
                ", areaInSqKm='" + areaInSqKm + '\'' +
                ", densityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
