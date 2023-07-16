package com.mobileprogramming.incomeexpensestracker;

import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    ArrayList<DataModel>data;

    public RecyclerViewAdapter(Activity context, ArrayList<DataModel> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.activity_list_items, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final DataModel current = data.get(position);

        String formattedDate = context.getString(R.string.tv_date, current.getDate());
        holder.tv_date.setText(formattedDate);
        if(current.getExpenses() == 0){
            holder.tv_expenses.setVisibility(View.GONE);
            holder.tv_income.setVisibility(View.VISIBLE);
            String formattedIncome = context.getString(R.string.tv_income_display, current.getIncome());
            holder.tv_income.setText(formattedIncome);
        } else{
            holder.tv_expenses.setVisibility(View.VISIBLE);
            holder.tv_income.setVisibility(View.GONE);
            String formattedExpenses = context.getString(R.string.tv_expenses_display, current.getExpenses());
            holder.tv_expenses.setText(formattedExpenses);
        }

        holder.relative_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ModifyDetails.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("date", current.getDate());

            if (current.getExpenses() == 0){
                intent.putExtra("amount", current.getIncome());
            } else {
                intent.putExtra("amount", current.getExpenses());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_date, tv_income, tv_expenses;
        private final RelativeLayout relative_layout;

        public ViewHolder(View itemView){
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_income = itemView.findViewById(R.id.tv_income);
            tv_expenses = itemView.findViewById(R.id.tv_expenses);
            relative_layout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
