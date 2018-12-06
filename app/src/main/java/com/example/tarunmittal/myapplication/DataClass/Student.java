package com.example.tarunmittal.myapplication.DataClass;

public class Student {

    private String name, reg_no, fname, email, section, stream, fmobile_no, mobile_no, mentor, mentor_reg,
            state, pin, dob, gender, address;

    public Student(String name, String reg_no, String fname, String email, String section, String stream, String fmobile_no, String mobile_no, String mentor, String mentor_reg, String state, String pin, String dob, String gender, String address) {

        this.name = name;
        this.reg_no = reg_no;
        this.fname = fname;
        this.email = email;
        this.section = section;
        this.stream = stream;
        this.fmobile_no = fmobile_no;
        this.mobile_no = mobile_no;
        this.mentor = mentor;
        this.mentor_reg = mentor_reg;
        this.state = state;
        this.pin = pin;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public Student(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public String getReg_no() {

        return reg_no;
    }

    public String getFname() {

        return fname;
    }

    public String getEmail() {

        return email;
    }

    public String getSection() {

        return section;
    }

    public String getStream() {

        return stream;
    }

    public String getFmobile_no() {

        return fmobile_no;
    }

    public String getMobile_no() {

        return mobile_no;
    }

    public String getMentor() {

        return mentor;
    }

    public String getMentor_reg() {

        return mentor_reg;
    }

    public String getState() {

        return state;
    }

    public String getPin() {

        return pin;
    }

    public String getDob() {

        return dob;
    }

    public String getGender() {

        return gender;
    }

    public String getAddress() {

        return address;
    }
}
