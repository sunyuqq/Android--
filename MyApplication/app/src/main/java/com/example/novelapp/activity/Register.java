package com.example.novelapp.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novelapp.R;
import com.example.novelapp.database.UserDataBase;

public class Register extends AppCompatActivity {

    private UserDataBase mSQlite;
    private EditText username;
    private EditText password;
    private Button backlogin;
    private Button CR;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = (EditText)findViewById(R.id.username);
        password =(EditText)findViewById( R.id.password);
        backlogin=(Button)findViewById(R.id.back);
        CR=(Button) findViewById(R.id.confirmR);

        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        mSQlite=new UserDataBase(this, "User.db", null, 2);
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,LoginActivity.class);
               startActivity(intent);
            }
        });


        CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = username.getText().toString().trim();
                String PASSWORD = password.getText().toString().trim();

                //避免重复注册
                SQLiteDatabase db = mSQlite.getWritableDatabase();
                Cursor cursor = db.query("user", null, "name=?", new String[]{NAME}, null, null, null);
                if (PASSWORD.length()<8) {
                    alertDialog = new AlertDialog.Builder(Register.this)
                            .setTitle(" ")
                            .setMessage("密码不能少于8位")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Register.this, "请重新输入密码", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    alertDialog.show();
                    cursor.close();
                }
                else if (NAME.isEmpty() || PASSWORD.isEmpty()) {
                    alertDialog = new AlertDialog.Builder(Register.this)
                            .setTitle(" ")
                            .setMessage("用户名或密码不能为空")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Register.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    alertDialog.show();
                    cursor.close();
                } else {

                    if (cursor.getCount() == 0) {
                        SQLiteDatabase dp = mSQlite.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("name", NAME);
                        values.put("password", PASSWORD);
                        dp.insert("user", null, values);
                        Log.e("", "Insert succeeded");
                        values.clear();
                        /* alertDialog.dismiss();*/
                        Intent intent1 = new Intent(Register.this, LoginActivity.class);
                        startActivity(intent1);
                        finish();
                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        alertDialog = new AlertDialog.Builder(Register.this)
                                .setTitle(" ")
                                .setMessage("请勿重复注册")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(Register.this, "请重新注册", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .create();
                        alertDialog.show();
                        username.setText("");
                        password.setText("");

                    }

                }
                cursor.close();
            }

        });

    }
}
