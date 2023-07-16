package com.mobileprogramming.incomeexpensestracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tracker";

    private static final String TABLE_NAME = "records";

    public MyDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String createQuery = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, income TEXT, expenses TEXT)";
        database.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        String dropQuery = "DROP TABLE IF EXISTS ";
        database.execSQL(dropQuery + DATABASE_NAME);
        onCreate(database);
    }

    public void insertData(String date, String income, String expenses){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("income", income);
        contentValues.put("expenses", expenses);

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public Cursor selectData(String date){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE date=?";
        return database.rawQuery(query, new String[]{date});
    }

    public Cursor selectData(String date1, String date2){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE date BETWEEN ? AND ?";
        return database.rawQuery(query, new String[]{date1, date2});
    }

    public void updateData(String id, String date, String income, String expenses){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("income", income);
        contentValues.put("expenses", expenses);

        database.update(TABLE_NAME, contentValues, "id=?", new String[]{id});
        database.close();
    }

    public void deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, "id=?", new String[]{id});
    }
}
