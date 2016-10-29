package com.zhuolang.dao.impl;

import com.zhuolang.dao.IAppointmentDao;
import com.zhuolang.model.Appointment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by wnf on 2016/10/15.
 * 用户持久层实现类,进行数据持久操作
 *
 * @author jat
 *
 */
@Repository("appointmentDao")
public class AppointmentDao extends BaseDao<Appointment> implements IAppointmentDao{
//
//    public int findByDocId(int doctorId, Date date){
//        String hql="select max(appointment.dNumber) from Appointment appointment where appointment.doctorId=? and appointment.seeTime=?";
//        Query q = this.getCurrentSession().createQuery(hql);
//        q.setParameter(0, doctorId);
//        q.setParameter(1, date);
//        int max = q.executeUpdate();
//        return max;
//    }

}
