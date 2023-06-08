package com.sandrapeinados.pelugestion.services;


import com.sandrapeinados.pelugestion.models.Expense;

import java.util.List;

public interface IExpenseService {

    Expense saveExpense(Expense expense);
    List<Expense> getExpenses();
    Expense getExpenseById(Long id);
    void deleteExpense(Long id);
    Expense updateExpense(Expense expense);


}
