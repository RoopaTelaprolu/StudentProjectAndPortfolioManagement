package com.klef.jfsd.project.service;

import java.util.List;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Project;

public interface FacultyService
{
	public Faculty checkfaclogin(String facemail, String facpwd);
	public String updateFacultyProfile(Faculty faculty);
	public Faculty displayFacultybyID(int fid);
	public Faculty getFacultyById(int id);
	public List<Faculty> getAllFaculties();

}
