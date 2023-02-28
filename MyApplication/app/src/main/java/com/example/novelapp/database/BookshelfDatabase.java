package com.example.novelapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class BookshelfDatabase extends SQLiteOpenHelper {
    public static class PictureColumns implements BaseColumns {
        public static final String PICTURE = "picture";
    }
    public static final String CREATE_BOOKSHELF = "create table bookshelf ("
            +" id text PRIMARY KEY,"
            +"img text,"
            +"bookname text,"
            +"tag text,"
            +"descripe text,"
            +"author text)";

    private SQLiteDatabase db;
    private Context mContext;


    public BookshelfDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOKSHELF);
        /*initBookShelf(sqLiteDatabase);*/
    }
    public void add(SQLiteDatabase db, String id, String img, String bookname, String tag, String descripe, String author){
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("img",img);
        values.put("bookname",bookname);
        values.put("tag",tag);
        values.put("descripe",descripe);
        values.put("author",author);
        db.insert("bookshelf", null, values);
        Log.e("", "Insert succeeded");
        values.clear();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists bookshelf");
        onCreate(sqLiteDatabase);
    }



/*    private void initBookShelf (SQLiteDatabase db) {
        String DESCRIPE01="在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……";
        add(db,"0","01.jpg","西凉风暴","奇幻",DESCRIPE01,"辰东");

    }*/




}
