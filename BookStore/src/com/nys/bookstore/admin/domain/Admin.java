package com.nys.bookstore.admin.domain;

public class Admin {
    private String uid;
    private int level=1;

    public Admin() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
