package com.example.administrator.app.db;

import com.example.administrator.app.HaiApplication;
import com.example.administrator.app.db.dao.HaiDbCacheDao;
import com.example.administrator.app.db.dao.HaiTestCacheDao;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class DbManager {

    private static  DbManager mManager = new DbManager() ;
    private HaiSqliteOpenHelper mHelper;
    public HaiTestCacheDao mTestCache;
    public HaiDbCacheDao mDbCache;
    public synchronized static  DbManager  getInstance() {
        return mManager;
    }

    public  HaiSqliteOpenHelper getSqliteOpenHelper() {
        if(mHelper == null) {
            mHelper = new HaiSqliteOpenHelper(HaiApplication.mContext);
        }
        return mHelper;
    }

    public void init() {
        if(mTestCache == null) {
            mTestCache = new HaiTestCacheDao();
        }
        if(mDbCache == null) {
            mDbCache = new HaiDbCacheDao();
        }
    }
  }

