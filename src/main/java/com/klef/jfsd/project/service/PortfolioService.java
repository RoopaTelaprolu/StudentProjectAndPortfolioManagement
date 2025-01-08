package com.klef.jfsd.project.service;

import java.util.List;

import com.klef.jfsd.project.model.Portfolio;

public interface PortfolioService 
{
	public String AddPortfolio(Portfolio portfolio);
    public List<Portfolio> ViewAllPortfolios();
    public Portfolio GetPortfolioByID(int portfolioid);


}
