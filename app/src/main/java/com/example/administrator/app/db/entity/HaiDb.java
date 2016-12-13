package com.example.administrator.app.db.entity;

import java.io.Serializable;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class HaiDb implements Serializable {

    private static final long serialVersionUID = -5631629501758864929L;
    private byte [] content;
    private long updateTime;
    private long expireTime;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
