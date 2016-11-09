package com.zhuolang.service.impl;

import com.zhuolang.dao.IAppointmentDao;
import com.zhuolang.dao.impl.UserDao;
import com.zhuolang.dto.AppointmentDto;
import com.zhuolang.model.Appointment;
import com.zhuolang.model.User;
import com.zhuolang.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wnf on 2016/10/15.
 */
@Service("appointmentService")
public class AppointmentService implements IAppointmentService {

    // 注入服务层，操作数据持久化
    @Autowired
    IAppointmentDao dao;
    @Autowired
    UserDao userDao;

    /**
     * 业务逻辑操作
     */

    @Override
    public void addAppointment(Appointment appointment) {
        try {
            dao.save(appointment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //计算一位医师当天预约的人数
    public int CountAppByDIdAndDate(int doctorId, Date date) {
        String hql = "select count(*) from Appointment where doctorId=? and seeTime=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(doctorId);
        idObject.add(date);
        return dao.count(hql, idObject).intValue();
    }

    @Override
    public List<Appointment> findAppByDId(int doctorId, Date date) {
        String hql = "from Appointment where doctorId=? and seeTime=? ORDER BY dNumber DESC";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(doctorId);
        idObject.add(date);
        return dao.find(hql, idObject);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        dao.update(appointment);
    }

    @Override
    public void updateDiagnose(int id, String diagnose) {
        String hql = "update Appointment set diagnose=? where id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(diagnose);
        idObject.add(id);
        dao.executeHql(hql, idObject);
    }

    @Override
    public List<Appointment> findByDocId(int doctorId) {
        String hql = "from Appointment where doctorId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(doctorId);
        return dao.find(hql, idObject);
    }

    @Override
    public List<Appointment> findAllAppointment() {
        return dao.find("from Appointment");
    }

    @Override
    public List<Appointment> findAppointmentById(int id) {
        String hql = "from Appointment appointment where appointment.id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        return dao.find(hql, idObject);
    }

//    @Override
//    public List<Appointment> findByPatId(int id) {
//        String hql = "from Appointment where patientId=?";
//        List<Object> idObject = new ArrayList<Object>();
//        idObject.add(id);
//        return dao.find(hql, idObject);
//    }

    @Override
    public List<AppointmentDto> findByPatId(int id) {
        String hql = "from Appointment where patientId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<Appointment> appointments;
        List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
        appointments = dao.find(hql, idObject);
        for (int i = 0; i < appointments.size(); i++) {
            User doctor = userDao.get(User.class, appointments.get(i).getDoctorId());
            AppointmentDto dto = new AppointmentDto();
            dto.setId(appointments.get(i).getId());
            dto.setDateTime(appointments.get(i).getDateTime());
            dto.setDiagnose(appointments.get(i).getDiagnose());
            dto.setDisease(appointments.get(i).getDisease());
            dto.setdNumber(appointments.get(i).getdNumber());
            dto.setDstar(appointments.get(i).getDstar());
            dto.setPatientId(appointments.get(i).getPatientId());
            dto.setSeeTime(appointments.get(i).getSeeTime());
            dto.setDoctor_name(doctor.getName());
            appointmentDtos.add(dto);
        }
        return appointmentDtos;
    }


    @Override
    public void deleteAppointment(List<Appointment> findAppointment) {
        List<Appointment> list = findAppointment;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                dao.delete(list.get(i));
            }
        }
    }
}
