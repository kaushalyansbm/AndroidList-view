package com.example.customlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final  String dbName="Country.db";
    public static final  String TbName="Country_table";
    public static final  String col_1="id";
    public static final  String col_2="name";
    public static final  String col_3="capital";



    public DatabaseHelper( Context context) {
        super(context,dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TbName +"(id integer primary key autoincrement,name text,capital text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TbName);
        onCreate(db);
    }

    public  boolean insertData(String name,String capital){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,capital);

        long result=db.insert(TbName,null,contentValues);
        if (result==-1)
        {
            return  false;
        }
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TbName,null);
        return  res;
    }

}
