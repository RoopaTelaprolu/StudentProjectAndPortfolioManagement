package com.klef.jfsd.project.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_table")
public class Project {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "project_id")
   private int id;
   
   @ManyToOne
   @JoinColumn(name = "stu_id", nullable = false) 
   private Student student;
   
   @ManyToOne
   @JoinColumn(name = "faculty_id", nullable = false) 
   private Faculty faculty;

   @Column(name = "project_number", nullable = false, length = 100)
   private String projectNumber;

   @Column(name = "project_name", nullable = false, length = 100)
   private String name;

   @Column(name = "project_desc", nullable = false, length = 500)
   private String description;

   @Column(name = "project_url", nullable = false, length = 200)
   private String url;
   
   @Column(name = "feedback", length =1000)
   private String feedback;
   
   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public Student getStudent() {
       return student;
   }

   public void setStudent(Student student) {
       this.student = student;
   }
   
   public Faculty getFaculty() {
       return faculty;
   }

   public void setFaculty(Faculty faculty) {
       this.faculty = faculty;
   }

   public String getProjectNumber() {
       return projectNumber;
   }

   public void setProjectNumber(String projectNumber) {
       this.projectNumber = projectNumber;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getDescription() {
       return description;
   }

   public void setDescription(String description) {
       this.description = description;
   }

   public String getUrl() {
       return url;
   }

   public void setUrl(String url) {
       this.url = url;
   }

public String getFeedback() {
	return feedback;
}

public void setFeedback(String feedback) {
	this.feedback = feedback;
}
}