package com.example.customlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelperTwo extends SQLiteOpenHelper {

    public static final  String dbName="xaProkductsey.db";
    public static final  String TbName="Produkct_tables";
    public static final  String col_1="id";
    public static final  String col_2="name";
    public static final  String col_3="price";
    public static final  String col_4="description";
    public static final  String col_5="selection";



    public DataBaseHelperTwo( Context context) {
        super(context,dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TbName +"(id integer primary key autoincrement,price integer,name text,description text,selection text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TbName);
        onCreate(db);
    }

    public  boolean insertData(String name,int price,String description,String sel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,price);
        contentValues.put(col_4,description);
        contentValues.put(col_5,sel);
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
    public ArrayList<Product> getAll(){
        ArrayList<Product> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TbName,null);

        while(res.moveToNext()){

            int id=res.getInt(0);
            int price=res.getInt(1);

            String name=res.getString(2);

            String description=res.getString(3);
            String sel=res.getString(4);
            Product p;
            if (sel.equals("selected")){
                 p=new Product(id,price,name,description,"selected");
            }else{
                 p=new Product(id,price,name,description,"not selected");
            }

          arrayList.add(p);
        }
        return arrayList;

    }

 public boolean updateData(String id){

     ArrayList<Product> arrayList=new ArrayList<>();
     SQLiteDatabase db=this.getWritableDatabase();
     Cursor res=db.rawQuery("select * from "+TbName+" where "+col_1+"='"+id+"'",null);

     while(res.moveToNext()){

         int ids=res.getInt(0);
         int prices=res.getInt(1);

         String names=res.getString(2);

         String descriptions=res.getString(3);
         String sels=res.getString(4);
         if (sels.equals("not selected")){

             ContentValues contentValues=new ContentValues();
             contentValues.put(col_2,names);
             contentValues.put(col_1,ids);
             contentValues.put(col_3,prices);
             contentValues.put(col_4,descriptions);
             contentValues.put(col_5,"selected");
             db.update(TbName,contentValues,"id=?",new String[]{ id });
            // return true;
         }else{

             ContentValues contentValues=new ContentValues();
             contentValues.put(col_2,names);
             contentValues.put(col_1,ids);
             contentValues.put(col_3,prices);
             contentValues.put(col_4,descriptions);
             contentValues.put(col_5,"not selected");
             db.update(TbName,contentValues,"id=?",new String[]{ id });

         }


     }

      return true;


 }
}
