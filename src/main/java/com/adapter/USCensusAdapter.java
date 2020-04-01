package com.adapter;

import com.dto.USCensusCSV;
import com.dao.CensusDAO;
import com.exception.StateCensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}
