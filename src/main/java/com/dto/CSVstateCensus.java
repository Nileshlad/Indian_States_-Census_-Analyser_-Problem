package com.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVstateCensus {
    public CSVstateCensus() {
    }

    ;

    public CSVstateCensus(String state, int population, Double areaInSqKm, Double densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
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
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public Double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public Double densityPerSqKm;

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
