package com.klef.jfsd.project.service;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Project;
import com.klef.jfsd.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

   @Autowired
   private ProjectRepository projectRepository;

   @Override
   public String addProject(Project project) {
       projectRepository.save(project);
       return "Project added successfully!";
   }

   @Override
   public List<Project> viewAllProjects() {
       return projectRepository.findAll();
   }

   @Override
   public Project viewProjectById(int projectId) {
       return projectRepository.findById(projectId).orElse(null);
   }

   @Override
   public List<Project> viewProjectsByStudentId(int studentId) 
   {
       return projectRepository.findByStudentId(studentId);
   }

   @Override
   public Project viewProjectByProjectNumber(String projectNumber) {
       return projectRepository.findByProjectNumber(projectNumber);
   }

   @Override
   public List<Project> viewProjectsByName(String name) {
       return projectRepository.findByNameContainingIgnoreCase(name);
   }

@Override
public List<Project> findProjectsByFaculty(Faculty faculty) 
{
	return projectRepository.findByFaculty(faculty);
}


}
