package org.itstep.exceptions.validations;


/**
 * Мое исключение - я не могу работать с именами - которые меньше, чем 3 символа
 */
public class NameLengthValidationMin extends Exception
{
    public NameLengthValidationMin(String s) {
        super(s);
    }
}
