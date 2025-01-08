package com.klef.jfsd.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.project.model.Faculty;

import jakarta.transaction.Transactional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty ,Integer>
{
	@Query("select f from Faculty f where f.email=?1 and f.password=?2")
	public Faculty checkfaclogin(String facemail, String facpwd);
	
	@Query("update Faculty f set f.status=?1 where f.id=?2")
	@Modifying 
	@Transactional 
	public int updateFacultyStatus(String status,int fid);

	public List<Faculty> findAll();
	
}
