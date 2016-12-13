package com.example.administrator.app.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrator.app.db.entity.HaiDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class HaiDbCacheDao extends BaseDao<HaiDb> {

    public static final String TABLE_NAME = "hai_db_cache";
    public static final String CACHE_CONTENT = "content";
    public static final String CACHE_UPDATE_TIME = "cache_update_time";
    public static final String CACHE_EXPIRE_TIME = "cache_expire_time";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        String [] columns = {CACHE_CONTENT , CACHE_UPDATE_TIME , CACHE_EXPIRE_TIME};
        return columns;
    }

    @Override
    public String createTableSql() {
        String sql = "CREATE TABLE " + TABLE_NAME + "('" +CACHE_CONTENT + "' BLOB NOT NULL ,'"+ CACHE_UPDATE_TIME+ "' INTEGER DEFAULT 0,'" + CACHE_EXPIRE_TIME + "' INTEGER DEFAULT 0" +")";
        return sql;
    }

    @Override
    public ContentValues obj2Cv(HaiDb record) {
        if(record == null || record.getContent() == null) return null;
        ContentValues cv = new ContentValues();
        cv.put(CACHE_CONTENT , record.getContent());
        cv.put(CACHE_UPDATE_TIME , record.getUpdateTime());
        cv.put(CACHE_EXPIRE_TIME , record.getExpireTime());
        return cv;
    }

    @Override
    public HaiDb cv2Obj(ContentValues cv) {
        if(cv == null) return null;
        byte[] b = cv.getAsByteArray(CACHE_CONTENT);
        long updateTime = cv.getAsLong(CACHE_UPDATE_TIME);
        Long expireTime = cv.getAsLong(CACHE_EXPIRE_TIME);
        HaiDb haiDb = new HaiDb();
        haiDb.setContent(b);
        haiDb.setUpdateTime(updateTime);
        haiDb.setExpireTime(expireTime);
        return haiDb;
    }

    @Override
    public List<HaiDb> cursor2Obj(Cursor cursor) {
        List<HaiDb> lists = new ArrayList<>();
        if(cursor != null) {
            while(cursor.moveToNext()) {
                byte[] content = cursor.getBlob(cursor.getColumnIndex(CACHE_CONTENT));
                long updateTime = cursor.getLong(cursor.getColumnIndex(CACHE_UPDATE_TIME));
                long expireTime = cursor.getLong(cursor.getColumnIndex(CACHE_EXPIRE_TIME));
                HaiDb db = new HaiDb();
                db.setContent(content);
                db.setUpdateTime(updateTime);
                db.setExpireTime(expireTime);
                lists.add(db);
            }
        }
        return lists;
    }


}
