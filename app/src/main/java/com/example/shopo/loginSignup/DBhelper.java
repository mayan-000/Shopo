package com.example.shopo.loginSignup;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBhelper extends SQLiteOpenHelper {


    public DBhelper(Context context) {
        super(context, "CustomerDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SHOPPODETAILS (Password TEXT , Full_name TEXT , Email TEXT  , Address TEXT ,  Phone_no TEXT PRIMARY KEY)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists SHOPPODETAILS");
    }


    public Boolean insertuserdata(String pass, String name, String email, String add, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password", pass.toString());
        contentValues.put("Email", email.toString());
        contentValues.put("Full_name", name.toString());
        contentValues.put("Address", add.toString());
        contentValues.put("Phone_no", phone.toString());


        long result = sqLiteDatabase.insert("SHOPPODETAILS", null, contentValues);
        if (result == -1)
            return false;

        else
            return true;

    }


    public Boolean checkphone(String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from SHOPPODETAILS where Phone_no = ?", new String[]{phone});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkpassword(String phone_no, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from SHOPPODETAILS where Phone_no = ? and Password = ?", new String[]{phone_no, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean updatedata(String name, String email, String add, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //  contentValues.put("Password" , pass.toString());
        contentValues.put("Email", email.toString());
        contentValues.put("Full_name", name.toString());
        contentValues.put("Address", add.toString());
        contentValues.put("Phone_no", phone.toString());

        Cursor cursor = sqLiteDatabase.rawQuery("select * from SHOPPODETAILS where Phone_no = ?", new String[]{phone});
        if (cursor.getCount() > 0) {
            int updated = sqLiteDatabase.update("SHOPPODETAILS", contentValues, "Phone_no = ?", new String[]{phone});
            if (updated == -1)
                return false;
            else
                return true;
        } else
            return false;
    }


    public Cursor getdataforprofile(String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from SHOPPODETAILS where Phone_no = ?", new String[]{phone});
        return cursor;
    }
}
