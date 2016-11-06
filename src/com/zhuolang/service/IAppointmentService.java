package com.zhuolang.service;

import com.zhuolang.model.Appointment;

import java.util.Date;
import java.util.List;

/**
 * Created by wnf on 2016/10/15.
 * 用户服务类接口，根据业务逻辑进行设置
 *
 * @author wnf
 */
//@Service
public interface IAppointmentService {
    void addAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    void updateDiagnose(int id, String diagnose);

    List<Appointment> findByDocId(int doctorId);

    List<Appointment> findAllAppointment();

    List<Appointment> findAppointmentById(int id);

    List<Appointment> findByPatId(int id);

    List<Appointment> findAppByDId(int doctorId, Date date);

    int CountAppByDIdAndDate(int doctorId, Date date);

    void deleteAppointment(List<Appointment> findAppointment);
}
