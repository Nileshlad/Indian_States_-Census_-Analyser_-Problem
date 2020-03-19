package com.bridgelabz.census.analyser;

public class StateCensusAnalyserException extends Exception {

        //
   public enum CensusAnalyserCustomExceptionType{
        NO_SUCH_FILE_FOUND,NO_SUCH_TYPE;

        }
    public CensusAnalyserCustomExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(CensusAnalyserCustomExceptionType type, String message) {
        super(message);
        this.type = type;
    }

}

