package com.mobileprogramming.incomeexpensestracker;

import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class DataEntry extends AppCompatActivity {
    private EditText et_date, et_amount;
    private Spinner s_title;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        et_date = findViewById(R.id.et_date);
        et_amount = findViewById(R.id.et_amount);

        s_title = findViewById(R.id.s_title);

        Button btn_submit = findViewById(R.id.btn_submit);

        myDatabase = new MyDatabase(this);

        et_date.setText(MainActivity.getCurrentDate());

        btn_submit.setOnClickListener(view -> {
            String date = et_date.getText().toString();
            String title = s_title.getSelectedItem().toString();
            String amount = et_amount.getText().toString();

//            Data Validation
            if(date.equals("")){
                Toast.makeText(DataEntry.this, "Please Enter Date!", Toast.LENGTH_LONG).show();
            } else if (amount.equals("")) {
                Toast.makeText(DataEntry.this, "Please Enter Amount!", Toast.LENGTH_LONG).show();
            } else if (title.equals("Select Title")){
                Toast.makeText(DataEntry.this,"Please Select a Title!", Toast.LENGTH_LONG).show();
            } else {
                if (title.equals("Income")){
                    myDatabase.insertData(date, amount, "0");
                } else {
                    myDatabase.insertData(date, "0", amount);
                }
                Toast.makeText(DataEntry.this, "Data Inserted Successfully!", Toast.LENGTH_LONG).show();
                et_amount.setText("");
                s_title.setSelection(0);
            }
        });
    }
}
