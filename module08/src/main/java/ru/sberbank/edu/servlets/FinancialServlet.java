package ru.sberbank.edu.servlets;

import ru.sberbank.edu.servises.CalculateReturnOfInvestmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/finance")
public class FinancialServlet extends HttpServlet {
    private static final Integer MIN_INVESTMENT_AMOUNT = 50_000;
    private static final String ERROR_TITLE = "Ошибка";
    private static final String RESULT_TITLE = "Результат";
    private static final String RESULT_DESCRIPTION = "Итоговая сумма %s рублей";
    private static final String MIN_INVESTMENT_AMOUNT_DESCRIPTION = "Минимальная сумма на момент \n" +
            "открытия вклада " + MIN_INVESTMENT_AMOUNT + " рублей\n";
    private static final String RESULT_DESCRIPTION_ERROR = "Неверный формат данных. \n" +
            "Скорректируйте значения";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("calculator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            String amountStr = req.getParameter("amount");
            String interestRateStr = req.getParameter("interestRate");
            String yearsAmountStr = req.getParameter("yearsAmount");
            System.out.println(amountStr);
            System.out.println(interestRateStr);
            System.out.println(yearsAmountStr);
            Integer amount = Integer.valueOf(amountStr);
            Integer interestRate = Integer.valueOf(interestRateStr);
            Integer yearsAmount = Integer.valueOf(yearsAmountStr);

            if(amount < MIN_INVESTMENT_AMOUNT) {
                req.setAttribute("title", ERROR_TITLE);
                req.setAttribute("description", MIN_INVESTMENT_AMOUNT_DESCRIPTION);
                req.getRequestDispatcher("calcResult.jsp").forward(req, resp);
            }
            Integer result = CalculateReturnOfInvestmentService.calculate(amount, interestRate, yearsAmount);
            String resultDescription = String.format(RESULT_DESCRIPTION, result);
            req.setAttribute("title", ERROR_TITLE);
            req.setAttribute("description", resultDescription);
            req.getRequestDispatcher("calcResult.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("title", ERROR_TITLE);
            req.setAttribute("description", RESULT_DESCRIPTION_ERROR);
            req.getRequestDispatcher("calcResult.jsp").forward(req, resp);
        }
    }
}
