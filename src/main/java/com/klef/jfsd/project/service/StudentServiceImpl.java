package com.klef.jfsd.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.project.model.Student;
import com.klef.jfsd.project.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student checkstulogin(String stuemail, String stupwd) 
	{
		return studentRepository.checkstulogin(stuemail, stupwd);
	}

	@Override
	public String updateStudentProfile(Student student) 
	{
      Student s = studentRepository.findById(student.getId()).get();
		
	    s.setContact(student.getContact());
	    s.setDateofbirth(student.getDateofbirth());
	    s.setDepartment(student.getDepartment());
	    s.setGender(student.getGender());
	    s.setLocation(student.getLocation());
	    s.setName(student.getName());
	    s.setPassword(student.getPassword());
	    
	    studentRepository.save(s);
	    
	    return "Student Profile Updated Successfully";
	}

	@Override
	public Student displayStudentbyID(int sid) 
	{
		return studentRepository.findById(sid).get();
	}

}
