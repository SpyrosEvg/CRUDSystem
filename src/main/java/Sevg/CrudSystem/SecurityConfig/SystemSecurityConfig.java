package Sevg.CrudSystem.SecurityConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import Sevg.CrudSystem.service.CrudDAOServices;

@Configuration
@EnableWebSecurity
public class SystemSecurityConfig {

	// add a reference to our security data source

		private DataSource securityDataSource;
		
		@Autowired
		private CrudDAOServices userService;

		@Autowired
		public SystemSecurityConfig(DataSource theSecurityDataSource) {
			securityDataSource = theSecurityDataSource;
		}

		
		
		@Bean
		public UserDetailsManager userDetailsManager() {
			return new JdbcUserDetailsManager(securityDataSource);
		}
	 

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").hasAnyRole("ADMIN","MANAGER","EMPLOYEE")
			.antMatchers("/searchAll").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/search").hasAnyRole("EMPLOYEE")
			.antMatchers("/AddUserForm").hasAnyRole("ADMIN")
			.antMatchers("/updateUserForm").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/deleteUser").hasAnyRole("ADMIN")
			.antMatchers("/saveUser").hasAnyRole("ADMIN")
			.antMatchers("/saveUserForUpdate").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/ChangeRoleForUser").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/ChangeUserRole").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/addPrinterForm").hasAnyRole("ADMIN")
			.antMatchers("/savePrinter").hasAnyRole("ADMIN")
			.antMatchers("/updatePrinterForm").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/deletePrinter").hasAnyRole("ADMIN")
			.antMatchers("/callMe").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/toner-stock").hasAnyRole("ADMIN","MANAGER")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/LoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
		
		return http.build();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	auth.setUserDetailsService(userService);
	auth.setPasswordEncoder(passwordEncoder());
	return auth;
	}
}
