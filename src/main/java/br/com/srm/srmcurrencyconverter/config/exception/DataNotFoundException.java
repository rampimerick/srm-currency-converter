package br.com.srm.srmcurrencyconverter.config.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException( String message, String fieldName, Integer code) {
        super( message + " " + fieldName + " = " + code);
    }
}
