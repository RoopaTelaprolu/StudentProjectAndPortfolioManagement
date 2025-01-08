package com.klef.jfsd.project.service;

import java.util.List;

import com.klef.jfsd.project.model.Admin;
import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Student;

public interface AdminService 
{
	public String FacultyRegistration(Faculty fac);
	public List<Faculty> ViewAllFaculties();
	public Admin checkadminlogin(String uname, String pwd);
	public String updatefacultystatus(String status,int fid);
	public String deletefac(int fid);
	public long faccount();
	
	
	public String StudentRegistration(Student stu);
	public List<Student> ViewAllStudents();
	public String updatestudentstatus(String status,int sid);
	public String deletestu(int sid);
	public long stucount();

}
