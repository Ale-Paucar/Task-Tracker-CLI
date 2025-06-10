package org.alepaucar.tasktracker.utils;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberValidatorTest {


    @Test
    void testIsValidStringsWithLetters(){
        assertFalse(new NumberValidator("Hola mundo").isValid());
        assertFalse(new NumberValidator("123abc").isValid());
        assertFalse(new NumberValidator("12.34").isValid());
        assertFalse(new NumberValidator(" 123 ").isValid());
        assertFalse(new NumberValidator("").isValid());
    }

    @Test
    void testIsValidNumericValue(){
        assertTrue(new NumberValidator("123").isValid());
        assertTrue(new NumberValidator("-123").isValid());
        assertTrue(new NumberValidator("9223372036854775807").isValid());
        assertTrue(new NumberValidator("-9223372036854775808").isValid());
    }

    @Test
    void testIsValidNumericValueOutOfRange(){

        assertFalse(new NumberValidator("9223372036854775808").isValid());
        assertFalse(new NumberValidator("-9223372036854775809").isValid());
    }
}
