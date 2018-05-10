package com.jeff.monthlyexpense.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayt;

    private double amount;

    private String description;

    private String category;

    public Expense(Date dayt, double amount, String description, String category) {
        this.dayt = dayt;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", dayt='" + dayt + '\'' +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public Date getDayt() {
        return dayt;
    }


    public void setDayt(Date dayt) {
        this.dayt = dayt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Expense() {}


}
