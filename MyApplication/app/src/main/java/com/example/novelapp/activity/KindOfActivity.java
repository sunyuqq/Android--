package com.example.novelapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novelapp.Fragment.RecentReadFragment;
import com.example.novelapp.Object.Bookobj;
import com.example.novelapp.R;
import com.example.novelapp.adapter.KindOfAdapter;
import com.example.novelapp.database.Books;
import com.example.novelapp.database.BookshelfDatabase;
import com.example.novelapp.database.RecentBooks;

import java.util.ArrayList;
import java.util.List;

public class KindOfActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<Bookobj> s = new ArrayList<>();

    private Books dbHelper;
    private KindOfAdapter adapter;
    RecentBooks recentBooks;
    SQLiteDatabase db;
    String id;

    public Intent intent2;
    private String del_img;
    private String del_bookname;
    private String del_tag;
    private String del_author;
    private String del_descripe;
    private String del_id;

    Cursor cursor;

    String QIHUAN="奇幻";
    String LIANAI="恋爱";
    String JUNSHI="军事";
    String WUXIA="武侠";
    String YOUXI="游戏";
    String XIANXIA="仙侠";
    String TIYU="体育";
    String XUANYI="悬疑";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kindof);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        init();
        adapter = new KindOfAdapter(KindOfActivity.this, R.layout.kind_item, s);
        ListView listView = (ListView) findViewById(R.id.kind_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                recentBooks = new RecentBooks(KindOfActivity.this, "recentbooks.db", null, 1);
                db = recentBooks.getReadableDatabase();
                recentBooks.add(db,s.get(i).getId(),s.get(i).getImg(),s.get(i).getBookname(),s.get(i).getTag(),s.get(i).getDescripe(),s.get(i).getAuthor());
                Log.d("01", "添加成功！！！！！！！！！！！");

                intent2=new Intent(KindOfActivity.this, BookDetailActivity.class);
                intent2.putExtra("id",s.get(i).getId());
                intent2.putExtra("img",s.get(i).getImg());
                intent2.putExtra("bookname",s.get(i).getBookname());
                intent2.putExtra("tag",s.get(i).getTag());
                intent2.putExtra("descripe",s.get(i).getDescripe());
                intent2.putExtra("author",s.get(i).getAuthor());
                startActivity(intent2);
            }
        });

    }


    public void init(){
        dbHelper = new Books(this, "book.db", null, 1);
        dbHelper.getWritableDatabase();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String TAG[]={QIHUAN,LIANAI,JUNSHI,WUXIA,YOUXI,XIANXIA,TIYU,XUANYI};
        for(int i=0;i<8;i++){
            if(id.equals(i+"")){
                cursor = db.query("book", null, "tag=?", new String[]{TAG[i]}, null, null, null);
            }

        }
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex("img"));
                @SuppressLint("Range") String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
                @SuppressLint("Range") String descripe = cursor.getString(cursor.getColumnIndex("descripe"));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                s.add(new Bookobj(id,img, bookname,tag,descripe,author));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
