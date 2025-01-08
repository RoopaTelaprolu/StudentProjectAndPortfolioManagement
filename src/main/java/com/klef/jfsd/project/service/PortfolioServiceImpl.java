package com.klef.jfsd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.project.model.Portfolio;
import com.klef.jfsd.project.repository.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService
{

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Override
	public String AddPortfolio(Portfolio portfolio) 
	{
		portfolioRepository.save(portfolio);
		return "Portfolio Added Successfully";
		
	}

	@Override
	public List<Portfolio> ViewAllPortfolios() 
	{
		return (List<Portfolio>) portfolioRepository.findAll();
		
	}

	@Override
	public Portfolio GetPortfolioByID(int portfolioid) 
	{
		return portfolioRepository.findById(portfolioid).get();

	}
	

}
