package com.example.eltobgy.spotlight.models;

/**
 * Created by Eltobgy on 02-Jul-18.
 */

public class News {
    private String mTitle;
    private String mDescription;
    private int mImageResourceId;



    public News(String mTitle, String mDescription, int mImageResourceId) {

        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImageResourceId = mImageResourceId;
    }

    public News(String mTitle, String mDescription) {

        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
