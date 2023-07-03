package com.mobileprogramming.incomeexpensestracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView tv_income;
    private TextView tv_expenses;
    private TextView tv_balance;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_income = findViewById(R.id.tv_income);
        tv_expenses = findViewById(R.id.tv_expenses);
        tv_balance = findViewById(R.id.tv_balance);
        TextView tv_details = findViewById(R.id.tv_details);

        Button btn_enter = findViewById(R.id.btn_enter);
        Button btn_report = findViewById(R.id.btn_report);

        myDatabase = new MyDatabase(this);

        btn_enter.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DataEntry.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }

    public void loadData(){
        Cursor cursor = myDatabase.selectData(getCurrentDate());
        int income = 0, expenses = 0, balance;
        while (cursor.moveToNext()){
            income += cursor.getInt(2);
            expenses += cursor.getInt(3);
        }
        balance = income - expenses;
        tv_income.setText(String.valueOf(income));
        tv_expenses.setText(String.valueOf(expenses));
        tv_balance.setText(String.valueOf(balance));
    }

    public static String getCurrentDate(){
        Date date = new Date();
        DateFormat formatter = DateFormat.getDateInstance();
        String currentDate = formatter.format(date);
        return currentDate;
    }
}
