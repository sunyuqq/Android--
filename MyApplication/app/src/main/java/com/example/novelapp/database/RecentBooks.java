package com.example.novelapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class RecentBooks extends SQLiteOpenHelper {

    public static final String CREATE_BOOKSHELF = "create table recentbooks ("
            +" id text PRIMARY KEY,"
            +"img text,"
            +"bookname text,"
            +"tag text,"
            +"descripe text,"
            +"author text)";

    private SQLiteDatabase db;
    private Context mContext;


    public RecentBooks(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOKSHELF);

    }
    public void add(SQLiteDatabase db, String id, String img, String bookname, String tag, String descripe, String author){
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("img",img);
        values.put("bookname",bookname);
        values.put("tag",tag);
        values.put("descripe",descripe);
        values.put("author",author);
        db.insert("recentbooks", null, values);
        Log.e("", "Insert succeeded");
        values.clear();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists recentbooks");
        onCreate(sqLiteDatabase);
    }

}
