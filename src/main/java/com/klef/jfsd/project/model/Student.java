package com.klef.jfsd.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_table")
public class Student
{
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 @Column(name="stu_id")
	 private int id;
	 @Column(name="stu_name",nullable=false,length = 50)
	 private String name;
	 @Column(name="stu_gender",nullable=false,length = 20)
	 private String gender;
	 @Column(name="stu_dob",nullable=false,length = 20)
	 private String dateofbirth;
	 @Column(name="stu_department",nullable=false,length = 50)
	 private String department;
	 @Column(name="stu_email",nullable=false,unique = true,length = 50)
	 private String email;
	 @Column(name="stu_password",nullable=false,length = 50)
	 private String password;
	 @Column(name="stu_location",nullable=false,length=50)
	 private String location;
	 @Column(name="stu_contact",nullable=false,unique = true,length = 20)
	 private String contact;
	 @Column(name="stu_status",nullable=false,length = 50)
	 private String status;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
