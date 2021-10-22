package com.couponFormLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private UserDetailsServiceImpl uds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(uds);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().logout().logoutSuccessUrl("/");
		
	    http.formLogin().loginPage("/login");
	    http.authorizeRequests()
	    .antMatchers(HttpMethod.GET,"/createCoupon/{code:^[A-Z]*$}","/index","/showCreateCoupon","/CreateResponse").hasRole("ADMIN")
	    .antMatchers(HttpMethod.GET,"getCouponDetails/**").hasAnyRole("USER","ADMIN")
	    .antMatchers("/","/login","/tryLogin").permitAll()
	   .antMatchers(HttpMethod.POST,"/saveCoupon","/getCouponDetails","/coupondetails").hasRole("ADMIN");
	   
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
		
	}	
	
}
