package com.example.novelapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.ListFragment;

import com.example.novelapp.Object.Bookobj;
import com.example.novelapp.R;
import com.example.novelapp.activity.BookDetailActivity;
import com.example.novelapp.adapter.BookshelfAdapter;
import com.example.novelapp.book.ReadingActivity;
import com.example.novelapp.book.model.Book;
import com.example.novelapp.book.model.BookLab;
import com.example.novelapp.database.BookshelfDatabase;

import java.util.ArrayList;
import java.util.List;


public class BookshelfFragment extends ListFragment {
    private List<Bookobj> s = new ArrayList<>();

    private BookshelfDatabase dbHelper;
    public BookshelfAdapter adapter;

    private Context context;
    private BookDetailActivity activity;

    ListView listView;
    View view;

    SQLiteDatabase db ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.bookshelffragment, container, false);
        dbHelper = new BookshelfDatabase(getActivity(), "bookshelf.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        adapter = new BookshelfAdapter(getActivity(), R.layout.book_item, s);
        listView =(ListView) view.findViewById(android.R.id.list);
        setListAdapter(adapter);
        init();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示！");
                builder.setMessage("确定删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isSuccess;
                        // 删除listView选择item的同时删除数据库中对应的信息
                        String id = s.get(position).getId();
                        try {
                            db.delete("bookshelf","id=?",new String[]{id});

                            Log.e("position", String.valueOf(position));
                            isSuccess = true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            isSuccess = false;
                        }

                        if (s.remove(position) != null) {
                            System.out.println("Success");
                        } else {
                            System.out.println("Failed");
                        }
                        if (isSuccess) {
                            adapter.notifyDataSetChanged();

                            Toast.makeText(getActivity(), "已删除", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
                // 返回true避免与点击事件冲突
                return true;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        s.clear();
        init();
    }

    public void init(){

        Cursor cursor = db.query("bookshelf", null, null, null, null, null, null);
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
        adapter.notifyDataSetChanged();
        cursor.close();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
      /*  Bookobj contacts = s.get(position);
        String bookname = contacts.getBookname();
        Intent intent=new Intent(getActivity(), PageReadActivity.class);
        intent.putExtra("bookname",bookname+".txt");
        startActivity(intent);*/

        Bookobj contacts = s.get(position);
        List<Book> mBookList = BookLab.newInstance(getActivity()).getBookList();
        for (int n=0;n<mBookList.size();n++) {
            Book bean=   mBookList.get(n);
            if (TextUtils.equals(bean.getBookTitle(),contacts.getBookname())){
                startActivity(ReadingActivity.newIntent(getActivity(), n));
            }
        }

    }

}
