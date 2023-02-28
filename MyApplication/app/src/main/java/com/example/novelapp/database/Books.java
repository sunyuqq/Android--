package com.example.novelapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.novelapp.book.model.Book;
import com.example.novelapp.book.model.BookLab;

import java.util.List;

public class Books extends SQLiteOpenHelper {


    public static final String CREATE_BOOK = "create table book ("
            + " id text PRIMARY KEY,"
            + "img text,"
            + "bookname text,"
            + "tag text,"
            + "descripe text,"
            + "author text)";


    private Context mContext;


    public Books(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        initBook(db);

    }

    //数据库，id,书名，标签，描述，作者
    public void add(SQLiteDatabase db, String id, String img, String bookname, String tag, String descripe, String author) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("img", img);
        values.put("bookname", bookname);
        values.put("tag", tag);
        values.put("descripe", descripe);
        values.put("author", author);
        db.insert("book", null, values);
        Log.e("", "Insert succeeded");
        values.clear();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists book");
        onCreate(sqLiteDatabase);
    }

    private void initBook(SQLiteDatabase db) {
        List<Book> mBookList = BookLab.newInstance(mContext).getBookList();
        for (int n=1;n<mBookList.size()+1;n++) {
            Book bean=mBookList.get(n-1);

            /*add(db, n+"", name(bean.getBookTitle()), bean.getBookTitle(), "奇幻", bean.getBookTitle(), "薄情书生");*/
            if(n<10){
                add(db, n+"", "0"+n+".jpg", bean.getBookTitle(), tag(bean.getBookTitle()), desc(bean.getBookTitle()), auth(bean.getBookTitle()));
                    }
            else{
                add(db, n+"", n+".jpg", bean.getBookTitle(), tag(bean.getBookTitle()), desc(bean.getBookTitle()), auth(bean.getBookTitle()));

                  }
        }

    }
public String tag(String str){
String tag = "奇幻";
    switch (str){
        case "西凉风暴":case "乱世文章": case "京城之会": case "忠义孤臣": case "正统王朝":
            tag="军事";
            break;
        case "神剑擒龙": case "圣墟": case "一代真龙": case "重建怒苍":
            tag="奇幻";
            break;
        case "天下第一":case "金榜题名": case "西出阳关":
            tag="武侠";
            break;
        case "十面埋伏": case "神鬼亭外":
            tag="悬疑";
            break;
        case "海上孤鸿":
            tag="恋爱";
            break;
        case "飞剑问道":
            tag="仙侠";
            break;
        case "我真的在打篮球":
            tag="体育";
            break;
        case "网游：我能够掠夺属性":
            tag="游戏";
            break;

    }
        return tag;
}
    public String desc(String str){
        String des = "奇幻";
        switch (str){
            case "海上孤鸿": case "十面埋伏": case "神鬼亭外":case "天下第一":case "金榜题名": case "西出阳关":case "西凉风暴":case "乱世文章": case "京城之会": case "忠义孤臣": case "正统王朝":case "神剑擒龙": case "一代真龙": case "重建怒苍":
                des="《英雄志》为一虚构中国明朝历史的古典小说，借用明英宗土木堡之变为背景，以复辟为舞台，写尽了英雄们与时代间的相互激荡，造反与政变、背叛与殉道……书中无人不可以为英雄，贩夫走卒、市井小民、娼妇与公主、乞丐与皇帝，莫不可以为英雄。";
                break;
            case "圣墟":
                 des="在破败中崛起，在寂灭中复苏。沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……";
                break;
            case "飞剑问道":
                des="在这个世界，有狐仙、河神、水怪、大妖，也有求长生的修行者。　　修行者们，　　开法眼，可看妖魔鬼怪。　　炼一口飞剑，可千里杀敌。　　千里眼、顺风耳，更可探查四方。　　……　　秦府二公子‘秦云’，便是一位修行者……";
                break;
            case "我真的在打篮球":
                des="我是一个准备留学的普通留美留学生，正准备努力学习天天向上，留学归来建设美丽祖国！可是，踏上美利坚的那一刻，一切都不一样了……你拿起了篮球！你走进了球场！你在球场的山呼海啸中‘迷路’了，头脑一片空白！";
                break;
            case "网游：我能够掠夺属性":
                des="《网游：我能够掠夺属性》是白首不相离精心创作的网游小说，恋上你看书网实时更新网游：我能够掠夺属性最新章节并且提供无弹窗阅读，书友所发表的网游：我能够掠夺属性评论，并不代表恋上你看书网赞同或者支持网游：我能够掠夺属性读者的观点。";
                break;

        }
        return des;
    }
    public String auth(String str){
        String auth = "奇幻";
        switch (str){
            case "海上孤鸿": case "十面埋伏": case "神鬼亭外":case "天下第一":case "金榜题名": case "西出阳关":case "西凉风暴":case "乱世文章": case "京城之会": case "忠义孤臣": case "正统王朝":case "神剑擒龙": case "一代真龙": case "重建怒苍":
                auth="孙晓";
                break;
            case "圣墟":
                auth="辰东";
                break;
            case "飞剑问道":
                auth="我吃西红柿";
                break;
            case "我真的在打篮球":
                auth="临河羡鱼翁";
                break;
            case "网游：我能够掠夺属性":
                auth="白首不相离";
                break;

        }
        return auth;
    }



}
