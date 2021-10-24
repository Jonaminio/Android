package com.example.basic;

public class Checklist {
    private int mId;
    private String mDescription;
    private boolean mActive;

    public Checklist(int mId, String mDescription, boolean mActive) {
        this.mId = mId;
        this.mDescription = mDescription;
        this.mActive = mActive;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean ismActive() {
        return mActive;
    }

    public void setmActive(boolean mActive) {
        this.mActive = mActive;
    }
}