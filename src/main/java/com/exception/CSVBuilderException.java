package com.exception;

public class CSVBuilderException extends Exception {

    public StateCensusAnalyserException.CensusAnalyserCustomExceptionType type;

    //ENUM CLASS
    enum Exception_Type {
        UNABLE_TO_PARSE;
    }
    //CONSTRUCTOR
    public CSVBuilderException(StateCensusAnalyserException.CensusAnalyserCustomExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}