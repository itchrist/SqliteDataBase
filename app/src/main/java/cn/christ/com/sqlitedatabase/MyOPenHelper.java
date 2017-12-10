package cn.christ.com.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Christ on 2017/12/9.
 */

public class MyOPenHelper extends SQLiteOpenHelper {

    public MyOPenHelper(Context context) {
        super(context, "christ.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("alter table info add phone varchar(20)");
    }
}
