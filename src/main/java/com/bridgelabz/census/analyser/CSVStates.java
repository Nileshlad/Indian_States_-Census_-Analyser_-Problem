package com.bridgelabz.census.analyser;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

public class CSVStates<recordCount> {
    //CONSTANT
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws StateCensusAnalyserException {
        //LOCAL VARIABLE
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE,extension))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE,"No such a type");
        int recordCount1 = recordCount;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<IndianStateCode> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianStateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianStateCode> statesCSVIterator = csvToBean.iterator();
            while (statesCSVIterator.hasNext()) {
                IndianStateCode censusCSV = statesCSVIterator.next();
                ++recordCount1;
                System.out.print("SrNo: " + censusCSV.getSrNo() + ", ");
                System.out.print("state: " + censusCSV.getState() + ", ");
                System.out.print("Name: " + censusCSV.getName() + ", ");
                System.out.print("TIN: " + censusCSV.getTin() + ", ");
                System.out.print("StateCode: " + censusCSV.getStateCode() + ", ");
                System.out.println();
            }
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER,"No such delimiter and header");
        }catch (NoSuchFileException e) {
                throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND,"File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordCount1;
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
}