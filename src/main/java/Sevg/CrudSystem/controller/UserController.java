package Sevg.CrudSystem.controller;



import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Sevg.CrudSystem.entities.BlackToner;
import Sevg.CrudSystem.entities.CyanToner;
import Sevg.CrudSystem.entities.MagentaToner;
import Sevg.CrudSystem.entities.Printers;
import Sevg.CrudSystem.entities.Role;
import Sevg.CrudSystem.entities.Users;
import Sevg.CrudSystem.entities.YellowToner;
import Sevg.CrudSystem.service.CrudDAOServices;
import Sevg.CrudSystem.service.CrudDAOServicesImpl;

@Controller
@RequestMapping("/")
public class UserController {
	
	//initialize DaoService
	private CrudDAOServices ticketService;
	//initialize temporary variables  
	private String printerName,tempUpdatedTonerName,tempUpdatedUser;
	
	//inject DaoService in the constructor
	@Autowired
	public UserController(CrudDAOServices theTicketService) {
		ticketService = theTicketService;
	}
	
	//--------------------------simple methods--------------------------
	//This Method is trimming white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}
	
	//Searching in the database
	@GetMapping("/search")
	public String search(@RequestParam("name") String theName,
						 Model theModel) {
		
		String result="/users/Users";
		
		// search all List 
		List<Users> theUsers = ticketService.searchBy(theName);
		List<Printers> thePrinter = ticketService.searchPrinters(theName);
		List<BlackToner> theBlackToner = ticketService.searchKToner(theName);
		List<MagentaToner> theMagentaToner = ticketService.searchMToner(theName);
		List<CyanToner> theCyanToner = ticketService.searchCToner(theName);
		List<YellowToner> theYellowToner = ticketService.searchYToner(theName);

		
		//if nothing is found
		if(!theUsers.isEmpty()) {
			// add to the spring model
			theModel.addAttribute("users", theUsers);
			
			// send to /employees/list
			result = "/users/Users";
		}
		else if(!thePrinter.isEmpty()) {
			
			theModel.addAttribute("printers", thePrinter);
			
			result = "/printers/Printers";
		}
		else if(!theBlackToner.isEmpty()) {
			
			theModel.addAttribute("blackToner", theBlackToner);
			
			result = "/printers/tonerStock";
		}
		else if(!theMagentaToner.isEmpty()) {
			
			theModel.addAttribute("magentaToner", theMagentaToner);
			
			result = "/printers/tonerStock";
		}
		else if(!theCyanToner.isEmpty()) {
			
			theModel.addAttribute("cyanToner", theCyanToner);
			
			result = "/printers/tonerStock";
		}
		else if(!theYellowToner.isEmpty()) {
			
			theModel.addAttribute("yellowToner", theYellowToner);
			
			result = "/printers/tonerStock";
		}
		
		return result;
	}
	
	//Convert a String into BCrtypt
	public String passwordEncoder(String pass) {
		return new BCryptPasswordEncoder().encode(pass);
	}
	

	//--------------------------Users--------------------------------------

	
	@GetMapping("/users")
	public String findAllUsers(Model theModel){
		
		//get list of users for db
		List<Users> theUsers = ticketService.findALLUsers();
		
		theModel.addAttribute("users",theUsers);
		
		return "users/Users";
	}
	
	@GetMapping("/addUserForm")
	public String AddUser (Model theModel) {
		
		Users theUser = new Users();
		
		theModel.addAttribute("user",theUser);
		
		return "/users/addUser";
	}
	
	@GetMapping("/updateUserForm")
	public String updateUserForm(@RequestParam("username") String username,Model theModel) {
		
		Users theUsers = ticketService.findUserById(username);
		
		theModel.addAttribute("user",theUsers);
		
		tempUpdatedUser = username;
		
		return "/users/updateUser";
		
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
		
		ticketService.deleteUser(username);
		
		return "redirect:/users";
	}
	
