package com.zhuolang.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.components.Password;
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
        String hql = "from User where phone=? and password=?";
        List<Object> object = new ArrayList<Object>();
        object.add(user.getPhone());
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
    public int addUser(User user) {
            return (int) dao.save(user);//返回主键
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
    public boolean findPhone(String phone) {
        String hql="from User where phone=?";
        User user = dao.get(hql, new Object[]{phone});
        if (user == null) {//为空就是没有，找不到，返回false
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean updateUser(User user) {
        String hql = "update User set nickname=?,name=?,gender=?,phone=?,address=?,signature=?,introduction=?,age=? where id=?";
        List<Object> object = new ArrayList<Object>();
        object.add(user.getNickname());
        object.add(user.getName());
        object.add(user.getGender());
        object.add(user.getPhone());
        object.add(user.getAddress());
        object.add(user.getSignature());
        object.add(user.getIntroduction());
        object.add(user.getAge());
        object.add(user.getId());
        if (dao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePassword(int id, String oldPW, String newPW) {
        String hql1 = "from User where id=?";
        User user = dao.get(hql1, new Object[]{id});
        String password = user.getPassword();
        if (oldPW.equals(password)) {
            String hql2 = "update User set password=? where id=?";
            List<Object> object = new ArrayList<Object>();
            object.add(newPW);
            object.add(id);
            dao.executeHql(hql2, object);
            return true;
        } else {
            return false;
        }
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

    @Override
    public List<User> findUserByType(int type) {
        String hql = "from User where type=?";
        return dao.find(hql, new Object[]{type});
    }

//	@Override
//	public List<User> findUser(String hql) {
//		return dao.find(hql);
//	}

}
