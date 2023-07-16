package com.mobileprogramming.incomeexpensestracker;

public class DataModel {
    private final int id;
    private final String date;
    private final int income;
    private final int expenses;

    public DataModel(int id, String date, int income, int expenses){
        this.id = id;
        this.date = date;
        this.income = income;
        this.expenses = expenses;
    }

    public int getId(){
        return id;
    }

    public String getDate(){
        return date;
    }

    public int getIncome(){
        return income;
    }

    public int getExpenses() {
        return expenses;
    }
}
