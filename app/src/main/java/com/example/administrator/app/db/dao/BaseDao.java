package com.example.administrator.app.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.app.db.DbManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public abstract class BaseDao<T> {

    public abstract String getTableName();

    public abstract String[] getColumns();

    public abstract String createTableSql();


    public synchronized SQLiteDatabase getReaderableDatabase() {
        return DbManager.getInstance().getSqliteOpenHelper().getReadableDatabase();
    }

    public synchronized SQLiteDatabase getWriteableDatabase() {
        return DbManager.getInstance().getSqliteOpenHelper().getWritableDatabase();
    }


    public abstract ContentValues obj2Cv(T record);

    public abstract T cv2Obj(ContentValues cv);

    public abstract List<T> cursor2Obj(Cursor cursor);

    public synchronized int getCount(String selectionClause , String [] selectionArgs){
        int result = -1;
        Cursor cursor = null;
        try{
            SQLiteDatabase db = getReaderableDatabase();
            cursor = db.query(getTableName(), new String[]{"count(*)"}, selectionClause, selectionArgs, null, null, null);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToNext();
                result = cursor.getInt(cursor.getColumnIndex("count(*)"));
            }
        }catch (Exception e) {
            result = -1;
        } finally{
            if(cursor != null) cursor.close();
        }
        return result;
    }

     /**
     * 插入新的记录
     *
     * @param record 实体类
     * @return 返回正数代表成功，-1代表失败
     */
    public synchronized long insert(T record) {
        long result = -1;
        try {
            SQLiteDatabase db = getWriteableDatabase();
            ContentValues values = obj2Cv(record);
            if (values != null) {
                result = db.insertOrThrow(getTableName(), null, values);
            }
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * 插入新数据（当数据存在时，改为更新字段）
     * @param record
     * @param whereClause (whereClause 和 whereArgs只有在更新时，才会作为更新条件使用)
     * @param whereArgs
     * @return
     */
    public synchronized long set(T record , String whereClause , String [] whereArgs) {
        long  result = -1;
        ContentValues cv = null;
        try{
            cv = obj2Cv(record);
            if(cv == null) return result;
            SQLiteDatabase db = getWriteableDatabase();
            result = db.insertOrThrow(getTableName(), null, cv);
        }catch (SQLiteConstraintException e) {
            update(cv , whereClause , whereArgs);
        } catch (Exception e) {
            result = -1;
        }
          return result;
    }

    /**
     * 更新数据
     *
     * @param values
     * @param whreClause
     * @param whereArgs
     * @return
     */
    public synchronized int update(ContentValues values, String whreClause, String[] whereArgs) {
        int result = -1;
        try {
            SQLiteDatabase db = getWriteableDatabase();
            result = db.update(getTableName(), values, whreClause, whereArgs);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * 删除数据
     *
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public synchronized int delete(String whereClause, String[] whereArgs) {
        int result = -1;
        try {
            SQLiteDatabase db = getWriteableDatabase();
            result = db.delete(getTableName(), whereClause, whereArgs);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * 查询数据
     *
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return
     */
    public synchronized List<T> query(String selection,
                                      String[] selectionArgs, String groupBy, String having,
                                      String orderBy) {
        List<T> lists = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getReaderableDatabase();
            cursor = db.query(getTableName(), getColumns(), selection, selectionArgs, groupBy, having, orderBy);
            lists.addAll(cursor2Obj(cursor));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(cursor != null)
            cursor.close();
        }
        return lists;
    }

    public synchronized List<T> query(String selection,
                                      String[] selectionArgs) {
        return query(selection, selectionArgs, null, null, null);
    }

    /**
     * 原始语句查询
     *
     * @param sql
     * @param selectionArgs
     * @return
     */
    public synchronized Cursor rawQuery(String sql, String[] selectionArgs) {
        SQLiteDatabase db = getWriteableDatabase();
        return db.rawQuery(sql, selectionArgs);

    }


}
