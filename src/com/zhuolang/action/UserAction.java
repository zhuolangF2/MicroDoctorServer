package com.zhuolang.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuolang.dto.DoctorDto;
import com.zhuolang.model.Doctor;
import com.zhuolang.service.IDoctorService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zhuolang.model.User;
import com.zhuolang.service.IUserService;

/**
 * UserAction动作，需要继承ActionSupport父类
 *
 * @author jat
 */
@Controller
public class UserAction extends ActionSupport {
    /**
     * 用来表明类的不同版本间的兼容性。如果你修改了此类, 要修改此值。 否则以前用老版本的类序列化的类恢复时会出错。
     * 为了在反序列化时，确保类版本的兼容性，最好在每个要序列化的类中加入 private static final long
     * serialVersionUID这个属性，具体数值自己定义
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    IUserService userService;

    @Autowired
    IDoctorService doctorService;

    public String login() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        response.setContentType("text/html;charset=utf-8");
//      通过电话号码来登录，电话号码是唯一，且不为空
        User user = new User();
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));

        String result;
        if (userService.userLogin(user)) {
            result = "login_success";
        } else {
            result = "login_failure";
        }
        PrintWriter out = response.getWriter();
        out.print(result);//返回登录的结果，success or failure
        out.flush();
        out.close();
        return null;
    }

    /**
     * 1、普通用户注册  2、医师注册(注册界面有另外的医生附加信息)
     *
     * @throws IOException
     */
    public String add() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();

        /**
         * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
         * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
         * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
         *
         * request.setCharacterEncoding（）是设置从request中取得的值或从数据库中取出的值
         * response.setContentType("text/html;charset=gb2312")是设置页面中为中文编码
         */

        // 进行操作。。。
        response.setContentType("text/html;charset=utf-8");
        // 测试插入数据
        String phone = request.getParameter("phone");

        if (findPhone(phone)) {
            PrintWriter out = response.getWriter();
            out.print("register_failure");//该电话号码已经注册，请输入其他电话号码
            out.flush();
            out.close();
        } else {
            User user = new User();
            user.setPhone(phone);
            user.setNickname(request.getParameter("nickname"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            //强制转换为整形
            String genderStr = request.getParameter("gender");
            int gender = Integer.parseInt(genderStr);
            user.setGender(gender);
            user.setAddress(request.getParameter("address"));
            user.setSignature("未填写");
            user.setIntroduction("未填写");

            String ageStr = request.getParameter("age");
            int age = Integer.parseInt(ageStr);
            user.setAge(age);

            String typeStr = request.getParameter("type");
            int type = Integer.parseInt(typeStr);
            user.setType(type);
            int userId = userService.addUser(user);
            //根据类型可判断是普通用户注册还是医师注册,如果是医师的话还要在doctor表上添加一个数据
            if (type == 1) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(userId);
                doctor.setHospital("未填写");
                doctor.setOffice("未填写");
//                int amount = Integer.parseInt(request.getParameter("amount"));
                doctor.setAmount(0);//接诊量刚开始是0的，不是医师自己填写的
                doctorService.addDoctor(doctor);
            }
            //在这里添加失败会怎么样？
            PrintWriter out = response.getWriter();
            out.print("register_success");
            out.flush();
            out.close();
        }
        return null;
    }

    /*
   * 3、密码修改
   * */
    public String updatePW() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        boolean flag = userService.updatePassword(id, request.getParameter("oldPassword"), request.getParameter("newPassword"));
        String result;
        if (flag) {
            result = "updatePassword_success";
        } else {
            result = "updatePassword_failure";
        }
        // 测试输出json数据
        PrintWriter out = response.getWriter();
        // JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
        out.print(result);
        out.flush();
        out.close();
        return null;
    }

    /**
     * 4、普通资料修改(密码跟用户类型（普通用户或医师）不允许改)
     */
    public String update() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        User user = new User();
        //从当前登录的用户中获取id
        int id = Integer.parseInt(request.getParameter("id"));
        user.setId(id);
        user.setNickname(request.getParameter("nickname"));
        user.setName(request.getParameter("name"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        user.setGender(gender);
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));
        user.setSignature(request.getParameter("signature"));
        user.setIntroduction(request.getParameter("introduction"));
        int age = Integer.parseInt(request.getParameter("age"));
        user.setAge(age);
        userService.updateUser(user);

        PrintWriter out = response.getWriter();
        out.print("update_success");
        out.flush();
        out.close();
        return null;
    }

    /**
     * 5、用户个人信息资料展示 6、医师个人资料展示
     *
     * @throws IOException
     */
    public String find() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        List<User> list = userService.findUserById(id);
        if (list != null && list.size() > 0) {
            request.setAttribute("user_list", list);
        }
        //即是获取到密码，但是（model中的toString）不展示出来，就是安卓界面里没有这个展示项，或者后期加密后获取到也没有
        PrintWriter out = response.getWriter();
        out.print(list);
        out.flush();
        out.close();

        return null;
    }

    /**
     * 6、用户个人信息资料展示  、医师个人资料展示
     *
     * @throws IOException
     */
    public String findByPhone() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        List<User> list = userService.findByPhone(request.getParameter("phone"));
        if (list != null && list.size() > 0) {
            request.setAttribute("user_list", list);
        }

