package com.klef.jfsd.project.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "portfolio_table")
public class Portfolio {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "portfolio_id")
   private int id;
   

   @Column(name = "role", nullable = false, length = 500)
   private String role;
   
   @Column(name = "skills", nullable = false, length = 500)
   private String skills;

   @Column(name="file",nullable = false)
   private Blob file; 

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getSkills() {
	return skills;
}

public void setSkills(String skills) {
	this.skills = skills;
}

public Blob getFile() {
	return file;
}

public void setFile(Blob file) {
	this.file = file;
}

}
