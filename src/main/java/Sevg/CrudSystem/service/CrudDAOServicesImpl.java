package Sevg.CrudSystem.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Sevg.CrudSystem.dao.BlackTonerRepository;
import Sevg.CrudSystem.dao.CyanTonerRepository;
import Sevg.CrudSystem.dao.MagentaTonerRepository;
import Sevg.CrudSystem.dao.PrintersRepository;
import Sevg.CrudSystem.dao.RoleRepository;
import Sevg.CrudSystem.dao.UserRepository;
import Sevg.CrudSystem.dao.YellowTonerRepository;
import Sevg.CrudSystem.entities.BlackToner;
import Sevg.CrudSystem.entities.CyanToner;
import Sevg.CrudSystem.entities.MagentaToner;
import Sevg.CrudSystem.entities.Printers;
import Sevg.CrudSystem.entities.Role;
import Sevg.CrudSystem.entities.Users;
import Sevg.CrudSystem.entities.YellowToner;

@Service
public class CrudDAOServicesImpl implements CrudDAOServices {

	private UserRepository userRepo;
	
	private RoleRepository roleRepo;
	
	private PrintersRepository printerRepo;
	
	private BlackTonerRepository blackRepo;
	
	private MagentaTonerRepository magentaRepo;
	
	private CyanTonerRepository cyanRepo;
	
	private YellowTonerRepository yellowRepo;
	
	public static String CurrentlyLoginUser;
			
	@Autowired
	public CrudDAOServicesImpl (UserRepository theUserRepo,RoleRepository theRoleRepo,PrintersRepository thePrinterRepo,
									BlackTonerRepository Krepo,MagentaTonerRepository Mrepo,
									CyanTonerRepository Crepo,YellowTonerRepository Yrepo) {
		userRepo = theUserRepo;
		roleRepo = theRoleRepo;
		printerRepo = thePrinterRepo;
		blackRepo = Krepo;
		magentaRepo = Mrepo;
		cyanRepo = Crepo;
		yellowRepo = Yrepo;
	}
	
	
	@Override
	@Transactional
	public List<Users> findALLUsers() {
		return userRepo.findAll();
	}

	@Override
	@Transactional
	public Users findUserById(String theUsername) {
		Optional<Users> result= userRepo.findById(theUsername);
		
		Users theUsers = null;
		
		if (result.isPresent()) {
			theUsers = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("We did not find theUsers - " + theUsername);
		}
		
		return theUsers;
		
	}

	@Override
	@Transactional
	public void saveUser(Users theUser) {
		userRepo.save(theUser);
	}

	@Override
	@Transactional
	public void deleteUser(String theUsername) {
		userRepo.deleteById(theUsername);
	}

	@Override
	public Collection<Role> findRoleByName(String roleName) {
		return roleRepo.findRoleByName(roleName);
	}
	
	

	
	@Override
	public List<Users> searchBy(String theName) {
		 
		List<Users> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = userRepo.findByFirstNameContainsOrLastNameContainsOrUsernameContainsAllIgnoreCase(theName, theName, theName);
		}
		else {
			results = findALLUsers();
		}
		
		return results;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> result = userRepo.findById(username);
		
		Users theUsers = null;
		
