package com.jeff.monthlyexpense.repository;

import com.jeff.monthlyexpense.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {
    
    @Query(value = "select sum(e.amount) from Expense e where e.category=:catName")
    Double getTotalExpensesByCategory(String catName);
    
}
