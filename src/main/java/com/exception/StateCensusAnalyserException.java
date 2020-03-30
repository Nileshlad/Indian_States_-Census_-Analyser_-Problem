package com.exception;

public class StateCensusAnalyserException extends Exception {

    //CONSTANT
    public enum CensusAnalyserCustomExceptionType {
        FILE_NOT_FOUND,NO_SUCH_TYPE,WRONG_DELIMITER_OR_HEADER,NO_SUCH_CENSUS_DATA,NO_CENSUS_DATA,CENSUS_FILE_PROBLEM;
    }

    public CensusAnalyserCustomExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(CensusAnalyserCustomExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}