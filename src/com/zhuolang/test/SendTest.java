package com.zhuolang.test;

import com.zhuolang.dto.SendDto;
import com.zhuolang.model.Send;
import com.zhuolang.service.ISendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by hzg on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SendTest {
    @Autowired
    ISendService service;
    @Test
    public void testAdd(){
        System.out.println("service:"+service);
        Send send = new Send();
        send.setSendContent("发送信息");
        send.setUserId(38);
        send.setSendTime(new Date());

        service.addSend(send);
    }

    @Test
    public void testUpdate(){
        //通过什么来修改更新信息的？这里不应该出现id？应该是通过名字获取id?还是直接用当前用户的id?
        Send send = new Send();
        send.setSendContent("发送信息改为修改信息");
        send.setSendId(6);
        send.setUserId(38);
        send.setSendTime(new Date());

        service.updateSend(send);
    }

    @Test
    public void testFindDto(){
        List<SendDto> list = service.findSendDto();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testFind() {
        System.out.println(service.findAllSend());
    }

    @Test
    public void testFindById() {
        System.out.println(service.findSendDtoById(6));
        System.out.println(service.findSendById(6));
    }

    @Test
    public void testDelete(){
        service.deleteSend(service.findSendById(6));//通过id找到send
//        service.deleteSendById(7);//其实跟上面的一样
    }
}
