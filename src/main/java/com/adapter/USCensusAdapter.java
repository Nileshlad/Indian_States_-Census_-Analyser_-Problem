package com.adapter;

import com.bridgelabz.census.analyser.CensusDAO;
import com.bridgelabz.census.analyser.USCensusCSV;
import com.exception.StateCensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}
