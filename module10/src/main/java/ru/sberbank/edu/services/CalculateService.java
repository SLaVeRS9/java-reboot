package ru.sberbank.edu.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@PropertySource("classpath:application.properties")
public class CalculateService {
    @Value("${finance.min.sum}")
    private String minAmount;

    @Value("${finance.result.title}")
    private String resultTitle;

    @Value("${finance.min.description}")
    private String minAmountDescription;

    @Value("${finance.error.title}")
    private String errorTitle;

    @Value("${finance.error.description}")
    private String errorDescription;

    @Value("${finance.result.description}")
    private String resultDescription;

    @Value("${finance.exception.negativeValues}")
    private String exceptionNegativeValues;

    public String calculate(String sum, String percentage, String years, Model model) {
        try {
            Double dAmount = Double.parseDouble(sum);
            Double dInterestRate = Double.parseDouble(percentage);
            Double dYearsAmount = Double.parseDouble(years);

            if (dAmount < 0 || dInterestRate < 0 || dYearsAmount < 0) {
                throw new IllegalArgumentException(exceptionNegativeValues);
            }

            if(dAmount < Double.parseDouble(minAmount)) {
                model.addAttribute("title", resultTitle);
                model.addAttribute("description", String.format(minAmountDescription, minAmount));
            } else {
                Double result = dAmount * dInterestRate * dYearsAmount;
                model.addAttribute("title", resultTitle);
                model.addAttribute("description", String.format(resultDescription, result));
            }
        } catch (Exception e) {
            model.addAttribute("title", errorTitle);
            model.addAttribute("description", errorDescription + ". " + e.getMessage());
        }
        return "finance/calcResult";
    }

}