//        JSONArray jsonArray = new JSONArray();
//        for (User a : list) {
//            JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
//            jsonArray.add(jsonObj);
//        }
        DoctorDto userDto=new DoctorDto();
        userDto.setId(list.get(0).getId());
        userDto.setName(list.get(0).getName());
        userDto.setNickname(list.get(0).getNickname());
        userDto.setGender(list.get(0).getGender());
        userDto.setAge(list.get(0).getAge());
        userDto.setPhone(list.get(0).getPhone());
        userDto.setAddress(list.get(0).getAddress());
        userDto.setSignature(list.get(0).getSignature());
        userDto.setIntroduction(list.get(0).getIntroduction());
        if (list.get(0).getType()==1){
            List<Doctor> doctorList=doctorService.findDoctorByDoctorId(list.get(0).getId());

            userDto.setType(1);
            userDto.setHospital(doctorList.get(0).getHospital());
            userDto.setOffice(doctorList.get(0).getOffice());
            userDto.setAmount(doctorList.get(0).getAmount());
        }
        else{
            userDto.setType(0);
            userDto.setHospital("");
            userDto.setOffice("");
            userDto.setAmount(0);
        }
        JSONObject jsonObject=new JSONObject();
        String userJson=jsonObject.toJSONString(userDto);
        //即是获取到密码，但是（model中的toString）不展示出来，就是安卓界面里没有这个展示项，或者后期加密后获取到也没有
        PrintWriter out = response.getWriter();
        out.print(userJson);
        out.flush();
        out.close();

        return null;
    }

    /*
    * 7、查看医师列表（用户类型为1）
    * */
    public String findDoctorDto() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        List<DoctorDto> doctorDtos = userService.findDoctorDto();
//        String jsonUser = JSON.toJSONString(doctorDtos, true);
//        PrintWriter out = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        for (DoctorDto a : doctorDtos) {
            JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
            jsonArray.add(jsonObj);
        }
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 7、查看医师列表（用户类型为1）8、查看用户列表（所有的用户列表）
     *
     * @throws IOException
     */
    public String findByType() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");
        int type = Integer.parseInt(request.getParameter("type"));
        if (type == 1) {
            findDoctorDto();
            return null;
        } else {
            List<User> userList = userService.findUserByType(type);
            JSONArray jsonArray = new JSONArray();
            for (User a : userList) {
                JSONObject jsonObj = (JSONObject) JSON.toJSON(a);
                jsonArray.add(jsonObj);
            }
//            String jsonStr = JSON.toJSONString(userList, true);
            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();
            out.close();
            return null;
        }
    }

    /*
    * 寻找是否存在这个号码
    * true:存在
    * false:不存在
    * */
    public boolean findPhone(String phone) {
        return userService.findPhone(phone);//找不到，返回false,就是没有
    }

    /**
     * 测试删除
     *
     * @throws IOException
     */
    public String delete() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html;charset=utf-8");

        userService.deleteUser(userService.findUserById(38));
        // 测试输出json数据
        PrintWriter out = response.getWriter();
        // JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
        String jsonString = "{\"user\":{\"id\":\"123\",\"name\":\"张三\",\"say\":\"Hello , i am a action to print a json!\",\"password\":\"JSON\"},\"success\":true}";
        // 输出数据
        out.print(jsonString);
        out.flush();
        out.close();

        return null;
    }

}
