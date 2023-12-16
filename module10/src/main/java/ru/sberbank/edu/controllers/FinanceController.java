package ru.sberbank.edu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.edu.services.CalculateService;


@Controller
@RequiredArgsConstructor
public class FinanceController {
    private final CalculateService calculateService;
    @GetMapping("/finance")
    public String calculator() {
        return "finance/calculator";
    }

    @PostMapping("/finance")
    public String calcResult(
            @RequestParam("sum") String sum,
            @RequestParam("percentage") String percentage,
            @RequestParam("years") String years,
            Model model) {

        return calculateService.calculate(sum, percentage, years, model);
    }
}
