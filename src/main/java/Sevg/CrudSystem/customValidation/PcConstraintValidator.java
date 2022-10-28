package Sevg.CrudSystem.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PcConstraintValidator 
			implements ConstraintValidator<PcValidation,String>{

	private String pcPrefix;
	
	@Override
	public void initialize(PcValidation thePcValidation) {
		pcPrefix=thePcValidation.value();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean result =  value.startsWith(pcPrefix);
		
		return result;
	}

}
