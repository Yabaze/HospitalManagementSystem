package com.thomas.mirakle.hospitalmanagementsystem.activities.adaptor;

public class Doctor {
//Name,contact no,Specialization,Available days,
    private String doctorName,doctorContactNumber,doctorSpecialization,doctorAvailability,doctorTiminig;

    public Doctor(String doctorName, String doctorContactNumber, String doctorSpecialization, String doctorAvailability) {
        this.doctorName = doctorName;
        this.doctorContactNumber = doctorContactNumber;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorAvailability = doctorAvailability;
        this.doctorTiminig="7AM to 11AM";
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    public void setDoctorContactNumber(String doctorContactNumber) {
        this.doctorContactNumber = doctorContactNumber;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorAvailability() {
        return doctorAvailability;
    }

    public void setDoctorAvailability(String doctorAvailability) {
        this.doctorAvailability = doctorAvailability;
    }

    public String getDoctorTiminig() {
        return doctorTiminig;
    }

    public void setDoctorTiminig(String doctorTiminig) {
        this.doctorTiminig = doctorTiminig;
    }
}
