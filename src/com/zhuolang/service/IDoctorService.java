package com.zhuolang.service;

import com.zhuolang.model.Doctor;

import javax.print.Doc;
import java.util.List;

//@Service
// 如果这里也注释，就会重复注释service了，无法自动注入doctorService，在DoctorService里注释就可以了
public interface IDoctorService {

	/**
	 * @param doctor
	 */
	void addDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
//    List<Doctor> findDoctor(String hql);
	List<Doctor> findAllDoctor();
	List<Doctor> findDoctorById(int id);
    void deleteDoctor(List<Doctor> findDoctor);

}
