package com.example.eltobgy.spotlight.models;

/**
 * Created by Eltobgy on 02-Jul-18.
 */

public class News {
    private String mTitle;
    private String mDescription;
    private String mImageResourceId = "";



    public News(String mTitle, String mDescription, String mImageResourceId) {

        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImageResourceId = mImageResourceId;
    }

    public News(String mTitle, String mDescription) {

        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(String mImageResourceId) {
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
