package ru.sberbank.edu.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.edu.services.CalculateService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {
    private final CalculateService calculateService;
    @GetMapping()
    public String calculator() {
        return "finance/calculator";
    }

    @PostMapping()
    public String calcResult(
            @RequestParam("sum") String sum,
            @RequestParam("percentage") String percentage,
            @RequestParam("years") String years,
            Model model) {

        return calculateService.calculate(sum, percentage, years, model);
    }
}
