package com.zhuolang.service.impl;

import com.zhuolang.dao.IDiscussDao;
import com.zhuolang.model.Discuss;
import com.zhuolang.service.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzg on 2016/10/15.
 */
@Service("discussService")
public class DiscussService implements IDiscussService {
    @Autowired
    IDiscussDao dao;

    @Override
    public void addDiscuss(Discuss discuss) {
        dao.save(discuss);
    }

    @Override
    public void updateDiscuss(Discuss discuss) {
        dao.update(discuss);
    }

    @Override
    public List<Discuss> findAllDiscuss() {
        String hql = "from Discuss";
        return dao.find(hql);
    }

    @Override
    public List<Discuss> findDiscussById(int id) {
        String hql="from Discuss discuss where discuss.id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        return dao.find(hql,idObject);
    }

//    @Override
//    public List<Discuss> findDiscuss(String hql) {
//        return dao.find(hql);
//    }

    @Override
    public void deleteDiscuss(List<Discuss> findDiscuss) {
        List<Discuss> list = findDiscuss;
        if (list != null && list.size() > 0) {
            for (int i=0;i<list.size();i++) {
                dao.delete(list.get(i));
            }
        }
    }
}
