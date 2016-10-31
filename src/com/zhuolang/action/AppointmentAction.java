package com.zhuolang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.model.Appointment;
import com.zhuolang.service.IAppointmentService;
import com.zhuolang.util.TimeUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by wnf on 2016/10/15.
 * AppointmentAction动作，需要继承ActionSupport父类
 */
@Controller
public class AppointmentAction extends ActionSupport {

    @Autowired
    IAppointmentService service;

    /**
     * 1、向某医师申请预约  2、申请资料提交
     *
     * @throws IOException
     */
    public String add() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));//在安卓界面上通过点击选择一名医师，所以可以取得其id
        Date seeTime = TimeUtil.strToDate(request.getParameter("seeTime"));

        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setSeeTime(seeTime);
        appointment.setDisease(request.getParameter("disease"));
        appointment.setDateTime(new Date());
        int dNumber = service.findAppByDId(doctorId,seeTime).get(0).getdNumber()+1;
        appointment.setdNumber(dNumber);

        service.addAppointment(appointment);
        PrintWriter out = response.getWriter();
        out.println("addAppointment_success");//或者失败
        out.flush();
        out.close();
        return null;
    }

    /*
    * 3、查看医师预约申请队列  4、用户查看某医师预约队列
    * */
    public String findByDocId() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<Appointment> list = service.findByDocId(Integer.parseInt(request.getParameter("doctorId")));
        PrintWriter out = response.getWriter();
        out.println(list);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 6、医师填写就诊结果
     */
    public String updateDiagnose() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        // 根据主键id来更新信息，将整个appointment传到数据库，通过id找到要更新的appointment
        int id = Integer.parseInt(request.getParameter("id"));
        String diagnose = request.getParameter("diagnose");
        service.updateDiagnose(id,diagnose);

        PrintWriter out = response.getWriter();
        out.println("updateDiagnose_success");
        out.flush();
        out.close();
        return null;
    }


    /**
     *7、在登录状态下查看个人的预约信息
     *
     * @throws IOException
     */
    public String findByPatId() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        List<Appointment> list = service.findByPatId(Integer.parseInt(request.getParameter("patientId")));
        PrintWriter out = response.getWriter();
        out.println(list);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 测试删除
     *
     * @throws IOException
     */
    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        // HttpServletRequest request = ServletActionContext.getRequest();
        // int id = (int) request.getAttribute("user_id");
        response.setContentType("text/html;charset=utf-8");

        service.deleteAppointment(service.findAppointmentById(3));

        // 测试输出json数据
        PrintWriter out = response.getWriter();
        // JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
        String jsonString = "{\"id\":\"123\",\"PatientId\":\"124\",\"DoctorId\":\"125\",\"SeeTime\":\"new Date()\",\"Disease\":\"喉咙发炎,喉咙痛\",\"DateTime\":\"new Date()\",\"Diagnose\":\"感冒咳嗽\",\"Dstar\":\"5\"}";
        // 输出数据
        out.println(jsonString);
        out.flush();
        out.close();

        return "success";
    }

    /**
     * 测试查询
     *
     * @throws IOException
     */
    public String find() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request=ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        List<Appointment> list = service.findAppointmentById(2);
        if (list!=null&&list.size()>0){
            request.setAttribute("students_list",list);//获取参数
        }

        PrintWriter out = response.getWriter();
        out.println(list);
        out.flush();
        out.close();

        return "success";
    }
}








