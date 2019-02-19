package com.example.fundsdonation.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UnicodeSetSpanner;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "MyDataBase";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USER = "UserTable";
    private static final String COL_ID ="id";
    private static final String COL_NAME = "Name";
    private static final String COL_Mobile = "Mobile";
    private static final String COL_Email = "Email";
    private static final String COL_Pwd = "Pwd";
    private Context context;


    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDB(Context context)
    {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS = "CREATE TABLE "+TABLE_USER+" ("+COL_ID+" integer primary key AUTOINCREMENT, "+
                COL_NAME+" text, "+COL_Mobile+" text, "+COL_Email+" text, "+COL_Pwd+" text)";
        db.execSQL(CREATE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        onCreate(db);
    }

    public boolean addUser(User user)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,user.getName());
        cv.put(COL_Mobile,user.getMobile());
        cv.put(COL_Email,user.getEmail());
        cv.put(COL_Pwd,user.getPassword());
        long insert = db.insert(TABLE_USER, null, cv);

        return insert > 0 ? true : false;

    }

    public List<User> getUsers(){

        List<User> list = new ArrayList<User>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        cursor.moveToFirst();

        do{
            int _id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(COL_Mobile));
            String email = cursor.getString(cursor.getColumnIndex(COL_Email));
            String pwd = cursor.getString(cursor.getColumnIndex(COL_Pwd));
            list.add(new User(_id,name,mobile,email,pwd));

        }while (cursor.moveToNext());

        return list;
    }

    public boolean isValidUser(String email, String pwd)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE "+COL_Email+" = "+"'"+email+"'"+" AND "+COL_Pwd+" = "+"'"+pwd+"'", null);
        return  cursor.getCount() >0 ? true : false;
    }


    private void showToast(String msg)
    {
       Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

}
