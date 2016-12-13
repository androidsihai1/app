package com.example.administrator.app.db.entity;

import java.io.Serializable;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class Student implements Serializable {
    private static final long serialVersionUID = -2358423767820104927L;
    private String name;
    private int age;
    private String like;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name =" + name + " age=" +age + "like" + like;
    }
}
