package com.example.novelapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.novelapp.R;
import com.example.novelapp.database.UserDataBase;


public class LoginActivity extends AppCompatActivity {
    private String NAME;//用户名
    private String PASSWORD;//密码
    private UserDataBase mSQlite;

    AlertDialog alertDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //设置此界面为竖屏
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        mSQlite=new UserDataBase(this, "User.db", null, 2);
        init();
    }

    private void init(){
        EditText username=(EditText)findViewById(R.id.username);
        EditText password=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.login);
        Button register=(Button)findViewById(R.id.register);


        if(ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        //点击注册跳转到注册
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(LoginActivity.this, Register.class);
                startActivity(intent5);
                finish();
            }
        });
        //点击登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mSQlite.getWritableDatabase();
                NAME = username.getText().toString();
                PASSWORD = password.getText().toString();
                if (NAME.equals("") || PASSWORD.equals("")) {
                     alertDialog = new AlertDialog.Builder(LoginActivity.this)
                            .setTitle(" ")
                            .setMessage("用户名或密码不能为空")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(LoginActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    alertDialog.show();
                }


                // 查询User表中所有的数据
                else {


                    Cursor cursor = db.query("user", null, "name=?", new String[]{NAME}, null, null, null);

                    if(cursor.getCount()==0){
                        alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                .setTitle(" ")
                                .setMessage("该用户未注册！")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(LoginActivity.this, "请先注册！", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        alertDialog.show();


                    }
                    else{
                        cursor.moveToNext();
                        @SuppressLint("Range") String pw = cursor.getString(cursor.getColumnIndex("password"));
                        if (PASSWORD.equals(pw)) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, NovelRead.class);
                            intent.putExtra("username", NAME);
                            intent.putExtra("password", PASSWORD);  //展示账号密码功能

                            startActivity(intent);
                            finish();
                        }

                        else
                        {
                            alertDialog = new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle(" ")
                                    .setMessage("用户名或密码输入错误!")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(LoginActivity.this, "请重新输入用户名或密码", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .create();
                            alertDialog.show();
                        }


                    }
                    cursor.close();
                }
            }
            });
           }
}


