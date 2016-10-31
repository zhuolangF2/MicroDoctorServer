package com.zhuolang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.model.Discuss;
import com.zhuolang.service.IDiscussService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzg on 2016/10/15.
 */
@Controller
public class DiscussAction extends ActionSupport{
    @Autowired
    IDiscussService service;

    /*
    * 5、评论
    * */
    public String add() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int observerId = Integer.parseInt(request.getParameter("observerId"));
        Discuss discuss = new Discuss();
        discuss.setSendId(sendId);
        discuss.setObserverId(observerId);//评论者，就是userId
        discuss.setDcontent(request.getParameter("dcontent"));//"我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的"
        discuss.setDtime(new Date());
        service.addDiscuss(discuss);

        PrintWriter out = response.getWriter();
        String jsonString="{\"评论成功\"}";
        out.println(jsonString);
        out.flush();
        out.close();
        return null;
    }

    public String update() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

        Discuss discuss = new Discuss();
        discuss.setId(3);
        discuss.setSendId(8);
        discuss.setObserverId(38);
        discuss.setDcontent(null);//"我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的"

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = sdf.format(new Date());
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        discuss.setDtime(date);

        service.updateDiscuss(discuss);

        PrintWriter out = response.getWriter();
        String jsonString="{\"discuss success\"}";
        out.println(jsonString);
        out.flush();
        out.close();

        return "success";
    }

    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
//        String hql = "from Discuss where id = '" + 2 + "'";
//        service.deleteDiscuss(service.findDiscuss(hql));
        service.deleteDiscuss(service.findDiscussById(2));

        PrintWriter out = response.getWriter();
        String jsonString="{\"discuss success\"}";
        out.println(jsonString);
        out.flush();
        out.close();

        return "success";
    }

    public String find() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
//        String hql="from Discuss where id ='"+3+"'";

        PrintWriter out = response.getWriter();
        String jsonString="{\"discuss success\"}";
        out.println(jsonString);
//        out.println(service.findDiscuss(hql));
        out.println(service.findDiscussById(3));
        out.flush();
        out.close();
        return "success";
    }
}
