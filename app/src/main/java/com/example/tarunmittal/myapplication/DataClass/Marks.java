package com.example.tarunmittal.myapplication.DataClass;

public class Marks {

    private String section;

    private String treg_no;

    private String subject;

    private String date;

    private String exam_type;

    private String marks;

    public Marks(String section, String treg_no, String subject, String date, String exam_type,String marks) {

        this.section = section;
        this.treg_no = treg_no;
        this.subject = subject;
        this.date = date;
        this.exam_type = exam_type;
this.marks=marks;
    }

    public String getSection() {

        return section;
    }

    public String getTreg_no() {

        return treg_no;
    }

    public String getMarks() {

        return marks;
    }

    public String getSubject() {

        return subject;
    }

    public String getDate() {

        return date;
    }

    public String getExam_type() {

        return exam_type;
    }


}
