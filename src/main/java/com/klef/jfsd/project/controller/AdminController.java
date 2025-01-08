package com.klef.jfsd.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.project.model.Admin;
import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Student;
import com.klef.jfsd.project.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController
{
	@Autowired
	private AdminService adminService;
	
	@GetMapping("adminhome")
	public ModelAndView adminhome()
	{
		ModelAndView mv= new ModelAndView();
		mv.setViewName("adminhome");
		
		return mv;
	}
	
	@GetMapping("adminlogin")
	public ModelAndView adminlogin()
	{
		ModelAndView mv= new ModelAndView();
		mv.setViewName("adminlogin");
		return mv;
	}
	@PostMapping("checkadminlogin")
	public ModelAndView checkadminlogin(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String auname=request.getParameter("auname");
		String apwd=request.getParameter("apwd");
		
		Admin admin = adminService.checkadminlogin(auname, apwd);
		if(admin!=null)
		{

			HttpSession session = request.getSession();
            session.setAttribute("admin", admin); 
			mv.setViewName("redirect:adminhome");
		}
		else
		{
			mv.setViewName("adminlogin");
			mv.addObject("message","Login Failed");
		}
		return mv;
	}
	@GetMapping("adminsessionexpiry")
	public ModelAndView adminsessionexpiry()
	{		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminsessionexpiry");
		return mv;
	}
	@GetMapping("viewallfacs")
    public ModelAndView viewallfacs()
    {
      ModelAndView mv = new ModelAndView();
      List<Faculty> faclist = adminService.ViewAllFaculties();
      mv.setViewName("viewallfacs");
      mv.addObject("faclist",faclist);
      
      long count = adminService.faccount();
	  mv.addObject("faccount", count);
		
      return mv;
    }
	
	@GetMapping("adminlogout")
	public ModelAndView adminlogout(HttpServletRequest request)
	{
        HttpSession session = request.getSession();
		session.removeAttribute("admin");
		
		ModelAndView mv= new ModelAndView();
		mv.setViewName("adminlogin");
		return mv;
	}
	
	@GetMapping("facreg")
	public ModelAndView facreg()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("facreg");
		return mv;
	}
	
	@PostMapping("insertfac")
	   public ModelAndView insertfac(HttpServletRequest request)
	   {
	    String name = request.getParameter("fname");
	    String gender = request.getParameter("fgender");
	    String dob = request.getParameter("fdob");
	    String dept = request.getParameter("fdept");
	    String location = request.getParameter("flocation");
	    String email = request.getParameter("femail");
	    String password = request.getParameter("fpwd");
	    String contact = request.getParameter("fcontact");
	    String status = "Registered";
	    
	      Faculty fac = new Faculty();
	      fac.setName(name);
	      fac.setGender(gender);
	      fac.setDepartment(dept);
	      fac.setDateofbirth(dob);
	      fac.setLocation(location);
	      fac.setEmail(email);
	      fac.setPassword(password);
	      fac.setContact(contact);
	      fac.setStatus(status);
	      
	      String msg = adminService.FacultyRegistration(fac);
	      
	      ModelAndView mv = new ModelAndView("regsuccess");
	      mv.addObject("message", msg);
	    
	      return mv;

	   }
	
	@GetMapping("updatefacstatus")
    public ModelAndView updatefacstatus()
    {
      ModelAndView mv = new ModelAndView();
      List<Faculty> faclist = adminService.ViewAllFaculties();
      mv.setViewName("updatefacstatus");
      mv.addObject("faclist",faclist);
      return mv;
    }
	
	@GetMapping("updatestatus")
	public String updatestatus(@RequestParam("id")  int fid , @RequestParam("status") String status)
	{
		adminService.updatefacultystatus(status, fid);
		return "redirect:/updatefacstatus";
	}
	
	@GetMapping("delete")
	public String deleteopertaion(@RequestParam("id") int fid)
	{
		adminService.deletefac(fid);
		return "redirect:/deletefac";
	}
	
	@GetMapping("deletefac")
    public ModelAndView deletefac()
    {
      ModelAndView mv = new ModelAndView();
      List<Faculty> faclist = adminService.ViewAllFaculties();
      mv.setViewName("deletefac");
      mv.addObject("faclist",faclist);
      return mv;
    }

	
	//student operations 
	
	@GetMapping("viewallstus")
    public ModelAndView viewallstus()
    {
      ModelAndView mv = new ModelAndView();
      List<Student> stulist = adminService.ViewAllStudents();
      mv.setViewName("viewallstus");
      mv.addObject("stulist",stulist);
      
      long count =adminService.stucount();
	  mv.addObject("saccount", count);
		
      return mv;
    }
	
	@GetMapping("stureg")
	public ModelAndView stureg()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stureg");
		return mv;
	}
	
	@PostMapping("insertstu")
	   public ModelAndView insertstu(HttpServletRequest request)
	   {
	    String name = request.getParameter("sname");
	    String gender = request.getParameter("sgender");
	    String dob = request.getParameter("sdob");
	    String dept = request.getParameter("sdept");
	    String location = request.getParameter("slocation");
	    String email = request.getParameter("semail");
	    String password = request.getParameter("spwd");
	    String contact = request.getParameter("scontact");
	    String status = "Registered";
	    
	      Student stu = new Student();
	      stu.setName(name);
	      stu.setGender(gender);
	      stu.setDepartment(dept);
	      stu.setDateofbirth(dob);
	      stu.setLocation(location);
	      stu.setEmail(email);
	      stu.setPassword(password);
	      stu.setContact(contact);
	      stu.setStatus(status);
	      
	      String msg = adminService.StudentRegistration(stu);
	      
	      ModelAndView mv = new ModelAndView("regssuccess");
	      mv.addObject("message", msg);
	    
	      return mv;

	   }
	
	@GetMapping("updatestustatus")
    public ModelAndView updatestustatus()
    {
      ModelAndView mv = new ModelAndView();
      List<Student> stulist = adminService.ViewAllStudents();
      mv.setViewName("updatestustatus");
      mv.addObject("stulist",stulist);
      return mv;
    }
	
	@GetMapping("updatesstatus")
	public String updatesstatus(@RequestParam("id")  int sid , @RequestParam("status") String status)
	{
		adminService.updatestudentstatus(status, sid);
		return "redirect:/updatestustatus";
	}
	
	@GetMapping("deletes")
	public String deleteopertaions(@RequestParam("id") int sid)
	{
		adminService.deletestu(sid);
		return "redirect:/deletestu";
	}
	
	@GetMapping("deletestu")
    public ModelAndView deletestu()
    {
      ModelAndView mv = new ModelAndView();
      List<Student> stulist = adminService.ViewAllStudents();
      mv.setViewName("deletestu");
      mv.addObject("stulist",stulist);
      return mv;
    }

}
