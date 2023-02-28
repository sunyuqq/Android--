package com.example.novelapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novelapp.Object.Bookobj;
import com.example.novelapp.R;
import com.example.novelapp.adapter.SearchAdapter;
import com.example.novelapp.database.Books;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private SearchAdapter adapter;
    private List<Bookobj> s = new ArrayList<>();
    private String keytext;
    private Books dbHelper;
    Cursor cursor;


    public Intent intent2;
    private String del_img;
    private String del_bookname;
    private String del_tag;
    private String del_author;
    private String del_descripe;
private String del_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        adapter = new SearchAdapter(SearchActivity.this, R.layout.book_item, s);
        ListView listView = (ListView) findViewById(R.id.searchl);
        listView.setAdapter(adapter);
        EditText ed=findViewById(R.id.edit_text);
        Button search=(Button) findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keytext=ed.getText().toString();
                dbHelper = new Books(SearchActivity.this, "book.db", null, 1);
                dbHelper.getWritableDatabase();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                cursor = db.query("book", null, "bookname=?", new String[]{keytext}, null, null, null);

                if(cursor.getCount()==0){
                    Toast.makeText(SearchActivity.this, "找不到该书", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (cursor.moveToFirst()) {
                        do {
                            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                            @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex("img"));
                            @SuppressLint("Range") String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                            @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
                            @SuppressLint("Range") String descripe = cursor.getString(cursor.getColumnIndex("descripe"));
                            @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                            Log.d("01",bookname);
                            s.add(new Bookobj(id,img, bookname,tag,descripe,author));
                        } while (cursor.moveToNext());
                    }

                }
                cursor.close();
                listView.setAdapter(adapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent2=new Intent(SearchActivity.this, BookDetailActivity.class);
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

}
