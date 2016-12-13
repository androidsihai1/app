package com.example.administrator.app.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrator.app.db.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class HaiTestCacheDao extends BaseDao<Student> {

    public final static String TABLE_NAME = "db_student";
    public final static String STU_NAME = "name";
    public final static String STU_AGE = "age";
    public final static String STU_LIKE = "like";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        String columns [] = {STU_NAME , STU_AGE , STU_LIKE};
        return columns;
    }

    @Override
    public String createTableSql() {
        String sql = "CREATE TABLE " + TABLE_NAME + "('" + STU_NAME + "' TEXT NOT NULL ,'"  + STU_AGE + "' INTEGER DEFAULT 0 ,'" + STU_LIKE +  "' TEXT NOT NULL"+ ")";
        return sql;
    }

    @Override
    public ContentValues obj2Cv(Student record) {
        ContentValues cv = new ContentValues();
        if(record == null) {
          return null;
        }
        cv.put(STU_NAME , record.getName());
        cv.put(STU_AGE ,record.getAge());
        cv.put(STU_LIKE ,record.getLike());
        return cv;
    }

    @Override
    public Student cv2Obj(ContentValues cv) {
        if(cv == null) return  null;
        Student stu  = new Student();
        stu.setName(cv.getAsString(STU_NAME));
        stu.setAge(cv.getAsInteger(STU_AGE));
        stu.setLike(cv.getAsString(STU_LIKE));
        return stu;
    }

    @Override
    public List<Student> cursor2Obj(Cursor cursor) {
        List<Student> students = new ArrayList<>();
        if(cursor != null) {
            while(cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(STU_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(STU_AGE));
                String like = cursor.getString(cursor.getColumnIndex(STU_LIKE));
                Student stu = new Student();
                stu.setName(name);
                stu.setAge(age);
                stu.setLike(like);
                students.add(stu);
            }
        }
        return students;
    }


}
