package com.example.novelapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.novelapp.R;
import com.example.novelapp.database.Books;
import com.example.novelapp.database.BookshelfDatabase;

public class MainActivity extends AppCompatActivity {

//定义小说简介
    private String DESCRIPE01;
    private String DESCRIPE02;
    private String DESCRIPE03;
    private String DESCRIPE04;
    private String DESCRIPE05;
    private String DESCRIPE06;
    private String DESCRIPE07;
    private String DESCRIPE08;





    SQLiteDatabase dp;
    SQLiteDatabase db;
    BookshelfDatabase bookshelfDatabase;
    Books book;

    private TextView skip;
    private int COUNTDOWN=3;
    private  boolean isSkip=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skip=(TextView) findViewById(R.id.skip);

        //初始化数据库
         bookshelfDatabase = new BookshelfDatabase(this, "bookshelf.db", null, 1);
        book = new Books(this, "book.db", null, 1);
        dp = bookshelfDatabase.getReadableDatabase();
        db = book.getReadableDatabase();
       /* initBook(db,this);
        initBookShelf(dp,this);*/

        //抛弃初始界面
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

       /* if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }*/

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case -1:
                        skip.setText("跳过( "+COUNTDOWN+"s )");
                        break;
                    case 1:
                        if (!isSkip) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            isSkip = true;
                            MainActivity.this.finish();
                        }
                        break;
                }
            }
        };
        //开启线程倒计时，发送信息给handler
        new Thread(new Runnable() {
            @Override
            public void run() {
                //倒计时没有结束就继续倒计时并且显示在Textview上，发送-1给handler
                for (; COUNTDOWN>0; COUNTDOWN--){
                    handler.sendEmptyMessage(-1);
                    if (COUNTDOWN<=0)
                        break;
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //倒计时结束发送1
                handler.sendEmptyMessage(1);
            }
        }).start();


        skip.setOnClickListener(new View.OnClickListener() {  // 设置跳过按键的监听器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                isSkip = true;
                MainActivity.this.finish();
            }
        });
    }

   /* private void initBookShelf (SQLiteDatabase db, Context context) {
        DESCRIPE01="在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……";
        bookshelfDatabase.add(db,"01","01.jpg","圣墟","奇幻",DESCRIPE01,"辰东");

    }
    private void initBook (SQLiteDatabase db, Context context) {

        DESCRIPE01="在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……";
        DESCRIPE02="在箫逸看来，只要是个女人，她就不可能是完美的，必然有漏洞存在。只要有漏洞，那么一切就好办了。人设崩塌系统为您服务。只要目标人设崩塌就可以获得大量的奖励。箫逸：这...？";
        DESCRIPE03="这世界，有儒；有道；有佛；有妖；有术士。警察学校毕业的许七安幽幽醒来，发觉自己身处牢房之中，三日后发配边陲.....他最初的目的只不过自保，顺带在这个没有人权的社会里...";
        DESCRIPE04="骨灰级玩家穿越魔兽，成了吉安娜的亲哥哥，咱现在也是王子了，为了家族存续着想，我是不是该先把自家妹妹的腿打折，免得她以后成为一个弑亲禽兽？等等？怎么一过来就要先死一次？让我看看攻略……";
        DESCRIPE05="特种兵王野，一觉睡醒来到了亮剑世界。 从此，晋西北铁三角之一的李云龙麾下又多一员猛将，独立团的战斗序列中也多了一支传奇部队。晋西北铁三角！";
        DESCRIPE06="江离穿越过来，发现没有金手指，只能老老实实的修炼，五百年后，他成为九州最强者，突然出现一个系统，说能帮宿主从练气期逆袭成为九州第一人。江离看着第一个任务，陷...";
        DESCRIPE07="我是一个准备留学的普通留美留学生，正准备努力学习天天向上，留学归来建设美丽祖国,可是，踏上美利坚的那一刻，一切都不一样了……你拿起了篮球！你走进了球场！ 你在...";
        DESCRIPE08="一切要从一次旅行说起。意识，存在…… 维度之上的注视，心与真理的救赎。 踏入另一个世界吧， 那里…… 正在举行一场惊惧的盛宴！";

        book.add(db,"01","01.jpg","圣墟","奇幻",DESCRIPE01,"辰东");
        book.add(db,"02","02.jpg","我的恋爱画风有些不正常","恋爱",DESCRIPE02,"喜欢红烧带鱼");
        book.add(db,"03","03.jpg","大奉打更人","武侠",DESCRIPE03,"卖报小郎君");
        book.add(db,"04","04.jpg","艾泽拉斯阴影轨迹","游戏",DESCRIPE04,"帅犬弗兰克");
        book.add(db,"05","05.jpg","我在亮剑当战狼","军事",DESCRIPE05,"寂寞剑客");
        book.add(db,"06","06.jpg","大乘期才有逆袭系统","仙侠",DESCRIPE06,"最白的乌鸦");
        book.add(db,"07","07.jpg","我真的在打篮球","体育",DESCRIPE07,"临河羡鱼翁");
        book.add(db,"08","08.jpg","惊惧盛宴","悬疑",DESCRIPE08,"薄情书生");

    }

*/

}
