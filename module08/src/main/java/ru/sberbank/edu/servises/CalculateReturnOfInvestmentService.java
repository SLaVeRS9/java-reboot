package ru.sberbank.edu.servises;

public class CalculateReturnOfInvestmentService {
    public static Integer calculate(int amount, int interestRate, int yearsAmount) {
        return amount*interestRate*yearsAmount;
    }
}
