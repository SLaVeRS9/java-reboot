package ru.sberbank.edu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GCDTest {
    private static GCD GCD;
    private static String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "This algo doesn't work with two zero numbers";
    @Before
    public void init() {
        GCD = new GCD();
    }

    @After
    public void tearDown() {
        GCD = null;
    }

    @Test
    public void getDivisorWithTwoPositiveNumbers() {
        int result = GCD.getDivisor(10, 4);
        assertEquals(2, result);
    }

    @Test
    public void getDivisorWithTwoNegativeNumbers() {
        int result = GCD.getDivisor(-15, -10);
        assertEquals(5, result);
    }

    @Test
    public void getDivisorWithPositiveAndNegativeNumbers() {
        int result = GCD.getDivisor(15, -10);
        assertEquals(5, result);
    }

    @Test
    public void getDivisorWithOneZeroNumber() {
        int result = GCD.getDivisor(0, -10);
        assertEquals(10, result);
    }

    @Test
    public void getDivisorWithTwoZeroNumbers() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> GCD.getDivisor(0, 0)
        );
        assertEquals(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, exception.getMessage());
    }
}
