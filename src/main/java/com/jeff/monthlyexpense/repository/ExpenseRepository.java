package com.jeff.monthlyexpense.repository;

import com.jeff.monthlyexpense.model.Expense;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {
}
