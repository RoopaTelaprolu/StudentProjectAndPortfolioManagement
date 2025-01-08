package com.klef.jfsd.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

   List<Project> findByStudentId(int studentId);

   Project findByProjectNumber(String projectNumber);

   List<Project> findByNameContainingIgnoreCase(String name);

   List<Project> findByStudent_Name(String studentName);
   
   List<Project> findAll();
   
   List<Project> findByFaculty(Faculty faculty); 
   
   @Query("SELECT p FROM Project p WHERE p.id = :id")
   Project findProjectById(@Param("id") Long id); // Custom query to find project by ID
}
