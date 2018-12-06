package com.example.tarunmittal.myapplication.DataClass;

public class Suspension {

    String reason;

    String date;

    public String getFine() {

        return fine;
    }

    public void setFine(String fine) {

        this.fine = fine;
    }

    String fine;

    public String getReason() {

        return reason;
    }

    public String getDate() {

        return date;
    }

    public Suspension(String reason, String date, String fine) {


        this.reason = reason;
        this.date = date;
        this.fine =fine;
    }
}
