package ru.sberbank.edu;

/**
 * @author SLaVeRS9
 * @version 1.0
 * Euclid algo realization
 */

public class GCD implements CommonDivisor {
    /**
     * Euclid algo method
     * @param firstNumber first number for euclid algo
     * @param secondNumber second number for euclid algo
     * @return greatest common divisor of two numbers
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        if (firstNumber == 0 && secondNumber == 0) {
            throw new IllegalArgumentException("This algo doesn't work with two zero numbers");
        } else if (secondNumber == 0) {
            return Math.abs(firstNumber);
        } else {
            return getDivisor(secondNumber, firstNumber % secondNumber);
        }
    }
}
