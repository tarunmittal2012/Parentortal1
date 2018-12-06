package com.example.tarunmittal.myapplication.DataClass;

public class Placement {

    private String cName,date,isSelected,isEligible;
    private String jobProfile,salary,location,about;

    public Placement(String cName, String date, String isSelected, String isEligible, String jobProfile, String salary, String location, String about) {

        this.cName = cName;
        this.date = date;
        this.isSelected = isSelected;
        this.isEligible = isEligible;
        this.jobProfile = jobProfile;
        this.salary = salary;
        this.location = location;
        this.about = about;
    }

    public String getJobProfile() {


        return jobProfile;
    }

    public String getSalary() {

        return salary;
    }

    public String getLocation() {

        return location;
    }

    public String getAbout() {

        return about;
    }

    public Placement(String cName, String date, String isSelected, String isEligible) {

        this.cName = cName;
        this.date = date;
        this.isSelected = isSelected;
        this.isEligible = isEligible;
    }

    public String getcName() {


        return cName;
    }

    public String getDate() {

        return date;
    }

    public String getIsSelected() {

        return isSelected;
    }

    public String getIsEligible() {

        return isEligible;
    }
}
