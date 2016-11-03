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

    /*
    * 1、动态发表
    * */
    public String add() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

        Send send = new Send();
        send.setSendContent(request.getParameter("sendContent"));
        send.setUserId(Integer.parseInt(request.getParameter("userId")));
        send.setSendTime(new Date());

        PrintWriter out = response.getWriter();
        String jsonString = "{\"Send success\"}";
        out.print(jsonString);
        out.flush();
        out.close();
        service.addSend(send);
        return null;
    }

    /*
    * 2、获取动态列表(动态列表包括了discuss表里的评论内容和likes点赞人数等信息,返回的数据是sendDto)
    * */
    public String findAll() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");

        List<SendDto> list = service.findSendDto();
        PrintWriter out = response.getWriter();
        out.print(list.toString());
        out.flush();
        out.close();
        return null;
    }

    /*
    * 3、动态详情:通过用户id找到此用户发表的动态
    * */
    public String findByUID() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        List<SendDto> list = service.findDtoByUID(Integer.parseInt(request.getParameter("userId")));
        PrintWriter out = response.getWriter();
        out.print(list.toString());
        out.flush();
        out.close();
        return null;
    }

    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        String jsonString = "{\"Send success\"}";
        out.print(jsonString);
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
        out.print(jsonString);
        out.flush();
        out.close();

        service.updateSend(send);
        return "success";
    }

}
