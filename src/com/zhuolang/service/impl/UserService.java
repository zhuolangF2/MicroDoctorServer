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

    /**
     * 业务逻辑操作
     */
    @Override
    public void addUser(User user) {
        try {
            dao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
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
