package com.example.onome.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anychart.chart.common.dataentry.CategoryValueDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    private Context context;
    public DatabaseHandler(@Nullable Context context) {
        super(context, "dbNames", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE IF NOT EXISTS tbNome( ");
        sql.append(" NAME TEXT PRIMARY KEY NOT NULL, ");
        sql.append(" PERIO0 INTEGER NOT NULL, ");
        sql.append(" PERIO1 INTEGER NOT NULL, ");
        sql.append(" PERIO2 INTEGER NOT NULL, ");
        sql.append(" PERIO3 INTEGER NOT NULL, ");
        sql.append(" PERIO4 INTEGER NOT NULL, ");
        sql.append(" PERIO5 INTEGER NOT NULL, ");
        sql.append(" PERIO6 INTEGER NOT NULL, ");
        sql.append(" PERIO7 INTEGER NOT NULL  ); ");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addName(String name, int p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("PERIO0", p0);
        cv.put("PERIO1", p1);
        cv.put("PERIO2", p2);
        cv.put("PERIO3", p3);
        cv.put("PERIO4", p4);
        cv.put("PERIO5", p5);
        cv.put("PERIO6", p6);
        cv.put("PERIO7", p7);
        long result = db.insert("tbNome", null, cv);
        if(result == -1){
            Toast.makeText(context, "Erro", Toast.LENGTH_LONG).show();
        }
    }
    public boolean CheckIsDataAlreadyInDBorNot(String TableName, String dbfield, String fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TableName + " WHERE " + dbfield + " = '"+fieldValue+"'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<DataEntry> getNames(int time){
        String p = String.valueOf(time);
        final List<DataEntry> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT NAME, PERIO"+p+" FROM tbNome", null);
        if (c.moveToFirst()){
            do {
                data.add(new CategoryValueDataEntry(c.getString(0), c.getString(0), c.getInt(1)));
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return data;
    }
}
