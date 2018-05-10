package com.jeff.monthlyexpense.repository;

import com.jeff.monthlyexpense.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {
    

//    Was getting an error saying For queries with named parameters you need to use provide
//    names for method parameters. Use @Param for query method parameters
//    https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/

    @Query(value = "select sum(e.amount) from Expense e where e.category=:catName")
    Double getTotalExpensesByCategory(@Param("catName") String catName);

    
}
