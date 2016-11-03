package com.zhuolang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.model.Like;
import com.zhuolang.service.ILikeService;
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
 * Created by hzg on 2016/10/15.
 */
@Controller
public class LikeAction extends ActionSupport{
    @Autowired
    ILikeService service;

    /*
    * 4、点赞
    * */
    public String add() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request= ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        Like like = new Like();
        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int likesId = Integer.parseInt(request.getParameter("likesId"));
        like.setSendId(sendId);
        like.setLikesId(likesId);//用户id，就是谁点的赞
        like.setLikesTime(new Date());
        service.addLike(like);

        PrintWriter out = response.getWriter();
        String jsonString="{\"like success\"}";
        out.print(jsonString);
        out.flush();
        out.close();
        return null;
    }

    /*
    * 4、取消点赞
    * */
    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        int sendId = Integer.parseInt(request.getParameter("sendId"));
        int likesId = Integer.parseInt(request.getParameter("likesId"));
        service.deleteLike(service.findBySLId(sendId,likesId));

        PrintWriter out = response.getWriter();
        String jsonString="{\"取消点赞\"}";
        out.print(jsonString);
        out.flush();
        out.close();

        return null;
    }

    public String update() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
        Like like = new Like();
        like.setId(8);
        like.setSendId(7);
        like.setLikesId(39);
        Date date = new Date();
        like.setLikesTime(date);
        service.updateLike(like);

        PrintWriter out = response.getWriter();
        String jsonString="{\"like success\"}";
        out.print(jsonString);
        out.flush();
        out.close();

        return "success";
    }

    public String find() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");

//        String hql = "from Like where id = '" +8 + "'";
//        List<Like> list = service.findLike(hql);

        List<Like> likeList = service.findLikeById(8);

        PrintWriter out = response.getWriter();
        String jsonString = "{\"success like\"}";
        out.print(jsonString);
        out.print(likeList);
        out.flush();
        out.close();

        return "success";
    }
}
