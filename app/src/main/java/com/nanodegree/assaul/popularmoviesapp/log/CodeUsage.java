package com.nanodegree.assaul.popularmoviesapp.log;

/**
 * Created by Andrey Assaul on 04.07.2015.
 */
public final class CodeUsage {

    private final static String ILLEGAL_PARAMETER_VALUE_ERROR_PATTERN = "Illegal parameter %s with value %s for method %s in class %s";

    public String getIllegalParameterValueError(String parameter, String value, String method, String className){
        return String.format(ILLEGAL_PARAMETER_VALUE_ERROR_PATTERN, parameter, value, method, className);
    }

    CodeUsage(){}
}