		if (result.isPresent()) {
			theUsers = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("We did not find theUsers - " + username);
		}
		CurrentlyLoginUser=theUsers.getUsername();
		return new org.springframework.security.core.userdetails.User(theUsers.getUsername(), theUsers.getPassword(),
				mapRolesToAuthorities(theUsers.getRoles()));
		
	}


	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}	

	
	//---------------------------------PRINTERS-----------------------//
	
	@Override
	@Transactional
	public List<Printers> findAllPrinters() {
		return printerRepo.findAll();
	}


	@Override
	public void savePrinter(Printers thePrinter) {
		printerRepo.save(thePrinter);
	}


	@Override
	public Printers findPrinterById(String thePrintername) {
		Optional<Printers> result =printerRepo.findById(thePrintername);
		
		Printers thePrinter = null;
		
		if (result.isPresent()) {
			thePrinter = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("We did not find theUsers - " + thePrintername);
		}
		
		return thePrinter;

	}


	@Override
	public void deletePrinter(String thePrintername) {
		printerRepo.deleteById(thePrintername);
		
	}


	@Override
	public List<Printers> searchPrinters(String thePrinterName) {
	
		List<Printers> results = null;
		
		if (thePrinterName != null && (thePrinterName.trim().length() > 0)) {
			results = printerRepo.findByNameContainsOrManifacturerContainsAllIgnoreCase(thePrinterName, thePrinterName);
		}
		else {
			results = findAllPrinters();
		}
		
		return results;
	}


	
	//--------------------------TONERS----------------//
	
	//------------------------Black Toner-------------//
	@Override
	public List<BlackToner> findAllKtoners() {
		return blackRepo.findAll();
	}
	
	
	@Override public List<BlackToner> findKTonerByPrinterName(String thePrinterName) { 	
	  return blackRepo.findTonerByPrintersName(thePrinterName); 
	}
	 
	@Override
	public void deleteKToner(String theTonerName) {
		blackRepo.deleteById(theTonerName);
		
	}
	
	@Override
	public BlackToner findKTonerByName(String theTonerName) {
		return blackRepo.findKTonerByName(theTonerName);
	}
	
	@Override
	public void saveKToner(BlackToner theBlackToner) {
		 blackRepo.save(theBlackToner);
	}
	
	public List<BlackToner> searchKToner(String theBlackToner){
		
		List<BlackToner> results = null;
		
		if (theBlackToner != null && (theBlackToner.trim().length() > 0)) {
			results = blackRepo.findByNameContainsOrColorContainsAllIgnoreCase(theBlackToner, theBlackToner);
		}
		else {
			results = findAllKtoners();
		}
		
		return results;
	}

	
	//----------------------Magenta Toner--------------------//
	@Override
	public List<MagentaToner> findAllMtoners() {
		return magentaRepo.findAll();
	}
	
	
	@Override
	public List<MagentaToner> findMTonerByPrinterName(String thePrinterName) {
		return magentaRepo.findTonerByPrintersName(thePrinterName);
	}
	
	@Override
	public void deleteMToner(String theTonerName) {
		magentaRepo.deleteById(theTonerName);
		
	}
	
	@Override
	public MagentaToner findMTonerByName(String theTonerName) {
		return magentaRepo.findMTonerByName(theTonerName);
	}
	
	@Override
	public void saveMToner(MagentaToner theMagentaToner) {
		 magentaRepo.save(theMagentaToner);
	}
	
	
	public List<MagentaToner> searchMToner(String theMagentaToner){
		
		List<MagentaToner> results = null;
		
		if (theMagentaToner != null && (theMagentaToner.trim().length() > 0)) {
			results = magentaRepo.findByNameContainsOrColorContainsAllIgnoreCase(theMagentaToner, theMagentaToner);
		}
		else {
			results = findAllMtoners();
		}
		
		return results;
	}

	//----------------------Cyan Toner--------------------//
	@Override
	public List<CyanToner> findAllCtoners() {
		return cyanRepo.findAll();
	}
	
	@Override
	public List<CyanToner> findCTonerByPrinterName(String thePrinterName) {
		return cyanRepo.findTonerByPrintersName(thePrinterName);
	}
	
	@Override
	public void deleteCToner(String theTonerName) {
		cyanRepo.deleteById(theTonerName);
		
	}
	
	@Override
	public CyanToner findCTonerByName(String theTonerName) {
		return cyanRepo.findCTonerByName(theTonerName);
	}
	
	@Override
	public void saveCToner(CyanToner theCyanToner) {
		 cyanRepo.save(theCyanToner);
	}
	
	public List<CyanToner> searchCToner(String theCyanToner){
		
		List<CyanToner> results = null;
		
		if (theCyanToner != null && (theCyanToner.trim().length() > 0)) {
			results = cyanRepo.findByNameContainsOrColorContainsAllIgnoreCase(theCyanToner, theCyanToner);
		}
		else {
			results = findAllCtoners();
		}
		
		return results;
	}


	//---------------------Yellow Toner--------------------//
	
	public List<YellowToner> findAllYtoners() {
		return yellowRepo.findAll();
	}

	
	public List<YellowToner> findYTonerByPrinterName(String thePrinterName) {
		return yellowRepo.findTonerByPrintersName(thePrinterName);
	}

	
	public void deleteYToner(String theTonerName) {
		yellowRepo.deleteById(theTonerName);
		
	}
	
	
	public YellowToner findYTonerByName(String theTonerName) {
		return yellowRepo.findYTonerByName(theTonerName);
	}
	
	
	public void saveYToner(YellowToner theYellowToner) {
		 yellowRepo.save(theYellowToner);
	}
	
	
	public List<YellowToner> searchYToner(String theYellowToner){
		
		List<YellowToner> results = null;
		
		if (theYellowToner != null && (theYellowToner.trim().length() > 0)) {
			results = yellowRepo.findByNameContainsOrColorContainsAllIgnoreCase(theYellowToner, theYellowToner);
		}
		else {
			results = findAllYtoners();
		}
		
		return results;
	}
	


	


	


	


	

	
}
