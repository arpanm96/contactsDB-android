package com.example.amajumder.contacts;

import java.io.Serializable;

/**
 * Created by amajumder on 9/12/2016.
 */
public class Contact implements Serializable{
    private String name;
    private long phNo;
    private String mailID;
    private int iconID;
    public Contact(String name, long phNo, String mailID,int iconID) {
        this.name = name;
        this.phNo = phNo;
        this.mailID = mailID;
        this.iconID = iconID;
    }

    public String getName() {
        return name;
    }

    public long getPhNo() {
        return phNo;
    }

    public String getMailID() {
        return mailID;
    }

    public int getIconID() {
        return iconID;
    }
}
