package com.adapter;

import com.bridgelabz.census.analyser.CsvBuilderFactory;
import com.bridgelabz.census.analyser.IcsvBuilder;
import com.service.StateCensusAnalyser;
import com.dao.CensusDAO;
import com.dto.IndianStateCode;
import com.exception.CSVBuilderException;
import com.exception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    //CONSTANT FOR REGEX PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException {
        Map<String, CensusDAO> censusMap = super.loadCensusData(IndianStateCode.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusMap;
        return this.loadStateCodeCensusData(censusMap, csvFilePath[1]);
    }

    //FUNCTION TO LOAD US CENSUS DATA
    private <E> Map<String, CensusDAO> loadStateCodeCensusData(Map<String, CensusDAO> censusMap, String csvFilePath) throws StateCensusAnalyserException {

        String extension = StateCensusAnalyser.getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCode.class);
            Iterable<IndianStateCode> csvIterable = () -> stateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.getState()) != null)
                    .forEach(csvState -> censusMap.get(csvState.getState()).stateCode = csvState.getStateCode());
            return censusMap;
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

}
