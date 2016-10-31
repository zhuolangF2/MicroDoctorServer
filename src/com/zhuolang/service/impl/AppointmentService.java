package com.zhuolang.service.impl;

import com.zhuolang.dao.IAppointmentDao;
import com.zhuolang.model.Appointment;
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
    public void updateDiagnose(int id,String diagnose) {
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

    @Override
    public List<Appointment> findByPatId(int id) {
        String hql = "from Appointment where patientId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        return dao.find(hql, idObject);
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
