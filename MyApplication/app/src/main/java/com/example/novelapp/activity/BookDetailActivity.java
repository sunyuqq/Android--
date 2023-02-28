package com.example.novelapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novelapp.Object.Bookobj;
import com.example.novelapp.R;
import com.example.novelapp.adapter.BookshelfAdapter;
import com.example.novelapp.database.BookshelfDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookDetailActivity  extends AppCompatActivity {
    InputStream inputStream;
    private Context context;
    AssetManager assetManager =null;

    FileInputStream fs;
    Bitmap bitmap;


    SQLiteDatabase db;
    BookshelfDatabase bookshelfDatabase;

    private ImageView imageView;
    private TextView bookname;
    private TextView tag;
    private TextView author;
    private TextView descripe;

    //��������鱾ʱˢ��listview
    private List<Bookobj> s = new ArrayList<>();
    private BookshelfDatabase dbHelper;
//    private ViewPager2 viewPager;
    private BookshelfAdapter newBookshelfadapter;


    String del_id;
    String del_img;
    String de_bookname;
    String de_tag;
    String de_descripe;
    String de_author;
    Button addbookshelf;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        //���ô˽���Ϊ����
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        //��ʼ������
        init();
        addbookshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashBookshelf();
            }
        });
    }

    //��ʼ��ҳ�����
    private void init(){
         imageView=(ImageView) findViewById(R.id.detai_img);
         bookname=(TextView) findViewById(R.id.detai_bookname);
         tag=(TextView) findViewById(R.id.detai_tag);
         author=(TextView) findViewById(R.id.detai_author);
         descripe=(TextView) findViewById(R.id.detai_descripe);
        addbookshelf=(Button) findViewById(R.id.addbooksgelf);

        Intent intent=getIntent();
        del_id=intent.getStringExtra("id");
        del_img=intent.getStringExtra("img");
        de_bookname=intent.getStringExtra("bookname");
        de_tag=intent.getStringExtra("tag");
        de_descripe=intent.getStringExtra("descripe");
        de_author=intent.getStringExtra("author");


        try {
            assetManager=this.getAssets();
            inputStream = assetManager.open("image/"+del_img);
            bitmap= BitmapFactory.decodeStream(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
        bookname.setText(getString(R.string.Cbookname)+de_bookname);
        bookname.setGravity(Gravity.CENTER_VERTICAL);
        bookname.setTextSize(25);
        tag.setText(getString(R.string.Ctag)+de_tag);
        tag.setGravity(Gravity.CENTER_VERTICAL);
        tag.setTextSize(15);
        author.setText(getString(R.string.Cauthor)+de_author);
        author.setGravity(Gravity.CENTER_VERTICAL);
        author.setTextSize(15);
        descripe.setText(getString(R.string.Cdescripe)+de_descripe);
        descripe.setTextSize(20);
    }

    public void flashBookshelf(){
        dbHelper = new BookshelfDatabase(this, "bookshelf.db", null, 1);
        SQLiteDatabase db01 = dbHelper.getWritableDatabase();
        Cursor cursor = db01.query("bookshelf", null, "id=?", new String[]{del_id}, null, null, null);

        Log.d("01", String.valueOf(cursor.getCount()));
        Log.d("01",del_id);
        //判断书架有没有
        if (cursor.getCount()==0) {

                    bookshelfDatabase = new BookshelfDatabase(BookDetailActivity.this, "bookshelf.db", null, 1);
                    db = bookshelfDatabase.getReadableDatabase();
                    bookshelfDatabase.add(db,del_id,del_img,de_bookname,de_tag,de_descripe,de_author);
                    s.add(new Bookobj(del_id,del_img,de_bookname,de_tag,de_descripe,de_author));
                    addbookshelf.setEnabled(false);
                    addbookshelf.setText(R.string.addshelf);

                }
            else{

                    addbookshelf.setEnabled(false);
                    addbookshelf.setText(R.string.addshelf);
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle(" ")
                            .setMessage(R.string.alreadybook)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(BookDetailActivity.this, R.string.dontaddagine, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    alertDialog.show();
        }
        cursor.close();
    }





}
