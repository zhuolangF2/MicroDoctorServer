package com.zhuolang.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zhuolang.dao.IDoctorDao;
import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.Doctor;
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
    IUserDao userDao;

    @Autowired
    IDoctorDao doctorDao;

    @Override
    public List<User> userLogin(User user) {
        String hql = "from User where phone=? and password=?";
        List<Object> object = new ArrayList<Object>();
        object.add(user.getPhone());
        object.add(user.getPassword());
        List<User> userList = userDao.find(hql, object);
        return userList;
//        System.out.println(userList);
//        if (userList.size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * 业务逻辑操作
     */
    @Override
    public int addUser(User user) {
            return (int) userDao.save(user);//返回主键
    }

    @Override
    public void deleteUser(List<User> findUser) {
        // TODO Auto-generated method stub
        List<User> list = findUser;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                userDao.delete(list.get(i));
            }
        }
    }

    @Override
    public boolean findPhone(String phone) {
        String hql="from User where phone=?";
        User user = userDao.get(hql, new Object[]{phone});
        if (user == null) {//为空就是没有，找不到，返回false
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<User> findByPhone(String phone) {
        String hql="from User where phone=?";
        List<Object> idObject = new ArrayList<Object>();
        idObject.add(phone);
        List<User> userList = userDao.find(hql, idObject);
        return userList;
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
        if (userDao.executeHql(hql, object) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePassword(int id, String oldPW, String newPW) {
        String hql1 = "from User where id=?";
        User user = userDao.get(hql1, new Object[]{id});
        String password = user.getPassword();
        if (oldPW.equals(password)) {
            String hql2 = "update User set password=? where id=?";
            List<Object> object = new ArrayList<Object>();
            object.add(newPW);
            object.add(id);
            userDao.executeHql(hql2, object);
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
        List<User> userList = userDao.find(hql, idObject);
        return userList;
    }

    @Override
    public List<User> findAllUser() {
        String hql = "from User";
        return userDao.find(hql);
    }

    @Override
    public List<User> findUserByType(int type) {
        String hql = "from User where type=?";
        return userDao.find(hql, new Object[]{type});
    }

    @Override
    public List<DoctorDto> findDoctorDto() {
        List<User> userList = findUserByType(1);
        String hql = "from Doctor where doctorId=?";
        List<DoctorDto> doctorDtoList = new ArrayList<DoctorDto>();
        for (User user : userList) {
            DoctorDto dto=new DoctorDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setNickname(user.getNickname());
            dto.setPassword(user.getPassword());
            dto.setGender(user.getGender());
            dto.setAge(user.getAge());
            dto.setPhone(user.getPhone());
            dto.setAddress(user.getAddress());
            dto.setSignature(user.getSignature());
            dto.setIntroduction(user.getIntroduction());

            Doctor doctor = doctorDao.get(hql, new Object[]{user.getId()});
            dto.setHospital(doctor.getHospital());
            dto.setOffice(doctor.getOffice());
            dto.setAmount(doctor.getAmount());
            doctorDtoList.add(dto);
        }
        return doctorDtoList;
    }

}
