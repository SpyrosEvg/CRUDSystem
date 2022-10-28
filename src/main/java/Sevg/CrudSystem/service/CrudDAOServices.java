package Sevg.CrudSystem.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import Sevg.CrudSystem.entities.BlackToner;
import Sevg.CrudSystem.entities.CyanToner;
import Sevg.CrudSystem.entities.MagentaToner;
import Sevg.CrudSystem.entities.Printers;
import Sevg.CrudSystem.entities.Role;
import Sevg.CrudSystem.entities.Users;
import Sevg.CrudSystem.entities.YellowToner;

public interface CrudDAOServices extends UserDetailsService {
	
	//users
	public List<Users> findALLUsers();
	
	public Users findUserById(String theUSername);
	
	public void saveUser(Users theUser);
	
	public void deleteUser(String theUsername);
	
	public List<Users> searchBy(String theName);
	
	public Collection<Role> findRoleByName(String RoleName);
	
	
	
	//printers
	public List<Printers> findAllPrinters();
	
	public void savePrinter(Printers thePrinter);
	
	public Printers findPrinterById(String thePrintername);
	
	public void deletePrinter(String thePrintername);
	
	public List<Printers> searchPrinters(String thePrinterName);
	
	
	
	
	//Ktoners
	public List<BlackToner> findAllKtoners();
	
	public List<BlackToner> findKTonerByPrinterName(String thePritnerName);
	
	public BlackToner findKTonerByName(String theTonerName);
	
	public void saveKToner(BlackToner theBlackToner);
	
	public void deleteKToner(String theTonerName);
	
	public List<BlackToner> searchKToner(String theBlackToner);
	
	
	
	//Mtoners
	public List<MagentaToner> findAllMtoners();
	
	public List<MagentaToner> findMTonerByPrinterName(String thePritnerName);
	
	public MagentaToner findMTonerByName(String theTonerName);
	
	public void saveMToner(MagentaToner theMagentaToner);
	
	public void deleteMToner(String theTonerName);
	
	public List<MagentaToner> searchMToner(String theMagentaToner);
	
	//Ctoners
	public List<CyanToner> findAllCtoners();
	
	public List<CyanToner> findCTonerByPrinterName(String thePrinterName);

	public CyanToner findCTonerByName(String theTonerName);	
	
	public void saveCToner(CyanToner theCyanToner);
	
	public void deleteCToner(String theTonerName);
	
	public List<CyanToner> searchCToner(String theCyanToner);
	
	
	//Ytoners
	public List<YellowToner> findAllYtoners();

	public List<YellowToner> findYTonerByPrinterName(String thePrinterName);

	public YellowToner findYTonerByName(String theTonerName);
	
	public void saveYToner(YellowToner theMagentaToner);
	
	public void deleteYToner(String theTonerName);
	
	public List<YellowToner> searchYToner(String theYellowToner);

}
