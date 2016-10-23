package com.zhuolang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.dto.SendDto;
import com.zhuolang.model.Send;
import com.zhuolang.service.ISendService;
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
 * Created by hzg on 2016/10/14.
 */
@Controller
public class SendAction extends ActionSupport{
    @Autowired
    ISendService service;

    public String add() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

        Send send = new Send();
        send.setSendContent("发送信息");
        send.setUserId(39);
        send.setSendTime(new Date());

        PrintWriter out = response.getWriter();
        String jsonString = "{\"Send success\"}";
        out.println(jsonString);
        out.flush();
        out.close();
        service.addSend(send);
        return "success";
    }

    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
//        int userId=38;
//        String hql = "from Send where userId = '"+userId+"'";
//        service.deleteSend(service.findSend(hql));

        PrintWriter out = response.getWriter();
        String jsonString = "{\"Send success\"}";
        out.println(jsonString);
        out.flush();
        out.close();

        return "success";
    }

    public String update() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
        Send send = new Send();
        send.setSendContent("发送信息改为修改信息");
        send.setSendId(6);
        send.setUserId(39);
        send.setSendTime(new Date());

        PrintWriter out = response.getWriter();
        String jsonString = "{\"Send success\"}";
        out.println(jsonString);
        out.flush();
        out.close();

        service.updateSend(send);
        return "success";
    }

    public String find() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

        List<SendDto> list = service.findSendDto();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        PrintWriter out = response.getWriter();
        out.println("{\"Send success\"}");
        out.println(list.toString());
        out.flush();
        out.close();

        return "success";
    }
}
