package com.bridgelabz.census.analyser;

import com.exception.CSVBuilderException;
import com.exception.StateCensusAnalyserException;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    //CONSTANT
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    List<CSVstateCensus> csvFileList = null;
    List<IndianStateCode> stateCodeList = null;
    Map<String, CSVstateCensus> stateCensusMap = null;
    Map<String, IndianStateCode> csvStateCodeMap = null;

    public StateCensusAnalyser() {
        this.stateCensusMap = new HashMap<>();
        this.csvStateCodeMap = new HashMap<>();
    }

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndiaCensusData(String csvFilePath) throws StateCensusAnalyserException {
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCode.class);
            while (stateCensusIterator.hasNext()) {
                IndianStateCode stateCensus = stateCensusIterator.next();
                this.stateCensusMap.put(stateCensus.getState(), stateCensus);
                csvFileList = stateCensusMap.values().stream().collect(Collectors.toList());
            }
            return stateCensusMap.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws StateCensusAnalyserException {
        //LOCAL VARIABLE
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            List<IndianStateCode> csvFileList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
            return csvFileList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "delimiter and header");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }

    //METHOD TO GET EXTENSION OF CSV FILE
    private static String getFileExtension(String file) {
        String extension = "";
        try {
            if (file != null) {
                extension = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    //GENERIC METHOD TO GET CSV ITERATOR
    private <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<E> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }

    //TO COUNT THE RECORDS
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    public String getSortedCensusStateData() throws StateCensusAnalyserException {
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        Comparator<CSVstateCensus> stateCodeComparator = Comparator.comparing(indiaCensusCSV -> indiaCensusCSV.getState());
        this.sort(stateCodeComparator, csvFileList);
        String sortedStateCodeJson = new Gson().toJson(csvFileList);
        return sortedStateCodeJson;
    }

    public String getSortedStateCodeData() throws StateCensusAnalyserException {
        if (stateCodeList == null || stateCodeList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census state code data not found");
        }

        Comparator<IndianStateCode> stateCodeComparator = Comparator.comparing(stateCode -> stateCode.getStateCode());
        this.sort(stateCodeComparator, stateCodeList);
        String sortedStateCodeJson = new Gson().toJson(stateCodeList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT CSV DATA
    private <T> void sort(Comparator<T> csvComparator, List<T> csvList) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                T census1 = csvList.get(j);
                T census2 = csvList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }

            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}