package com.zhuolang.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhuolang.dao.IUserDao;
import com.zhuolang.model.User;
import com.zhuolang.service.IUserService;

/**
 * 用户服务实现类，实现业务逻辑功能
 *
 * @author jat
 */
@Service("userService")
public class UserService implements IUserService {

    // 注入服务层，操作数据持久化
    @Autowired
    IUserDao dao;

    @Override
    public boolean userLogin(User user) {
        String hql = "from User where nickname=? and password=?";
        List<Object> object=new ArrayList<Object>();
        object.add(user.getNickname());
        object.add(user.getPassword());
        List<User> userList = dao.find(hql, object);
        System.out.println(userList);
        if (userList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 业务逻辑操作
     */
    @Override
    public String addUser(User user) {
        try {
            dao.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public void deleteUser(List<User> findUser) {
        // TODO Auto-generated method stub
        List<User> list = findUser;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                dao.delete(list.get(i));
            }
        }
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public List<User> findUserById(int id) {
        String hql = "from User user where user.id=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(id);
        List<User> userList = dao.find(hql, idObject);
        return userList;
    }

    @Override
    public List<User> findAllUser() {
        String hql = "from User";
        return dao.find(hql);
    }


//	@Override
//	public List<User> findUser(String hql) {
//		return dao.find(hql);
//	}

}
