package cn.christ.com.sqlitedatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyOPenHelper mMyOPenHelper;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMyOPenHelper = new MyOPenHelper(this);
        mDb = mMyOPenHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ContentValues values = new ContentValues();
                values.put("name", "李四");
                values.put("phone", "15278693391");
                long insert = mDb.insert("info", null, values);
                if (insert != -1) {
                    Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "插入失败", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn2:
                int delete = mDb.delete("info", "name=?", new String[]{"李四"});
                Toast.makeText(MainActivity.this, "删除" + delete + "条记录", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn3:
                ContentValues values1 = new ContentValues();
                values1.put("name", "黑鬼");
                int update = mDb.update("info", values1, "name=?", new String[]{"李四"});
                Toast.makeText(MainActivity.this, "更新" + update + "条记录", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn4:
                Cursor cursor = mDb.query("info", null, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String id = cursor.getString(0);
                        String name = cursor.getString(1);
                        String phone = cursor.getString(2);
                        Log.d("christ", "-----" + id + "---" + name + "----" + phone);
                    }
                }
                cursor.close();
                break;
        }
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }
}
