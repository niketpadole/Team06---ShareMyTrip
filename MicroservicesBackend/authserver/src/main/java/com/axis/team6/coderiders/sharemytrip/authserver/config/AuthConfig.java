//package com.axis.team6.coderiders.sharemytrip.authserver.config;
//	
//	
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//

package com.axis.team6.coderiders.sharemytrip.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
	@Configuration
	@EnableWebSecurity
	public class AuthConfig {
//	
//	    @Bean
//	    public UserDetailsService userDetailsService(){
//	        return new CustomUserDetailsService();
//	    }
//	
//	
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf(csrf -> csrf.disable())
//	            .authorizeHttpRequests(authorize -> authorize
//	                .requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
//	                .anyRequest().authenticated()
//	            );
//	        return http.build();
//	    }
//	
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	
//	    @Bean
//	    public AuthenticationProvider authenticationProvider(){
//	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
//	        authenticationProvider.setUserDetailsService(userDetailsService());
//	        authenticationProvider.setPasswordEncoder(passwordEncoder());
//	        return authenticationProvider;
//	    }
//	
//	    @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	        return config.getAuthenticationManager();
//	    }
		
		@Bean
	    public UserDetailsService userDetailsService(){
	        return new CustomUserDetailsService();
	    }

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
					.csrf(csrf -> csrf.disable())
					.cors(cors -> cors.configurationSource(corsConfigurationSource()))
					.authorizeHttpRequests(authorize -> authorize
							.requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
							.anyRequest().authenticated()
					)
.requiresChannel(channel -> channel.anyRequest().requiresSecure());
			return http.build();
		}

		@Bean
		public CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.addAllowedOrigin("*"); // React app URL
			configuration.addAllowedMethod("*");
			configuration.addAllowedHeader("*");
			configuration.setAllowCredentials(true);

			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);

			return source;
		}

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}
	}
