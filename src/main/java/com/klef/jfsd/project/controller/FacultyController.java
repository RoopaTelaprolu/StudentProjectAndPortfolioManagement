package com.klef.jfsd.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Project;
import com.klef.jfsd.project.service.FacultyService;
import com.klef.jfsd.project.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FacultyController 
{
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private ProjectService projectService;
	

	
	@GetMapping("/")
	public ModelAndView home()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@GetMapping("faclogin")
	public ModelAndView faclogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("faclogin");
		return mv;
	}
	
	@PostMapping("checkfaclogin")
	@ResponseBody
	public ModelAndView checkfacultylogin(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String femail=request.getParameter("femail");
		String fpwd=request.getParameter("fpwd");
		
		Faculty fac = facultyService.checkfaclogin(femail, fpwd);
		if(fac!=null)
		{
			if (fac.getStatus() != null && fac.getStatus().equalsIgnoreCase("accepted")) {

				HttpSession session = request.getSession();
                session.setAttribute("faculty", fac); 
                
                mv.setViewName("redirect:fachome"); 
        } 
		  else
		  {
                mv.setViewName("faclogin");
                mv.addObject("message", "Your account status is not accepted. Please contact your administrator.");
            }
        } 
		else
		{
			mv.setViewName("faclogin");
			mv.addObject("message","Login Failed");
		}
		return mv;
	}
	@GetMapping("fachome")
	public ModelAndView fachome()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fachome");
		return mv;
	}
	
	@GetMapping("faclogout")
	public ModelAndView faclogout(HttpServletRequest request)
	{
		
		HttpSession session =request.getSession();
		
		session.removeAttribute("faculty");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("faclogin");
		return mv;
	}
	
	@GetMapping("facsessionexpiry")
	public ModelAndView facsessionexpiry()
	{		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("facsessionexpiry");
		return mv;
	}
	
	@GetMapping("facprofile")
	public ModelAndView facprofile()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("facprofile");
		return mv;
	}
	
	@GetMapping("updatefac")
	public ModelAndView updatefac()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatefac");
		return mv;
	}
	@PostMapping("updatefacprofile")
	   public ModelAndView updatefacprofile(HttpServletRequest request)
	   {
	     ModelAndView mv = new ModelAndView();
	     
	    try
	    {
		      int id = Integer.parseInt(request.getParameter("fid"));
		      String name = request.getParameter("fname");
		      String gender = request.getParameter("fgender");
		      String dob = request.getParameter("fdob");
		      String dept = request.getParameter("fdept");
		      String password = request.getParameter("fpwd");
		      String location = request.getParameter("flocation");
		      String contact = request.getParameter("fcontact");
	      
	        Faculty fac = new Faculty();
	        fac.setId(id);
	        fac.setName(name);
	        fac.setGender(gender);
	        fac.setDepartment(dept);
	        fac.setDateofbirth(dob);
	        fac.setPassword(password);
	        fac.setLocation(location);
	        fac.setContact(contact);
	        
	        String msg = facultyService.updateFacultyProfile(fac);
	        
	        Faculty f = facultyService.displayFacultybyID(id);
	        
	         HttpSession session = request.getSession();
	         session.setAttribute("faculty",f); 
	       
	       
	        mv.setViewName("updatesuccess");
	        mv.addObject("message", msg);
	      
	    }
	    catch(Exception e)
	    {
	      mv.setViewName("updateerror");
	      mv.addObject("message", e);
	    }
	      return mv;
	   }

	@GetMapping("/viewallprojects")
	public ModelAndView viewAllProjects(HttpServletRequest request) {
	    ModelAndView mv = new ModelAndView();

	    // Fetch the faculty object from the session
	    HttpSession session = request.getSession();
	    Faculty faculty = (Faculty) session.getAttribute("faculty");

	    if (faculty != null) {
	        List<Project> projects = projectService.findProjectsByFaculty(faculty);
	        mv.setViewName("viewallprojects"); 
	        mv.addObject("projects", projects);
	    } else {
	        mv.setViewName("error");  
	        mv.addObject("message", "You need to log in as a faculty member to view the projects.");
	    }

	    return mv;
	}
	

}
