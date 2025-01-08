package com.klef.jfsd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService
{
	@Autowired
	public FacultyRepository facultyRepository;

	
	@Override
	public Faculty checkfaclogin(String facemail, String facpwd) 
	{
		return facultyRepository.checkfaclogin(facemail, facpwd);
	}


	@Override
	public String updateFacultyProfile(Faculty faculty) 
	{
		Faculty f = facultyRepository.findById(faculty.getId()).get();
		
	    f.setContact(faculty.getContact());
	    f.setDateofbirth(faculty.getDateofbirth());
	    f.setDepartment(faculty.getDepartment());
	    f.setGender(faculty.getGender());
	    f.setLocation(faculty.getLocation());
	    f.setName(faculty.getName());
	    f.setPassword(faculty.getPassword());
	    
	    facultyRepository.save(f);
	    
	    return "Faculty Updated Successfully";
	}


	@Override
	public Faculty displayFacultybyID(int fid) 
	{
		return facultyRepository.findById(fid).get();

	}


	@Override
	public Faculty getFacultyById(int id)
	{
		 return facultyRepository.findById(id).orElse(null);
	}


	@Override
	public List<Faculty> getAllFaculties() 
	{
		return facultyRepository.findAll();
	}

}
