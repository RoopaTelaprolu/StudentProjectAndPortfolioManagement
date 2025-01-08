package com.klef.jfsd.project.service;

import com.klef.jfsd.project.model.Student;

public interface StudentService 
{
	public Student checkstulogin(String stuemail, String stupwd);
	public String updateStudentProfile(Student student);
	public Student displayStudentbyID(int sid);

}
