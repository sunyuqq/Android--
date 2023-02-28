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

public class BookshelfAdapter extends ArrayAdapter<Bookobj> {
    private int resourceId;
    private List<Bookobj> list;
    FileInputStream fs;
    Bitmap bitmap;
    Bookobj contacts;

    InputStream inputStream;
    private Context context;
    AssetManager assetManager =null;

    public BookshelfAdapter(Context context, int textViewResourceId, List<Bookobj> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        list = objects;
        this.context=context;
    }
    public View getView(int position, View CVIEW, ViewGroup parent) {
        final ViewHolder viewHolder;

         contacts = getItem(position);
        if (CVIEW == null){
            viewHolder = new ViewHolder();
            CVIEW = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder.imageView =(ImageView) CVIEW.findViewById(R.id.item_book_cover);
            viewHolder.bookname = (TextView) CVIEW.findViewById(R.id.item_book_name);
            viewHolder.author=(TextView) CVIEW.findViewById(R.id.item_book_author);
            viewHolder.tag=(TextView) CVIEW.findViewById(R.id.item_book_type);
            viewHolder.descripe=(TextView) CVIEW.findViewById(R.id.item_book_intro);
            CVIEW.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) CVIEW.getTag();
        }
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
        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.bookname.setText(contacts.getBookname());
        viewHolder.author.setText(contacts.getAuthor());
        viewHolder.tag.setText(contacts.getTag());
        viewHolder.descripe.setText(contacts.getDescripe());
        return CVIEW;
    }

    public final static class ViewHolder{
        TextView bookname,author,tag,descripe;
        ImageView imageView;
    }


}
