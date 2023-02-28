package com.example.novelapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.novelapp.R;
import com.example.novelapp.activity.KindOfActivity;


public class KindFragment extends Fragment {
    Button qihuan;
    Button lianai;
    Button wuxia;
    Button xianxia;
    Button junshi;
    Button xuanyi;
    Button youxi;
    Button tiyu;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.kindsfragment, container, false);
        qihuan=view.findViewById(R.id.qihuan);
        lianai=view.findViewById(R.id.lianai);

        junshi=view.findViewById(R.id.junshi);
        wuxia=view.findViewById(R.id.wuxia);

        youxi=view.findViewById(R.id.youxi);
        xianxia=view.findViewById(R.id.xianxia);

        tiyu=view.findViewById(R.id.tiyu);
        xuanyi=view.findViewById(R.id.xuanyi);

     /*   Drawable drawable = getResources().getDrawable(R.drawable.qihuanback);
        drawable.setBounds(0, 0, 100, 194);
        qihuan.setCompoundDrawables(null, drawable, null, null);*/




        Intent intent = new Intent(getActivity(), KindOfActivity.class);

        qihuan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String id= "0";
        intent.putExtra("id",id);
        startActivity(intent);
    }
});

        lianai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "1";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        junshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "2";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        wuxia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "3";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        youxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "4";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        xianxia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "5";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        tiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "6";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        xuanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= "7";
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });







        return view;
    }

}
