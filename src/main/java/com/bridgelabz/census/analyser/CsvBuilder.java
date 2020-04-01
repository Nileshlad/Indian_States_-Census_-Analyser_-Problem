package com.bridgelabz.census.analyser;


import com.dto.IndianStateCode;
import com.exception.CSVBuilderException;
import com.exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CsvBuilder implements IcsvBuilder {

    //GENERIC METHOD TO GET CSV ITERATOR

    public <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws StateCensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "Wrong file");
        }
    }

    public <E> List<E> getCSVFileList(Reader reader, Class<IndianStateCode> csvClass) {
        try {
            return (List<E>) this.getCSVBean(reader, csvClass).parse();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) {
        try {
            return this.getCSVBean(reader, csvClass).iterator();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return null;
    }
}
