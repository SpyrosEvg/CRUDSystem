package Sevg.CrudSystem.customValidation;

import java.lang.annotation.ElementType;

import javax.validation.*;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = PcConstraintValidator.class)
@Target ({ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PcValidation {
	
	//pc layout
	public String value() default "Pc-";
	
	//error message
	public String message() default "Pc name must start with Pc-";
	
	//groups
	public Class<?>[] groups() default {};
	
	//payloads
	public Class<? extends Payload>[] payload() default {};
}
