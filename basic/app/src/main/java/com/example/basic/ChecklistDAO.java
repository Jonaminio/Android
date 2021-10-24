package com.example.basic;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

import java.util.List;


public class ChecklistDAO {
    private SQLiteDatabase mConection;

    public ChecklistDAO(SQLiteDatabase conection) {
        mConection = conection;
    }

    public void insert(Checklist chk){ }
    public void remove(int id){ }
    public void alter(Checklist chk){ }
    public List<Checklist> listChecklists(){ return null; }
    public Checklist getChecklist(int id){return null; }

    public void insert(Checklist chk){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Description",chk.getmDescription());
        contentValues.put("Active",chk.ismActive());
        mConection.insertOrThrow("CHecklist",null, contentValues);
    }
    public void remove(int id){
        String[] params = new String[1];
        params[0] = String.valueOf(id);
        mConection.delete("Checklist","ID = ?",params);
    }
}

