package com.bridgelabz.census.analyser;

import com.exception.CSVBuilderException;
import com.exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    //CONSTANT
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndiaCensusData(String csvFilePath) throws StateCensusAnalyserException {
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            List<IndianStateCode> csvFileList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
            return csvFileList.size();
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
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

    public static void main(String[] args) {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}