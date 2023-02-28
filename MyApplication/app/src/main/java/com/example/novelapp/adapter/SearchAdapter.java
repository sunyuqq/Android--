package com.example.novelapp.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.novelapp.Object.Bookobj;
import com.example.novelapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<Bookobj> {
    private int resourceId;
    private List<Bookobj> list;
    FileInputStream fs;
    Bitmap bitmap;

    InputStream inputStream;
    private Context context;
    AssetManager assetManager =null;
    public SearchAdapter(Context context, int textViewResourceId, List<Bookobj> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        list = objects;
        this.context=context;
    }
    public View getView(int position, View CVIEW, ViewGroup parent) {
        final BookshelfAdapter.ViewHolder viewHolder;

        Bookobj contacts = getItem(position);
        if (CVIEW == null){
            viewHolder = new BookshelfAdapter.ViewHolder();
            CVIEW = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            ImageView imageView=(ImageView) CVIEW.findViewById(R.id.item_book_cover);

            try {

                /*fs=new FileInputStream(Environment.getExternalStorageDirectory().getPath()+"/bookimg/"+contacts.getImg());*/
                /*bitmap=BitmapFactory.decodeStream(fs);*/
                assetManager=context.getAssets();
                inputStream = assetManager.open("image/"+contacts.getImg());
                bitmap= BitmapFactory.decodeStream(inputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);

            viewHolder.bookname = (TextView) CVIEW.findViewById(R.id.item_book_name);
            viewHolder.bookname.setText(contacts.getBookname());
            viewHolder.author=(TextView) CVIEW.findViewById(R.id.item_book_author);
            viewHolder.author.setText(contacts.getAuthor());
            viewHolder.tag=(TextView) CVIEW.findViewById(R.id.item_book_type);
            viewHolder.tag.setText(contacts.getTag());
            viewHolder.descripe=(TextView) CVIEW.findViewById(R.id.item_book_intro);
            viewHolder.descripe.setText(contacts.getDescripe());

        } else {
            viewHolder = (BookshelfAdapter.ViewHolder) CVIEW.getTag();
        }

        return CVIEW;
    }

    public final static class ViewHolder{
        TextView bookname,author,tag,descripe;
    }
    public Bitmap stringToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}