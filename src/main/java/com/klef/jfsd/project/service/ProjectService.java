package com.klef.jfsd.project.service;

import java.util.List;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Project;

public interface ProjectService {
   public String addProject(Project project);

   public List<Project> viewAllProjects();

   public Project viewProjectById(int projectId);

   public List<Project> viewProjectsByStudentId(int studentId);

   public Project viewProjectByProjectNumber(String projectNumber);

   public List<Project> viewProjectsByName(String name);
   
   public List<Project> findProjectsByFaculty(Faculty faculty);
   
   
}
