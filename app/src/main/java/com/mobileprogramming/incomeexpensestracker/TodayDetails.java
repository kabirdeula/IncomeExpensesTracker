package com.mobileprogramming.incomeexpensestracker;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayDetails extends Activity {
    RecyclerView rv;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    MyDatabase myDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle viewSavedInstance){
        super.onCreate(viewSavedInstance);
        setContentView(R.layout.activity_today_details);
        rv = findViewById(R.id.rv);
        myDatabase = new MyDatabase(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }

    public void loadData(){
        cursor = myDatabase.selectData(MainActivity.getCurrentDate());
        ArrayList<DataModel> data = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            int income = cursor.getInt(2);
            int expenses = cursor.getInt(3);
            DataModel dataModel = new DataModel(id, date, income, expenses);
            data.add(dataModel);
        }

        layoutManager = new LinearLayoutManager(this);
        recyclerAdapter = new RecyclerViewAdapter(this, data);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(recyclerAdapter);
    }
}
