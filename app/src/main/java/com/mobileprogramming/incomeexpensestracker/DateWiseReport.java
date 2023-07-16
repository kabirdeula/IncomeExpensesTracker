package com.mobileprogramming.incomeexpensestracker;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateWiseReport extends Activity {
    private RecyclerView rv;
    private MyDatabase myDatabase;
    private EditText et_date1;
    private EditText et_date2;

    @Override
    protected void onCreate(Bundle viewSavedInstance){
        super.onCreate(viewSavedInstance);
        setContentView(R.layout.activity_datawise_report);
        rv = findViewById(R.id.rv);
        myDatabase = new MyDatabase(this);
        et_date1 = findViewById(R.id.et_date1);
        et_date2 = findViewById(R.id.et_date2);
        Button btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(view -> loadData());
    }

    public void loadData(){
        String date1 = et_date1.getText().toString();
        String date2 = et_date2.getText().toString();
        if (date1.equals("") || date2.equals("")){
            Toast.makeText(DateWiseReport.this, "Please Enter Date!", Toast.LENGTH_LONG).show();
        } else{
            Cursor cursor = myDatabase.selectData(date1, date2);
            ArrayList<DataModel> data = new ArrayList<>();
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                int income = cursor.getInt(2);
                int expenses = cursor.getInt(3);
                DataModel dataModel = new DataModel(id, date, income, expenses);
                data.add(dataModel);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(this, data);
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(recyclerAdapter);
        }
    }
}
