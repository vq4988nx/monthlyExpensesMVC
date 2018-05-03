package com.jeff.monthlyexpense.controller;

import com.jeff.monthlyexpense.model.Expense;
import com.jeff.monthlyexpense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Date;

@Controller
public class ExpenseController {


    //This task object will interact with the database
    private final ExpenseRepository expenses;

    @Autowired
    public ExpenseController(ExpenseRepository expenses) {
        this.expenses = expenses;
    }

    //This will always direct to the home page
    @RequestMapping("/")
    public ModelAndView addExpense() {
        return new ModelAndView("createExpense.html", "expense", new Expense());
    }

    //This is a post method that will redirect to the /addTask page
    @RequestMapping(value="/addExpense", method= RequestMethod.POST)
    public RedirectView addNewExpense(Expense expense) {
        expenses.save(expense);
        return new RedirectView("/allExpenses");
    }

    //This will direct to the /allTasks page, where the database will be queried for all tasks
    @RequestMapping("/allExpenses")
    public ModelAndView allTasks(ModelMap modelMap){
        modelMap.addAttribute("expenses", expenses.findAll());
        return new ModelAndView("expenseList.html", modelMap);
    }

    //    new mapping, experiment
    @RequestMapping("/categoriesTable")
    public ModelAndView categoriesTable() {
        return new ModelAndView("categoriesTable.html", "expense", new Expense());
    }


    //    Trying to fix this delete method
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteExpense(@PathVariable(name = "id") Long id) {
        System.out.println(id);
        expenses.deleteById(id);
        return "redirect:/allExpenses";
    }

    @RequestMapping("/expenseList")
    public ModelAndView backToTaskList(ModelMap modelMap) {
        return new ModelAndView("expenseList", modelMap);
    }

    @PostMapping("/updateExpense")
    public String updateTask(Expense t) {
        System.out.println(t);  // TODO actually do the update
        expenses.save(t);
        return  "redirect:/allExpenses";
    }


}
