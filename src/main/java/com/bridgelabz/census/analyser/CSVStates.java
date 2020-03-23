package com.bridgelabz.census.analyser;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CSVStates<recordCount> {
    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws StateCensusAnalyserException {
        //LOCAL VARIABLE
        int recordCount = 0;
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
        } catch (FileNotFoundException e) {
                throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND,"File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordCount1;
    }
}