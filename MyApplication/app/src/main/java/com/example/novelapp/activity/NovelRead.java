package com.example.novelapp.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.novelapp.Fragment.BookshelfFragment;
import com.example.novelapp.Fragment.KindFragment;
import com.example.novelapp.Fragment.RecentReadFragment;
import com.example.novelapp.R;
import com.example.novelapp.adapter.TabFragmentPagerAdapter;
import com.example.novelapp.book.model.BookLab;

import java.util.ArrayList;
import java.util.List;

public class NovelRead extends AppCompatActivity {
    private RadioGroup bottomRG;
    private RadioButton bookshelfRB;
    private RadioButton rankingRB;
    private RadioButton categoryRB;
    private TextView title;
    private List<Fragment> fragmentList= new ArrayList<>();
    private TabFragmentPagerAdapter fragmentadapter;
    private ViewPager2 viewPager;
    private ImageView search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_read);

        BookLab.newInstance(this).getBookList();
        bottomRG = findViewById(R.id.main_bottomRG);
        title = findViewById(R.id.main_title);
        bookshelfRB = findViewById(R.id.main_bottom_bookshelf);
        rankingRB = findViewById(R.id.main_bottom_ranking);
        categoryRB = findViewById(R.id.main_bottom_category);
        viewPager = findViewById(R.id.main_viewPager);
//初始化Fragment

        BookshelfFragment bookShelfFragment = new BookshelfFragment();//想访问这个


        RecentReadFragment rankingFragment = new RecentReadFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isMale", true);
        rankingFragment.setArguments(bundle);
        KindFragment categoryFragment = new KindFragment();
        fragmentList.add(bookShelfFragment);//想访问这个
        fragmentList.add(categoryFragment);
        fragmentList.add(rankingFragment);


//给viewPager设置适配器
        fragmentadapter = new TabFragmentPagerAdapter(this, fragmentList);
        viewPager.setAdapter(fragmentadapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        Log.d("01", String.valueOf(bookShelfFragment.getTag()));
//限制底部按钮大小
        Drawable drawable = getResources().getDrawable(R.mipmap.bookshelf_red);
        drawable.setBounds(0, 0, 70, 70);
        bookshelfRB.setCompoundDrawables(null, drawable, null, null);
        drawable = getResources().getDrawable(R.mipmap.ranking);
        drawable.setBounds(0, 0, 70, 70);
        rankingRB.setCompoundDrawables(null, drawable, null, null);
        drawable = getResources().getDrawable(R.mipmap.category);
        drawable.setBounds(0, 0, 70, 70);
        categoryRB.setCompoundDrawables(null, drawable, null, null);

        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        search=findViewById(R.id.main_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NovelRead.this, SearchActivity.class));
            }
        });


//底部按钮点击样式转化
        bottomRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.main_bottom_bookshelf:
                        index = 0;
                        title.setText("书架");
                        Drawable drawable = getResources().getDrawable(R.mipmap.bookshelf_red);
                        drawable.setBounds(0, 0, 70, 70);
                        bookshelfRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.ranking);
                        drawable.setBounds(0, 0, 70, 70);
                        rankingRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.category);
                        drawable.setBounds(0, 0, 70, 70);
                        categoryRB.setCompoundDrawables(null, drawable, null, null);
                        break;

                    case R.id.main_bottom_category:
                        index = 1;
                        title.setText("分类");
                        drawable = getResources().getDrawable(R.mipmap.bookshelf);
                        drawable.setBounds(0, 0, 70, 70);
                        bookshelfRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.ranking);
                        drawable.setBounds(0, 0, 70, 70);
                        rankingRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.category_red);
                        drawable.setBounds(0, 0, 70, 70);
                        categoryRB.setCompoundDrawables(null, drawable, null, null);
                        break;

                    case R.id.main_bottom_ranking:
                        index = 2;
                        title.setText("最近浏览");
                        drawable = getResources().getDrawable(R.mipmap.bookshelf);
                        drawable.setBounds(0, 0, 70, 70);
                        bookshelfRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.ranking_red);
                        drawable.setBounds(0, 0, 70, 70);
                        rankingRB.setCompoundDrawables(null, drawable, null, null);
                        drawable = getResources().getDrawable(R.mipmap.category);
                        drawable.setBounds(0, 0, 70, 70);
                        categoryRB.setCompoundDrawables(null, drawable, null, null);
                        break;
                }
                viewPager.setCurrentItem(index);
            }

        });

    }


}
