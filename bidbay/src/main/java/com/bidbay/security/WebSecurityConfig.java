package com.bidbay.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Usuario;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	 @Autowired  
     private IUsuarioDao usuarioDao;
	 
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain  configure(HttpSecurity http) throws Exception  {

		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
		http
		.csrf().disable()
        .authorizeRequests()
       .requestMatchers( "/productos/form/{productId}").hasRole("ADMIN") // Admin should be able to delete
       .requestMatchers( "/productos/delete/{productId}").hasRole("ADMIN") // Admin should be able to update
        .requestMatchers("/productos/form").hasAnyRole("ADMIN", "MODERATOR") // Admin and Supervisor should be able to add product.
        .requestMatchers("/productos/listar").hasAnyRole("ADMIN", "MODERATOR", "USER") // All three users should be able to get all products.
        .requestMatchers(HttpMethod.GET,"/productos/buscar").hasAnyRole("ADMIN", "MODERATOR", "USER")// All three users should be able to get a product by id.
        .requestMatchers("/").hasAnyRole("USER") 
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
		return http.build();
	}

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	       
	        		
	        List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
	        
	        for (Usuario usuario : usuarios) {
	         
	        	UserDetails userNew = org.springframework.security.core.userdetails.User
	    	                .withUsername(usuario.getNick())
	    	                .password(passwordEncoder().encode(usuario.getPassword()))
	    	                .roles("USER")
	    	                .build();
	    	        manager.createUser(userNew);
			}
	        
	        @SuppressWarnings("deprecation")
			UserDetails userAdmin =
	        		 org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
						.username("admin")
						.password("admin")
						.roles("ADMIN")
						.build();
	    	      manager.createUser(userAdmin);
	    	      
	    	        return manager;
	    }
		    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        // Utiliza un codificador de contraseñas seguro
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	    
	   
}
