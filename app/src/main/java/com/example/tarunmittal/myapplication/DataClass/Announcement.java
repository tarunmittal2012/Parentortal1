package com.example.tarunmittal.myapplication.DataClass;

public class Announcement {

    String type;

    String date;

    String message;

    String uploadBy;

    public String getUploadBy() {

        return uploadBy;
    }

    public String getDetail() {

        return detail;
    }

    String detail;

    public String getType() {

        return type;
    }

    public String getDate() {

        return date;
    }

    public String getMessage() {

        return message;
    }

    public Announcement(String type, String date, String message,String detail,String uploadBy) {


        this.type = type;
        this.date = date;
        this.detail =detail;
        this.message = message;
        this.uploadBy = uploadBy;
    }
}
