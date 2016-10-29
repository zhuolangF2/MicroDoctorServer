package com.zhuolang.test;

import com.zhuolang.model.Appointment;
import com.zhuolang.service.IAppointmentService;
import com.zhuolang.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by wnf on 2016/10/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AppointmentTest {
    @Autowired
    IAppointmentService service;

    @Test
    public void testAdd() {

        System.out.println("service:" + service);
        //测试插入数据
        Appointment appointment = new Appointment();
        appointment.setPatientId(38);
        appointment.setDoctorId(42);
        appointment.setSeeTime(new Date());
        appointment.setDisease("喉咙发炎,喉咙痛");
        appointment.setDateTime(new Date());
        appointment.setDiagnose("感冒咳嗽");
        appointment.setDstar(5);
        appointment.setdNumber(5);

        service.addAppointment(appointment);
    }

    @Test
    public void testDeleteById() {
        service.deleteAppointment(service.findAppointmentById(1));
    }

    @Test
    public void testUpdate() {
        Appointment appointment = new Appointment();
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment
        appointment.setId(2);
        appointment.setPatientId(38);
        appointment.setDoctorId(42);
        appointment.setSeeTime(new Date());
        appointment.setDisease("皮肤发痒,更新");
        appointment.setDateTime(new Date());
        appointment.setDiagnose("皮肤炎症");
        appointment.setDstar(3);

        service.updateAppointment(appointment);
    }

    @Test
    public void testFind() {
//        System.out.println(service.findAllAppointment());
        System.out.println(service.findAppointmentById(2));
//        System.out.println(TimeUtil.stringToDate("2016-10-29"));
    }

    @Test
    public void testFindMax() {
//        int dNumber = service.findAppointmentByDoctorId(42, "2016-10-29").get(2).getdNumber();
//        Date date = TimeUtil.strToDate("2016-10-29");
//        System.out.println(service.findAppointmentByDoctorId(42, date));
    }

    @Test
    public void testFindByDocId() {
        System.out.println(service.findByDocId(57));
    }

    @Test
    public void testUpdateByDID() {
        service.updateDiagnose(9,"精神病");
    }


}
