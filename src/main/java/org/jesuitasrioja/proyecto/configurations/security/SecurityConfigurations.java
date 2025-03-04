package org.jesuitasrioja.proyecto.configurations.security;

import org.jesuitasrioja.proyecto.configurations.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequiredArgsConstructor
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/alumnos/**").permitAll()
		.antMatchers(HttpMethod.GET, "/alumno/**").permitAll()
		.antMatchers(HttpMethod.POST, "/alumno/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/alumno/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/alumno/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/incidencias/**").permitAll()
		.antMatchers(HttpMethod.GET, "/incidencia/**").permitAll()
		.antMatchers(HttpMethod.POST, "/incidencia/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/incidencia/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/incidencia/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/clase/**").permitAll()
		.antMatchers(HttpMethod.GET, "/clase/**").permitAll()
		.antMatchers(HttpMethod.POST, "/clase/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/clase/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/clase/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/users/**").permitAll()
		.antMatchers(HttpMethod.GET, "/user/**").permitAll()
		.antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
		.anyRequest().permitAll().and().csrf().disable();
		
		http.addFilterBefore(jwtAuthorizationFilter,
				UsernamePasswordAuthenticationFilter.class);
	}

}
