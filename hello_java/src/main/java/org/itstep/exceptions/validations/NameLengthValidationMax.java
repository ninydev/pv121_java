package org.itstep.exceptions.validations;


/**
 * Мое исключение - я не могу работать с именами - которые больше, чем 10
 */
public class NameLengthValidationMax extends Exception
{
    public NameLengthValidationMax(String s) {
        super(s);
    }
}
