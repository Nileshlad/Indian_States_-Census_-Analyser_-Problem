package com.bridgelabz.census.analyser;

import java.io.Reader;
import java.util.Iterator;

public interface IcsvBuilder {
    public <E> Iterator <E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws StateCensusAnalyserException;
}