package com.mobileprogramming.incomeexpensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ModifyDetails extends AppCompatActivity {
    private MyDatabase myDatabase;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_details);

        EditText edit_date = findViewById(R.id.edit_date);
        EditText edit_amount = findViewById(R.id.edit_amount);

        Spinner s_title = findViewById(R.id.s_title);

        Button btn_update = findViewById(R.id.btn_update);
        Button btn_delete = findViewById(R.id.btn_delete);
        myDatabase = new MyDatabase(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String date1 = intent.getStringExtra("date");
        int amount1 = intent.getIntExtra("amount", 0);

        edit_date.setText(date1);
        edit_amount.setText(String.valueOf(amount1));

        btn_delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ModifyDetails.this);
            builder.setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this data?")
                    .setPositiveButton("Delete", (dialogInterface, i) -> {
                        myDatabase.deleteData(String.valueOf(id));
                        Toast.makeText(ModifyDetails.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        btn_update.setOnClickListener(view -> {
            String date = edit_date.getText().toString();
            String title = s_title.getSelectedItem().toString();
            String amount = edit_amount.getText().toString();

            //            Data Validation
            if(date.equals("")){
                Toast.makeText(ModifyDetails.this, "Please Enter Date!", Toast.LENGTH_LONG).show();
            } else if (amount.equals("")) {
                Toast.makeText(ModifyDetails.this, "Please Enter Amount!", Toast.LENGTH_LONG).show();
            } else if (title.equals("Select Title")){
                Toast.makeText(ModifyDetails.this,"Please Select a Title!", Toast.LENGTH_LONG).show();
            } else {
                if (title.equals("Income")){
                    myDatabase.updateData(String.valueOf(id), date, "0", amount);
                } else {
                    myDatabase.updateData(String.valueOf(id), date, "0", amount);
                }
                Toast.makeText(ModifyDetails.this, "Data Updated Successfully!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
