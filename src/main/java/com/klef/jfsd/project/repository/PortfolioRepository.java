package com.klef.jfsd.project.repository;

import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.project.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>
{
	

}
