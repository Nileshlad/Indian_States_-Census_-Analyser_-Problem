package com.exception;

public class CSVBuilderException extends Exception {

    public CensusAnalyserCustomExceptionType type;

    //ENUM CLASS
    public enum CensusAnalyserCustomExceptionType {
        UNABLE_TO_PARSE, NO_SUCH_FILE, NO_SUCH_FILE_TYPE, NO_SUCH_DELIMITER_OR_HEADER;
    }

    //CONSTRUCTOR
    public CSVBuilderException(CensusAnalyserCustomExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}

