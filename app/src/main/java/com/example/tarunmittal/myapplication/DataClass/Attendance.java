package com.example.tarunmittal.myapplication.DataClass;

public class Attendance {

    private String name;

    private String date;

    private String subject;

    private String att;

    private String section;

    private String id;

    private String mentorId;

    public String getCount() {

        return count;
    }


    private String count;

    public Attendance()
    {
    }

    public Attendance(String subject,String count)
    {
        this.subject = subject;
        this.count = count;
    }


    public String getName() {

        return name;
    }

    public String getDate() {

        return date;
    }

    public String getSubject() {

        return subject;
    }

    public String getAtt() {

        return att;
    }

    public String getSection() {

        return section;
    }

    public String getId() {

        return id;
    }

    public String getMentorId() {

        return mentorId;
    }

    public Attendance(String name, String date, String subject, String att, String section, String id, String mentorId) {

        this.name = name;
        this.date = date;
        this.subject = subject;
        this.att = att;
        this.section = section;
        this.id = id;
        this.mentorId = mentorId;
    }
}
