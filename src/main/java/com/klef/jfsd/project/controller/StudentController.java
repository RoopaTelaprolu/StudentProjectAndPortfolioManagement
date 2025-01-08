package com.klef.jfsd.project.controller;

import java.io.FileNotFoundException;
import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.project.model.Faculty;
import com.klef.jfsd.project.model.Portfolio;
import com.klef.jfsd.project.model.Project;
import com.klef.jfsd.project.model.Student;
import com.klef.jfsd.project.repository.ProjectRepository;
import com.klef.jfsd.project.service.FacultyService;
import com.klef.jfsd.project.service.PortfolioService;
import com.klef.jfsd.project.service.ProjectService;
import com.klef.jfsd.project.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
    @Autowired
    private ProjectService projectService;

    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private ProjectRepository projectRepository;
    
   @Autowired 
   private PortfolioService portfolioService;
   
	
	@GetMapping("stulogin")
	public ModelAndView stulogin()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stulogin");
		return mv;
	}
	
	@PostMapping("checkstulogin")
	@ResponseBody
	public ModelAndView checkstudentlogin(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		String semail=request.getParameter("semail");
		String spwd=request.getParameter("spwd");
		
		Student stu = studentService.checkstulogin(semail, spwd);
		if(stu!=null)
		{
			if (stu.getStatus() != null && stu.getStatus().equalsIgnoreCase("accepted")) {

				HttpSession session = request.getSession();
                session.setAttribute("student", stu); 
                
                mv.setViewName("redirect:stuhome"); 
        } 
		  else
		  {
                mv.setViewName("stulogin");
                mv.addObject("message", "Your account status is not accepted. Please contact your administrator.");
            }
        } 
		else
		{
			mv.setViewName("stulogin");
			mv.addObject("message","Login Failed");
		}
		return mv;
	}
	@GetMapping("stuhome")
	public ModelAndView stuhome()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stuhome");
		return mv;
	}
	
	@GetMapping("stulogout")
	public ModelAndView stulogout(HttpServletRequest request)
	{
		
		HttpSession session =request.getSession();
		
		session.removeAttribute("student");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stulogin");
		return mv;
	}
	
	@GetMapping("stusessionexpiry")
	public ModelAndView facsessionexpiry()
	{		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stusessionexpiry");
		return mv;
	}
	
	@GetMapping("stuprofile")
	public ModelAndView stuprofile()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("stuprofile");
		return mv;
	}
	
	@GetMapping("updatestu")
	public ModelAndView updatefac()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updatestu");
		return mv;
	}
	@PostMapping("updatestuprofile")
	   public ModelAndView updatefacprofile(HttpServletRequest request)
	   {
	     ModelAndView mv = new ModelAndView();
	     
	    try
	    {
		      int id = Integer.parseInt(request.getParameter("sid"));
		      String name = request.getParameter("sname");
		      String gender = request.getParameter("sgender");
		      String dob = request.getParameter("sdob");
		      String dept = request.getParameter("sdept");
		      String password = request.getParameter("spwd");
		      String location = request.getParameter("slocation");
		      String contact = request.getParameter("scontact");
	      
	        Student stu = new Student();
	        stu.setId(id);
	        stu.setName(name);
	        stu.setGender(gender);
	        stu.setDepartment(dept);
	        stu.setDateofbirth(dob);
	        stu.setPassword(password);
	        stu.setLocation(location);
	        stu.setContact(contact);
	        
	        String msg = studentService.updateStudentProfile(stu);
	        
	        Student s = studentService.displayStudentbyID(id);
	        
	         HttpSession session = request.getSession();
	         session.setAttribute("student",s); 
	       
	       
	        mv.setViewName("updatessuccess");
	        mv.addObject("message", msg);
	      
	    }
	    catch(Exception e)
	    {
	      mv.setViewName("updateserror");
	      mv.addObject("message", e);
	    }
	      return mv;
	   }

	@GetMapping("/addproject")
	public ModelAndView addProjectForm() {
	    ModelAndView mv = new ModelAndView("addproject");

	    List<Faculty> faculties = facultyService.getAllFaculties();

	    mv.addObject("faculties", faculties);

	    return mv;
	}

	 
	 

	 @PostMapping("/uploadproject")
	 public ModelAndView uploadProject(HttpServletRequest request, 
	                                    @RequestParam("projectnumber") String projectNumber,
	                                    @RequestParam("projectname") String projectName,
	                                    @RequestParam("projectdescription") String projectDescription,
	                                    @RequestParam("projecturl") String projectUrl,
	                                    @RequestParam("facultyId") int facultyId) {
	     ModelAndView mv = new ModelAndView();
	     
	     HttpSession session = request.getSession();
	     Student student = (Student) session.getAttribute("student");

	     if (student != null) {
	         try {
	             // Fetch the faculty by ID from the database
	             Faculty faculty = facultyService.getFacultyById(facultyId);

	             if (faculty == null) {
	                 throw new Exception("Invalid faculty selected.");
	             }

	             Project project = new Project();
	             project.setProjectNumber(projectNumber);
	             project.setName(projectName);
	             project.setDescription(projectDescription);
	             project.setUrl(projectUrl);

	             project.setStudent(student);
	             project.setFaculty(faculty); 
	             projectService.addProject(project);

	             mv.setViewName("projectmsg");
	             mv.addObject("message", "Project uploaded successfully!");
	         } catch (Exception e) {
	             mv.setViewName("projectmsg");
	             mv.addObject("message", "Error uploading project: " + e.getMessage());
	         }
	     } else {
	         mv.setViewName("projectmsg");
	         mv.addObject("message", "You must be logged in to upload a project.");
	     }

	     return mv;
	 }
	 
	 @GetMapping("viewmyprojects")
		public ModelAndView viewempsbydept(HttpServletRequest request)
		{
			ModelAndView mv= new ModelAndView("viewmyprojects");
			HttpSession session = request.getSession();
			Student stu =(Student) session.getAttribute("student"); 
			
			List<Project> projectlist=projectService.viewProjectsByStudentId(stu.getId());
			
			mv.addObject("projectlist",projectlist);
			return mv;
		}
	 @PostMapping("/submitfeedback")
	 public String submitFeedback(@RequestParam Long projectId, @RequestParam String feedbackText) {
	     Project project = projectRepository.findProjectById(projectId);

	     if (project != null) {
	         project.setFeedback(feedbackText);

	         projectRepository.save(project);

	         return "redirect:/viewallprojects";
	     } else {
	         return "redirect:/errorPage";
	     }
	 }
	 
	 @GetMapping("addportfolio")
	   public ModelAndView addportfolio()
	   {
		   ModelAndView mv = new ModelAndView("addportfolio");
		   return mv;
	   }
	 
	   
	  
	   @PostMapping("insertportfolio")
	   public ModelAndView insertportfolio(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception
	   {
		   String msg = null;
		   
		   ModelAndView mv = new ModelAndView();
		   
		   try
		   {
		
		   String skills = request.getParameter("skills");
		   String role = request.getParameter("role");
		
		   
			  byte[] bytes = file.getBytes();
			  Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			  //  creates a Blob object in Java from a byte array (bytes).
			  
			  Portfolio p = new Portfolio();
			  p.setSkills(skills);
			  p.setRole(role);
			  p.setFile(blob);
			  
			  msg=portfolioService.AddPortfolio(p);
			  System.out.println(msg);
			  mv.setViewName("portfoliomsg");
			  mv.addObject("message",msg);
		   }	  
		   catch(Exception e)
		   {
			   msg = e.getMessage();
			   System.out.println(msg.toString());
			   mv.setViewName("portfolioerror");
			   mv.addObject("message",msg);
		   }
		   return mv;
	   }
	   @GetMapping("viewallportfolios")
	   public ModelAndView viewallportfolios()
	   {
		   List<Portfolio> portfoliolist = portfolioService.ViewAllPortfolios();
		   
		   ModelAndView mv = new ModelAndView("viewallportfolios");
		   
		   mv.addObject("portfoliolist", portfoliolist);
		   
		   return mv;
	   }
	   
	   @GetMapping("displayfile")
	   public ResponseEntity<byte[]> displayFile(@RequestParam int id) throws Exception {
	       Portfolio portfolio = portfolioService.GetPortfolioByID(id); 
	       byte[] fileBytes = null;

	       if (portfolio.getFile() != null) {
	           fileBytes = portfolio.getFile().getBytes(1, (int) portfolio.getFile().length());
	           return ResponseEntity.ok()
	                   .contentType(MediaType.APPLICATION_PDF)
	                   .body(fileBytes);
	       }

	       throw new FileNotFoundException("No file associated with the given portfolio ID");
	   }







	    


	}

