package com.zhuolang.service.impl;

import com.zhuolang.dao.IDiscussDao;
import com.zhuolang.dao.ILikeDao;
import com.zhuolang.dao.ISendDao;
import com.zhuolang.dto.SendDto;
import com.zhuolang.model.Discuss;
import com.zhuolang.model.Send;
import com.zhuolang.service.ISendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hzg on 2016/10/13.
 */
@Service("sendService")
public class SendService implements ISendService {

    @Autowired
    ISendDao dao;
    @Autowired
    ILikeDao likeDao;
    @Autowired
    IDiscussDao discussDao;

    @Override
    public void addSend(Send send) {
        dao.save(send);
    }

    @Override
    public void updateSend(Send send) {
        dao.update(send);
    }

    //找所有的SendDto
    @Override
    public List<SendDto> findSendDto() {
        String hql = "from Send";
        List<Object> idObject = new ArrayList<Object>();
        List<Send> list = dao.find(hql, idObject);

        List<SendDto> sendDtoList = new ArrayList<SendDto>();
        for (Send send : list) {
            SendDto dto = new SendDto();

//            count(1)，其实就是计算一共有多少符合条件的行,实际就是点赞人数
            String likes_hql = "select count(*) from Like as l where l.sendId=?";
            idObject.clear();
            idObject.add(send.getSendId());
            //通过like表中的sendId来找到send
            dto.setLikes(likeDao.count(likes_hql, idObject).intValue());

            dto.setId(send.getSendId());
            dto.setSend_content(send.getSendContent());

            String discuss_hql = "from Discuss d where d.sendId=?";
            List<Discuss> dList = discussDao.find(discuss_hql, idObject);

            if (dList != null) {
                dto.setDiscussList(dList);
            }
            sendDtoList.add(dto);
        }

        return sendDtoList;
    }

    @Override
    public List<SendDto> findDtoByUID(int userId) {
        String hql = "from Send where userId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(userId);
        List<Send> list = dao.find(hql, idObject);

        List<SendDto> sendDtoList = new ArrayList<SendDto>();
        for (Send send : list) {
            SendDto dto = new SendDto();
            dto.setId(send.getSendId());
            dto.setUser_id(send.getUserId());
            dto.setSend_content(send.getSendContent());
            dto.setCreate_time(new Date());

            String likes_hql = "select count(*) from Like as l where l.sendId=?";
            idObject.clear();
            idObject.add(send.getSendId());
            dto.setLikes(likeDao.count(likes_hql, idObject).intValue());//点赞人数

            String discuss_hql = "from Discuss d where d.sendId=?";
            List<Discuss> dList = discussDao.find(discuss_hql, idObject);
            if (dList != null) {
                dto.setDiscussList(dList);
            }
            sendDtoList.add(dto);
        }
        return sendDtoList;
    }

    @Override
    public List<Send> findAllSend() {
        String hql = "from Send";
        return dao.find(hql);
    }

    @Override
    public SendDto findDtoById(int sendId) {
        String hql = "from Send send where send.sendId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(sendId);
        List<Send> list = dao.find(hql, idObject);//将id传进hql语句的？中,具体看BaseDao

        SendDto dto = new SendDto();
        for (Send send : list) {
            dto.setId(send.getSendId());
            dto.setUser_id(send.getUserId());
            dto.setSend_content(send.getSendContent());
            dto.setCreate_time(new Date());

            String likes_hql = "select count(*) from Like as l where l.sendId=?";
            idObject.clear();
            idObject.add(send.getSendId());
            dto.setLikes(likeDao.count(likes_hql, idObject).intValue());//点赞人数

            String discuss_hql = "from Discuss d where d.sendId=?";
            List<Discuss> dList = discussDao.find(discuss_hql, idObject);
            if (dList != null) {
                dto.setDiscussList(dList);
            }

        }
        return dto;
    }

    @Override
    public List<Send> findSendById(int id) {
        String hql = "from Send send where send.sendId=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<Send> list = dao.find(hql, idObject);//将id传进hql语句的？中,具体看BaseDao
        return list;
    }


    @Override
    public void deleteSend(List<Send> findSend) {
        List<Send> list = findSend;
        if (list != null && list.size() > 0) {
            //如果send删除了，要先删除like和discuss中的内容
            for (int i = 0; i < list.size(); i++) {
                String like_hql = "delete from Like l where l.sendId=?";
                List<Object> object = new ArrayList<Object>();
                object.add(list.get(i).getSendId());
                likeDao.executeHql(like_hql, object);

                String discuss_hql = "delete from Discuss d where d.sendId=?";
                object.clear();
                object.add(list.get(i).getSendId());
                discussDao.executeHql(discuss_hql, object);
                dao.delete(list.get(i));
            }
        }
    }
//类似重复
//    @Override
//    public int deleteSendById(int id) {
//        List<Send> list = findSendById(id);
//        if (list != null && list.size() > 0) {
//            //如果send删除了，要先删除like和discuss中的内容
//            for (int i = 0; i < list.size(); i++) {
//
//                String like_hql = "delete from Like l where l.sendId=?";
//                List<Object> object = new ArrayList<Object>();
//                object.add(list.get(i).getSendId());
//                likeDao.executeHql(like_hql, object);
//
//                String discuss_hql = "delete from Discuss d where d.sendId=?";
//
//                object.clear();//要清零，否则有两个id，会报错
//                object.add(list.get(i).getSendId());
//                discussDao.executeHql(discuss_hql, object);
//                dao.delete(list.get(i));
//            }
//        }
//        return 0;
//    }
}
