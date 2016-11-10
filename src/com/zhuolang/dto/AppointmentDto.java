package com.zhuolang.dto;

import java.util.Date;

/**
 * Created by hzg on 2016/11/6.
 */
public class AppointmentDto {

    private int id;
    private int patientId;//病人ID
    private int doctorId;//医师ID
    private String doctor_name;//医师名字
    private Date seeTime;//预约了去就诊日期时间
    private String disease;//病症
    private Date dateTime;//预约时间、
    private String diagnose;//医生诊断
    private double dstar;//评论（星号）
    private int dNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(Date seeTime) {
        this.seeTime = seeTime;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public double getDstar() {
        return dstar;
    }

    public void setDstar(double dstar) {
        this.dstar = dstar;
    }

    public int getdNumber() {
        return dNumber;
    }

    public void setdNumber(int dNumber) {
        this.dNumber = dNumber;
    }


    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", doctor_name='" + doctor_name + '\'' +
                ", seeTime=" + seeTime +
                ", disease='" + disease + '\'' +
                ", dateTime=" + dateTime +
                ", diagnose='" + diagnose + '\'' +
                ", dstar=" + dstar +
                ", dNumber=" + dNumber +
                '}';
    }

}
