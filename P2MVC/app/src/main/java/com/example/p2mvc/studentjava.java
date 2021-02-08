package com.example.p2mvc;

public class studentjava {
    //variables
    private int mNoControl;
    private String mNombre;
    private int mScore;

    public studentjava(int mNoControl, String mNombre, int mScore) {
        this.mNoControl = mNoControl;
        this.mNombre = mNombre;
        this.mScore = mScore;
    }

    public int getNoControl() {
        return mNoControl;
    }

    public void setNoControl(int mNoControl) {
        this.mNoControl = mNoControl;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }
}


