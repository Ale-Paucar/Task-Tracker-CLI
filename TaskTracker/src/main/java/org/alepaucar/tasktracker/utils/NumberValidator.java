package org.alepaucar.tasktracker.utils;

public class NumberValidator {
    private long numericValue;
    private boolean valid;

    public NumberValidator(String input) {
        try{
            this.numericValue = Long.parseLong(input);
            this.valid = true;
        } catch (NumberFormatException e) {
            this.valid = false;
        }
    }

    public boolean isValid(){
        return valid;
    }

    public long getNumericValue() {
        return numericValue;
    }
}
