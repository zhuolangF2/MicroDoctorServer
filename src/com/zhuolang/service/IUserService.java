package com.zhuolang.service;

import java.io.Serializable;
import java.util.List;

import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.User;

/**
 * 用户服务类接口，根据业务逻辑进行设置
 *
 * @author jat
 */
//@Service
public interface IUserService {
    boolean userLogin(User user);

    int addUser(User user);

    boolean updateUser(User user);

    boolean updatePassword(int id, String oldPW, String newPW);

    List<User> findUserById(int id);

    List<User> findAllUser();

    /*查找所有普通用户*/
    List<User> findUserByType(int type);


    /*查找所有医师*/
    List<DoctorDto> findDoctorDto();

    //	List<User> findUser(String hql);
    void deleteUser(List<User> findUser);

    boolean findPhone(String phone);
}