	@PostMapping("/saveUser")
	public String saveTheUser(@Valid @ModelAttribute("user") Users theUser,
								BindingResult theBindingResult) {
		
		//if the validations fails
		if(theBindingResult.hasErrors()) {
			return "/users/addUser";
		}
		else {
			//if the password is empty that means new users , 
			//so we initialize the role as Employee , password as 123456 and as enable user
			if(theUser.getPassword()==null) {
				
				Collection<Role> theRole=ticketService.findRoleByName("ROLE_EMPLOYEE");
	
				theUser.setEnable(1);
				
				theUser.setPassword(passwordEncoder("123456"));
				
				theUser.setRoles(theRole);
			}
			//save the user 
			ticketService.saveUser(theUser);
			
			return "redirect:/users";
		}
	}
	
	@PostMapping("/saveUserForUpdate")
	public String saveTheUserForUpdate(@Valid @ModelAttribute("user") Users theUser,
								BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "/users/updateUser";
		}
		else {
			//Search the user
			Users user = ticketService.findUserById(tempUpdatedUser);
			 
			//is the user exist delete the user
			if(!(user==null)) {
				 deleteUser(tempUpdatedUser);
			 }
			
			//save the user 
			ticketService.saveUser(theUser);
			
			return "redirect:/users";
		}
	}
	
	@GetMapping("/ChangeRoleForUser")
	public String ChangeRoleForUser(@RequestParam("username") String username,
									Model theModel) {
		
		Users theUsers = ticketService.findUserById(username);
		
		theModel.addAttribute("user",theUsers);

		return "/users/ChangeRole";
	}
	
	@PostMapping("/ChangeUserRole")
	public String ChangeUserRole(@Valid @RequestParam("role") String theRole,@ModelAttribute("user") Users theUser,
									BindingResult theBindingResult) {
		//retrieve the role from the Database
		 Collection<Role> Role = ticketService.findRoleByName(theRole);
			
		 theUser.setRoles(Role);

		 saveTheUser(theUser,theBindingResult);
		 
		 return "redirect:/users";
	}
	
	@GetMapping("/changePass")
	public String ChangePassword(Model theModel) {
		
		//Fetching the user that is currently login
		Users theUsers = ticketService.findUserById(CrudDAOServicesImpl.CurrentlyLoginUser);
		
		theModel.addAttribute("user",theUsers);
		
		return "/users/changePass";
	}
	
	@PostMapping("/ChangeUserPass")
	public String ChangeUserPass(@RequestParam("newPassword")String newPassword,
								 @RequestParam("oldPassword")String oldPassword,
								 @RequestParam("confirmPassword")String confirmPassword,
								 Model theModel) {
		
		 String Streturn=null;
		
		 Users theUsers = ticketService.findUserById(CrudDAOServicesImpl.CurrentlyLoginUser);
		
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		
		 //Validate the password
		 if(encoder.matches(oldPassword, theUsers.getPassword()) 
				  && newPassword.equals(confirmPassword)) {
			  
			  theUsers.setPassword(passwordEncoder(newPassword));
			  
			  theModel.addAttribute("user",theUsers);
				
			  ticketService.saveUser(theUsers);
			  
			  Streturn = "redirect:/";
		  }
		  else {
			  Streturn = ChangePassword(theModel);
		  }
		  return Streturn;
	}
	

	//-------------------------------- Printers ----------------------------
	@GetMapping("/printers")
	public String findAllPrinter(Model theModel){
		
		//get list of users 
		List<Printers> thePrinters = ticketService.findAllPrinters();
		
		theModel.addAttribute("printers",thePrinters);
		
		return "/printers/Printers";
	}
	
	
	
	@GetMapping("/addPrinterForm")
	public String AddPrinter (Model theModel) {
		
		Printers thePrinter = new Printers();
		
		theModel.addAttribute("printer",thePrinter);
		
		return "/printers/addPrinter";
	}
	
	@PostMapping("/savePrinter")
	public String saveThePrinter(@Valid @ModelAttribute("printer") Printers thePrinter,
								BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "/printers/addPrinter";
		}
		else {
			//save the user 
			ticketService.savePrinter(thePrinter);
			
			return "redirect:/printers";
		}
	}
	
	@GetMapping("/updatePrinterForm")
	public String updatePrinterForm(@RequestParam("name") String name,Model theModel) {
		
		Printers thePrinter = ticketService.findPrinterById(name);
		
		theModel.addAttribute("printer",thePrinter);
		
		return "/printers/addPrinter";
		
	}
	
	@GetMapping("/deletePrinter")
	public String deletePrinter(@RequestParam("name") String name) {
		
		ticketService.deletePrinter(name);
		
		return "redirect:/printers";
	}
	
	
	//------------------------------Toners------------------------------
	

	
	
	@GetMapping("/toner-stock")
	public String FindTonerStock (@RequestParam("name")String thePrinterName, 
									Model theModel) {
		
		printerName=thePrinterName;
		
		
		List<BlackToner> theBlackTonerList = ticketService.findKTonerByPrinterName(thePrinterName);
		List<MagentaToner> theMagentaTonerList = ticketService.findMTonerByPrinterName(thePrinterName);
		List<CyanToner> theCyanTonerList = ticketService.findCTonerByPrinterName(thePrinterName);
		List<YellowToner> theYellowTonerList = ticketService.findYTonerByPrinterName(thePrinterName);
		
		
		theModel.addAttribute("blackToner",theBlackTonerList);
		theModel.addAttribute("magentaToner",theMagentaTonerList);
		theModel.addAttribute("cyanToner",theCyanTonerList);
		theModel.addAttribute("yellowToner",theYellowTonerList);
	
		
		return "/printers/tonerStock";
	}
	
	
	
	  @GetMapping("/callMe") 
	  public String callMe (Model theModel) { 
		  return FindTonerStock(printerName , theModel); 
	  }
	 
	//------------------Black Toner ------------------------
	
	@GetMapping("/addKQuantity")
	public String addKQuantity(@RequestParam("name")String theTonerName ,
								Model theModel) {
		
		BlackToner theBlackToner = ticketService.findKTonerByName(theTonerName);
		
		int theStock = theBlackToner.getStock()+1;
		
		theBlackToner.setStock(theStock);
		
		
		ticketService.saveKToner(theBlackToner);
		
		
		theModel.addAttribute("blackToner",theBlackToner);
		
		return FindTonerStock(printerName , theModel);
		
		
	}
	
	@GetMapping("/removeKQuantity")
	public String removeKQuantity(@RequestParam("name")String theTonerName ,
								Model theModel) {
		
		BlackToner theBlackToner = ticketService.findKTonerByName(theTonerName);
		
		
		int theStock = theBlackToner.getStock()-1;
		
		theBlackToner.setStock(theStock);
		
		ticketService.saveKToner(theBlackToner);
		
		theModel.addAttribute("blackToner",theBlackToner);
		
		
		return FindTonerStock(printerName , theModel);
	}
	
	 
	  @GetMapping("/updateKtonerForm")
		public String updateKtonerForm(@RequestParam("name") String theTonerName,Model theModel) {
			
		    BlackToner theBlackToner = ticketService.findKTonerByName(theTonerName);
		    
		    tempUpdatedTonerName = theTonerName;
		    
			theModel.addAttribute("blackToner",theBlackToner);
			
			return "/printers/updateKToner";
			
		}
		
		@GetMapping("/deleteKtoner")
		public String deleteKtoner(@RequestParam("name") String TheTonerName,
											Model theModel) {
			
			ticketService.deleteKToner(TheTonerName);
			
			return FindTonerStock(printerName , theModel);
		}
		

	  
	  @PostMapping("/saveKtoner") 
	  public String saveKToner(@Valid @ModelAttribute("blackToner") BlackToner theBlackToner, 
			  					BindingResult theBindingResult,Model theModel) {
	  
		  if(theBindingResult.hasErrors()) {
			  return "/printers/addKToner";
		  }
		  else {
			  //save the toner 
			  Printers thePrinter = ticketService.findPrinterById(printerName); 
			  			  
			  thePrinter.addKtoner(theBlackToner);

			  ticketService.saveKToner(theBlackToner);
			  			
			  return FindTonerStock(printerName , theModel); 
		  }
	  }
	  

	  @PostMapping("/updateKtoner") 
	  public String updateKToner(@Valid @ModelAttribute("blackToner") BlackToner theBlackToner, 
			  					BindingResult theBindingResult,Model theModel) {
	  
		  if(theBindingResult.hasErrors()) {
			  return "/printers/updateKToner";
		  }
		  else { 
			  Printers thePrinter = ticketService.findPrinterById(printerName); 
  			  
			  thePrinter.addKtoner(theBlackToner);
			  
			  BlackToner blackToner = ticketService.findKTonerByName(tempUpdatedTonerName);

			  ticketService.saveKToner(theBlackToner);

			  if(!(blackToner==null)) {
				  deleteKtoner(tempUpdatedTonerName,theModel);
			  }
			  
			  			  
			  return FindTonerStock(printerName , theModel);
		  }
		   
	  }
	//------------------Magenta Toner ------------------------
	  @GetMapping("/addMQuantity")
		public String addMQuantity(@RequestParam("name")String theTonerName ,
									Model theModel) {
			
			MagentaToner theMagentaToner = ticketService.findMTonerByName(theTonerName);
			
			int theStock = theMagentaToner.getStock()+1;
			
			theMagentaToner.setStock(theStock);
			
			
			ticketService.saveMToner(theMagentaToner);
			
			
			theModel.addAttribute("magentaToner",theMagentaToner);
			
			return FindTonerStock(printerName , theModel);
			
			
		}
		
		@GetMapping("/removeMQuantity")
		public String removeMQuantity(@RequestParam("name")String theTonerName ,
									Model theModel) {
			
			MagentaToner theMagentaToner = ticketService.findMTonerByName(theTonerName);
			
			
			int theStock = theMagentaToner.getStock()-1;
			
			theMagentaToner.setStock(theStock);
			
			ticketService.saveMToner(theMagentaToner);
			
			theModel.addAttribute("magentaToner",theMagentaToner);
			
			
			return FindTonerStock(printerName , theModel);
		}
		
		 
		  @GetMapping("/updateMtonerForm")
			public String updateMtonerForm(@RequestParam("name") String theTonerName,Model theModel) {
				
			    MagentaToner theMagentaToner = ticketService.findMTonerByName(theTonerName);
			    
			    tempUpdatedTonerName = theTonerName;
			    
			    //
			    
				theModel.addAttribute("magentaToner",theMagentaToner);
				
				return "/printers/updateMToner";
				
			}
			
			@GetMapping("/deleteMtoner")
			public String deleteMtoner(@RequestParam("name") String TheTonerName,
												Model theModel) {
				
				ticketService.deleteMToner(TheTonerName);
				
				return FindTonerStock(printerName , theModel);
			}
			

		  
		  @PostMapping("/saveMtoner") 
		  public String saveMToner(@Valid @ModelAttribute("magentaToner") MagentaToner theMagentaToner, 
				  					BindingResult theBindingResult,Model theModel) {
		  
			  if(theBindingResult.hasErrors()) {
				  return "/printers/addMToner";
			  }
			  else {
				  //save the toner 
				  Printers thePrinter = ticketService.findPrinterById(printerName); 
				  
				  thePrinter.addMtoner(theMagentaToner);  
				  
				  ticketService.saveMToner(theMagentaToner);

				  return FindTonerStock(printerName , theModel); 
			  }
		  }
	  
		  @PostMapping("/updateMToner") 
		  public String updateMToner(@Valid @ModelAttribute("magentaToner") MagentaToner theMagentaToner, 
				  					BindingResult theBindingResult,Model theModel) {
		  
			  if(theBindingResult.hasErrors()) {
				  return "/printers/updateMToner";
			  }
			  else { 
				  MagentaToner magentaToner = ticketService.findMTonerByName(tempUpdatedTonerName);

				  
				  if(!(magentaToner==null)) {
					  deleteMtoner(tempUpdatedTonerName,theModel);
				  }
				  
				  ticketService.saveMToner(theMagentaToner);
				  
				  return FindTonerStock(printerName , theModel);
			  }
			   
		  }
	  
	//------------------Cayn Toner ------------------------

		  @GetMapping("/addCQuantity")
				public String addCQuantity(@RequestParam("name")String theTonerName ,
											Model theModel) {
					
					CyanToner theCyanToner = ticketService.findCTonerByName(theTonerName);
					
					int theStock = theCyanToner.getStock()+1;
					
					theCyanToner.setStock(theStock);
					
					
					ticketService.saveCToner(theCyanToner);
					
					
					theModel.addAttribute("cyanToner",theCyanToner);
					
					return FindTonerStock(printerName , theModel);
					
					
				}
				
				@GetMapping("/removeCQuantity")
				public String removeCQuantity(@RequestParam("name")String theTonerName ,
											Model theModel) {
					
					CyanToner theCyanToner = ticketService.findCTonerByName(theTonerName);
					
					
					int theStock = theCyanToner.getStock()-1;
					
					theCyanToner.setStock(theStock);
					
					ticketService.saveCToner(theCyanToner);
					
					theModel.addAttribute("cyanToner",theCyanToner);
					
					
					return FindTonerStock(printerName , theModel);
				}
				
				 
				  @GetMapping("/updateCtonerForm")
					public String updateCtonerForm(@RequestParam("name") String theTonerName,Model theModel) {
						
					    CyanToner theCyanToner = ticketService.findCTonerByName(theTonerName);
					    
					    tempUpdatedTonerName = theTonerName;
					    
					    //
					    
						theModel.addAttribute("cyanToner",theCyanToner);
						
						return "/printer/updateCToner";
						
					}
					
					@GetMapping("/deleteCtoner")
					public String deleteCtoner(@RequestParam("name") String TheTonerName,
														Model theModel) {
						
						ticketService.deleteCToner(TheTonerName);
						
						return FindTonerStock(printerName , theModel);
					}
					

				  
				  @PostMapping("/saveCtoner") 
				  public String saveCToner(@Valid @ModelAttribute("cyanToner") CyanToner theCyanToner, 
						  					BindingResult theBindingResult,Model theModel) {
				  
					  if(theBindingResult.hasErrors()) {
						  return "/printers/addCToner";
					  }
					  else {
						  //save the toner 
						  Printers thePrinter = ticketService.findPrinterById(printerName); 
						  
						  thePrinter.addCtoner(theCyanToner);  
						  
						  ticketService.saveCToner(theCyanToner);

						  return FindTonerStock(printerName , theModel); 
					  }
				  }
			  
				  @PostMapping("/updateCtoner") 
				  public String updateCToner(@Valid @ModelAttribute("cyanToner") CyanToner theCyanToner, 
						  					BindingResult theBindingResult,Model theModel) {
				  
					  if(theBindingResult.hasErrors()) {
						  return "/printers/updateCToner";
					  }
					  else { 
						  CyanToner cyanToner = ticketService.findCTonerByName(tempUpdatedTonerName);
						  

						  
						  if(!(cyanToner==null)) {
							  deleteCtoner(tempUpdatedTonerName,theModel);
						  }
						  
						  ticketService.saveCToner(theCyanToner);
						  
						  return FindTonerStock(printerName , theModel);
					  }
					   
				  }
			  
		  
	//------------------Yellow Toner ------------------------
		  @GetMapping("/addYQuantity")
			public String addYQuantity(@RequestParam("name")String theTonerName ,
										Model theModel) {
				
				YellowToner theYellowToner = ticketService.findYTonerByName(theTonerName);
				
				int theStock = theYellowToner.getStock()+1;
				
				theYellowToner.setStock(theStock);
				
				
				ticketService.saveYToner(theYellowToner);
				
				
				theModel.addAttribute("yellowToner",theYellowToner);
				
				return FindTonerStock(printerName , theModel);
				
				
			}
			
			@GetMapping("/removeYQuantity")
			public String removeYQuantity(@RequestParam("name")String theTonerName ,
										Model theModel) {
				
				YellowToner theYellowToner = ticketService.findYTonerByName(theTonerName);
				
				
				int theStock = theYellowToner.getStock()-1;
				
				theYellowToner.setStock(theStock);
				
				ticketService.saveYToner(theYellowToner);
				
				theModel.addAttribute("yellowToner",theYellowToner);
				
				
				return FindTonerStock(printerName , theModel);
			}
			
			 
			  @GetMapping("/updateYtonerForm")
				public String updateYtonerForm(@RequestParam("name") String theTonerName,Model theModel) {
					
				    YellowToner theYellowToner = ticketService.findYTonerByName(theTonerName);
				    
				    tempUpdatedTonerName = theTonerName;
				    
				    //
				    
					theModel.addAttribute("yellowToner",theYellowToner);
					
					return "/printers/addYToner";
					
				}
				
				@GetMapping("/deleteYtoner")
				public String deleteYtoner(@RequestParam("name") String TheTonerName,
													Model theModel) {
					
					ticketService.deleteYToner(TheTonerName);
					
					return FindTonerStock(printerName , theModel);
				}
				

			  
			  @PostMapping("/saveYtoner") 
			  public String saveYToner(@Valid @ModelAttribute("yellowToner") YellowToner theYellowToner, 
					  					BindingResult theBindingResult,Model theModel) {
			  
				  if(theBindingResult.hasErrors()) {
					  return "/printers/addYToner";
				  }
				  else {
					  //save the toner 
					  Printers thePrinter = ticketService.findPrinterById(printerName); 
					  
					  thePrinter.addYtoner(theYellowToner);  
					  
					  ticketService.saveYToner(theYellowToner);
					  
					  return FindTonerStock(printerName , theModel); 
				  }
			  }
			  
			  @PostMapping("/updateYtoner") 
			  public String updateYToner(@Valid @ModelAttribute("yellowToner") YellowToner theYellowToner, 
					  					BindingResult theBindingResult,Model theModel) {
			  
				  if(theBindingResult.hasErrors()) {
					  return "/printers/updateYToner";
				  }
				  else { 
					  YellowToner yellowToner = ticketService.findYTonerByName(tempUpdatedTonerName);

					  
					  if(!(yellowToner==null)) {
						  deleteYtoner(tempUpdatedTonerName,theModel);
					  }
					  
					  ticketService.saveYToner(theYellowToner);
					  
					  return FindTonerStock(printerName , theModel);
				  }
				   
			  }
		   
		  
	//------------------Univesal Toner ------------------------
	  @GetMapping("/addTonerForm") 
	  public String AddToner (@RequestParam("name") String theToner,Model theModel) {
	  
		String returnString=null;
		
			switch(theToner) {
				case "Black":{
					BlackToner theBlackToner = new BlackToner();
					theModel.addAttribute("blackToner",theBlackToner);
					returnString = "/printers/addKToner";
					break;
				}
				
				case "Magenta":{
					MagentaToner theMagentaToner = new MagentaToner();
					theModel.addAttribute("magentaToner",theMagentaToner);
					returnString = "/printers/addMToner";
					break;
				}
				case "Cyan":{
					CyanToner theCyanToner = new CyanToner();
					theModel.addAttribute("cyanToner",theCyanToner);
					returnString = "/printers/addCToner";
					break;
				}
				default:{
					YellowToner theYellowToner = new YellowToner();
					theModel.addAttribute("yellowToner",theYellowToner);
					returnString = "/printers/addYToner";
					break;
				}
			}  
			 return returnString;
	  }
	  
}
