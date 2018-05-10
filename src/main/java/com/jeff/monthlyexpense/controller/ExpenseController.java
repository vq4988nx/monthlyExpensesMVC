package com.jeff.monthlyexpense.controller;

import com.jeff.monthlyexpense.model.Expense;
import com.jeff.monthlyexpense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    Sample data here
    @Autowired
    public ExpenseController(ExpenseRepository expenses) {
        this.expenses = expenses;

        expenses.save(new Expense(new Date(), 6, "Panera", "Eating Out"));
        expenses.save(new Expense(new Date(), 800, "Rent", "Mortgage/Rent"));
        expenses.save(new Expense(new Date(), 70, "Verizon monthly", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 85, "Cub", "Groceries/Target"));
        expenses.save(new Expense(new Date(), 50, "Twins Game", "Entertainment"));
        expenses.save(new Expense(new Date(), 101, "Vet Appointment", "Pets"));
        expenses.save(new Expense(new Date(), 33, "MnGasCo", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 62, "Water Bill", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 48, "XCel Electric", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 45, "Birthday Present", "Gifts"));
        expenses.save(new Expense(new Date(), 160, "Discount Tires", "Transportation"));
        expenses.save(new Expense(new Date(), 7, "Chipotle", "Eating Out"));
        expenses.save(new Expense(new Date(), 32, "Walgreens Prescription", "Medical"));
        expenses.save(new Expense(new Date(), 140, "Target", "Groceries/Target"));
        expenses.save(new Expense(new Date(), 190, "MOA Spree", "Clothing"));
        expenses.save(new Expense(new Date(), 35, "US Internet", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 6, "MCTC Cafeteria", "Eating Out"));
        expenses.save(new Expense(new Date(), 32, "Bull Dog", "Entertainment"));
        expenses.save(new Expense(new Date(), 120, "Hotel in Duluth", "Travel"));
        expenses.save(new Expense(new Date(), 80, "Geico Auto Ins", "Monthly Bills"));
        expenses.save(new Expense(new Date(), 400, "Joe's Garage - Alternator", "Transportation"));
        expenses.save(new Expense(new Date(), 74, "Amazon stuff", "Amazon"));
        expenses.save(new Expense(new Date(), 30, "SA Gas", "Transportation"));
        expenses.save(new Expense(new Date(), 5, "Lunds", "Eating Out"));
        expenses.save(new Expense(new Date(), 35, "Chuck and Dons", "Pets"));
        expenses.save(new Expense(new Date(), 6, "Black Panther", "Entertainment"));
        expenses.save(new Expense(new Date(), 35, "Concert", "Entertainment"));
        expenses.save(new Expense(new Date(), 50, "Wedding Gift", "Gifts"));
    }

    //This will always direct to the home page
    @RequestMapping("/")
    public ModelAndView addExpense() {
        return new ModelAndView("createExpense.html", "expense", new Expense());
    }

    //This is a post method that will redirect to the /addExpense page
    @RequestMapping(value="/addExpense", method= RequestMethod.POST)
    public RedirectView addNewExpense(Expense expense) {
        expenses.save(expense);
        return new RedirectView("/allExpenses");
    }


//    this returns the updated expenseList page
    @RequestMapping("/allExpenses")
    public ModelAndView allTasks(ModelMap modelMap){
        modelMap.addAttribute("expenses", expenses.findAll());
        return new ModelAndView("expenseList.html", modelMap);
    }

    //This mapping is for the categoriesTable.  Each category is returning the total for the category
//    Need to add if statements to every single category to prevent null pointer exceptions if there is a category which does not have an expense
//    But I could not correctly format the if statement to do this.
    @RequestMapping("/categoriesTable")
    public String categoriesTable(Model model) {
        double totalExpensesMortgageRent = expenses.getTotalExpensesByCategory("Mortgage/Rent");
        // if there's no Mortgage/Rent expenses, this returns null, so check for null. Adding nulls to the model causes an exception.
        model.addAttribute("totalExpensesMortgageRent", totalExpensesMortgageRent);

        double totalExpensesMonthlyBills = expenses.getTotalExpensesByCategory("Monthly Bills");
        model.addAttribute("totalExpensesMonthlyBills", totalExpensesMonthlyBills);

        double totalExpensesEatingOut = expenses.getTotalExpensesByCategory("Eating Out");
        model.addAttribute("totalExpensesEatingOut", totalExpensesEatingOut);

        double totalExpensesGroceriesTarget = expenses.getTotalExpensesByCategory("Groceries/Target");
        model.addAttribute("totalExpensesGroceriesTarget", totalExpensesGroceriesTarget);

        double totalExpensesMedical = expenses.getTotalExpensesByCategory("Medical");
        model.addAttribute("totalExpensesMedical", totalExpensesMedical);

        double totalExpensesPets = expenses.getTotalExpensesByCategory("Pets");
        model.addAttribute("totalExpensesPets", totalExpensesPets);

        double totalExpensesEntertainment = expenses.getTotalExpensesByCategory("Entertainment");
        model.addAttribute("totalExpensesEntertainment", totalExpensesEntertainment);

        double totalExpensesTravel = expenses.getTotalExpensesByCategory("Travel");
        model.addAttribute("totalExpensesTravel", totalExpensesTravel);

        double totalExpensesAmazon = expenses.getTotalExpensesByCategory("Amazon");
        model.addAttribute("totalExpensesAmazon", totalExpensesAmazon);

        double totalExpensesGifts = expenses.getTotalExpensesByCategory("Gifts");
        model.addAttribute("totalExpensesGifts", totalExpensesGifts);

        double totalExpensesClothing = expenses.getTotalExpensesByCategory("Clothing");
        model.addAttribute("totalExpensesClothing", totalExpensesClothing);

        return "categoriesTable.html";
//        return new ModelAndView("categoriesTable.html", "expense", new Expense());


    }


    //    This will delete an individual expense item, and return to the allExpense page
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteExpense(@PathVariable(name = "id") Long id) {
        System.out.println(id);
        expenses.deleteById(id);
        return "redirect:/allExpenses";
    }

    //    This will delete all expense items
    @RequestMapping(path = "/deleteAll", method = RequestMethod.GET)
    public String deleteAllExpense() {
//        System.out.println(id);
        expenses.deleteAll();
        return "redirect:/allExpenses";
    }



//    This returns the updated expenseList page
    @RequestMapping("/expenseList")
    public ModelAndView backToTaskList(ModelMap modelMap) {
        return new ModelAndView("expenseList", modelMap);
    }


//    This mapping is for updating an expense
    @PostMapping("/updateExpense")
    public String updateTask(Expense t) {
        expenses.save(t);
        return  "redirect:/allExpenses";
    }



}


