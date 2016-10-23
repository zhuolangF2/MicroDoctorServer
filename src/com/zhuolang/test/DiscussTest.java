package com.zhuolang.test;

import com.zhuolang.model.Discuss;
import com.zhuolang.service.IDiscussService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzg on 2016/10/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DiscussTest {
    @Autowired
    IDiscussService service;

    @Test
    public void testAdd() {
        Discuss discuss = new Discuss();
        discuss.setSendId(6);
        discuss.setObserverId(38);
        discuss.setDcontent("我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的");
//        Date date = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String time = sdf.format(new Date());
//        try {
//            date = sdf.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        discuss.setDtime(new Date());

        service.addDiscuss(discuss);
    }

    @Test
    public void testDelete(){
//        String hql = "from Discuss where id = '" + 1 + "'";
//        service.deleteDiscuss(service.findDiscuss(hql));

        service.deleteDiscuss(service.findDiscussById(1));
    }

    @Test
    public void testUpdate(){
        Discuss discuss = new Discuss();
        discuss.setId(3);
        discuss.setSendId(7);
        discuss.setObserverId(39);
        discuss.setDcontent("我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的");//"我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的"

        Date date=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time= dateFormat.format(new Date());
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        discuss.setDtime(date);

        service.updateDiscuss(discuss);
    }

    @Test
    public void testFind(){
//        String hql="from Discuss where id ='"+15+"'";
//        System.out.println(service.findDiscuss(hql));

//        System.out.println(service.findDiscussById(12));
        System.out.println(service.findAllDiscuss());
    }
}
