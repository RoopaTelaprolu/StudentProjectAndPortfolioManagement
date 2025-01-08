package com.klef.jfsd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.project.model.Admin;
import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Student;
import com.klef.jfsd.project.repository.AdminRepository;
import com.klef.jfsd.project.repository.FacultyRepository;
import com.klef.jfsd.project.repository.StudentRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin checkadminlogin(String uname, String pwd)
	{
		return adminRepository.checkadminlogin(uname, pwd);
	}

	@Override
	public String FacultyRegistration(Faculty fac) 
	{
		facultyRepository.save(fac);
		return "Faculty Registered Successfully";
	}

	@Override
	public List<Faculty> ViewAllFaculties()
	{
		return facultyRepository.findAll();	
    }

	@Override
	public String updatefacultystatus(String status, int fid)
	{
		 facultyRepository.updateFacultyStatus(status, fid);
		 return "Faculty Status Updated Successfully";
	}

	@Override
	public String deletefac(int fid)
	{
		   facultyRepository.deleteById(fid);
		   return "Faculty Deleted Successfully";
	}

	@Override
	public long faccount() 
	{
		return facultyRepository.count();

	}
	
	

	@Override
	public String StudentRegistration(Student stu) 
	{
		studentRepository.save(stu);
		return "Student Registered Successfully";
		
	}

	@Override
	public List<Student> ViewAllStudents() 
	{
		return studentRepository.findAll();
	}

	@Override
	public String updatestudentstatus(String status, int sid)
	{
		studentRepository.updateStudentStatus(status, sid);
	    return "Student Status Updated Successfully";
	}

	@Override
	public String deletestu(int sid) 
	{
		studentRepository.deleteById(sid);
		return "Student Deleted Successfully";
		
	}

	@Override
	public long stucount()
	{
		return studentRepository.count();
	}
	
}
