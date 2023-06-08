package com.sandrapeinados.pelugestion.services;

import com.sandrapeinados.pelugestion.exceptions.ResourceNotFoundException;
import com.sandrapeinados.pelugestion.models.Expense;
import com.sandrapeinados.pelugestion.persistence.entities.ExpenseEntity;
import com.sandrapeinados.pelugestion.persistence.repositories.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements IExpenseService {
    @Autowired
    private IExpenseRepository expenseRepo;

    @Override
    public Expense saveExpense(Expense expense) {
        ExpenseEntity expenseToSave = new ExpenseEntity();
        expenseToSave.setExpenseTitle(expense.getExpenseTitle());
        expenseToSave.setExpenseDescription(expense.getExpenseDescription());
        expenseToSave.setExpenseAmount(expense.getExpenseAmount());
        expenseToSave.setDate(expense.getDate());
        expenseRepo.save(expenseToSave);
        expense.setId(expenseToSave.getId());
        return expense;
    }

    @Override
    public List<Expense> getExpenses() {
        List<ExpenseEntity> expensesFounds = expenseRepo.findAll();
        List<Expense> expensesList = new ArrayList<>();

        for(ExpenseEntity e: expensesFounds){
            Expense expense = new Expense();
            expense.setId(e.getId());
            expense.setExpenseTitle(e.getExpenseTitle());
            expense.setExpenseDescription(e.getExpenseDescription());
            expense.setExpenseAmount(e.getExpenseAmount());
            expense.setDate(e.getDate());
            expensesList.add(expense);
        }
        return expensesList;
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<ExpenseEntity> expenseFound = expenseRepo.findById(id);
        if(expenseFound.isPresent()){
            Expense expense = new Expense();
            expense.setId(expenseFound.get().getId());
            expense.setExpenseTitle(expenseFound.get().getExpenseTitle());
            expense.setExpenseDescription(expenseFound.get().getExpenseDescription());
            expense.setExpenseAmount(expenseFound.get().getExpenseAmount());
            expense.setDate(expenseFound.get().getDate());
            return expense;
        } else {
            throw new ResourceNotFoundException("Expense Not Found");
        }
    }

    @Override
    public void deleteExpense(Long id) {
        Optional<ExpenseEntity> expenseFound = expenseRepo.findById(id);
        if(expenseFound.isPresent()){
            expenseRepo.deleteById(id);
        } else{
            throw new ResourceNotFoundException("Expense not found");
        }
    }

    @Override
    public Expense updateExpense(Expense expense) {
        Optional<ExpenseEntity> expenseFound = expenseRepo.findById(expense.getId());
        if(expenseFound.isPresent()){
            expenseFound.get().setExpenseTitle(expense.getExpenseTitle());
            expenseFound.get().setExpenseDescription(expense.getExpenseDescription());
            expenseFound.get().setExpenseAmount(expense.getExpenseAmount());
            expenseFound.get().setDate(expense.getDate());
            expenseRepo.save(expenseFound.get());
            return expense;
        } else {
            throw new ResourceNotFoundException("Expense not found");
        }
    }
}
