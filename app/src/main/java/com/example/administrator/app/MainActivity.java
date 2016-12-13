package com.example.administrator.app;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.app.db.DbManager;
import com.example.administrator.app.db.dao.HaiTestCacheDao;
import com.example.administrator.app.db.entity.Student;
import com.example.administrator.dbdemo.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbManager mDbmanager;
    TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findView(R.id.textView);
        mDbmanager = DbManager.getInstance();
        mDbmanager.init();
    }

    int i = 0;
    public void onSave(View v) {
        Student stu  = new Student();
        stu.setName("lisa");
        stu.setAge(i);
        stu.setLike("pingpang");
        long insert = mDbmanager.mTestCache.insert(stu);
        Toast.makeText(this, "insert =" + insert, Toast.LENGTH_SHORT).show();
        i++;
    }

    public void onRestore(View v) {
        List<Student> stu = mDbmanager.mTestCache.query("name = ?", new String[]{"lisa"});
        StringBuffer sb = new StringBuffer();
        if(stu != null && stu.size() > 0) {
          for (int i  = 0 ; i < stu.size() ; i++) {
              Student s = stu.get(i);
              sb.append("name =" +s.getName() + "age=" +s.getAge() + "like =" +s.getLike() + "\n");
          }
        }
        mTv.setText(sb.toString());
    }

    public void onUpdate(View v) {
        ContentValues cv = new ContentValues();
        cv.put(HaiTestCacheDao.STU_LIKE , "baseketball");
        cv.put(HaiTestCacheDao.STU_AGE , 189);
        int update = mDbmanager.mTestCache.update(cv, "age=?", new String[]{"0"});
        mTv.setText("update=" +update);
    }

    public void onDelete(View v) {
        int delete = mDbmanager.mTestCache.delete("age=?", new String[]{"2"});
        mTv.setText("delete=" +delete);
    }

    public <T extends View>T findView(int res) {
        return (T)findViewById(res);
    }
}
