package com.lawencon.admin.config;

import java.util.ArrayList;
import java.util.List;

import com.lawencon.admin.filter.AuthorizationFilter;
import com.lawencon.admin.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
	@Bean
	public AuthenticationManager authManager(HttpSecurity http, UserService userService, BCryptPasswordEncoder encoder)
			throws Exception {

		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
				.passwordEncoder(encoder).and().build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter)
			throws Exception {

		http.cors();
		http.csrf().disable();

		http.addFilterAt(authorizationFilter, BasicAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/admin/login", HttpMethod.POST.toString()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/v3/**", HttpMethod.GET.toString()));
		matchers.add(new AntPathRequestMatcher("/admin/candidate/seeker", HttpMethod.POST.toString()));
		matchers.add(new AntPathRequestMatcher("/admin/candidate/seeker", HttpMethod.PUT.toString()));
		matchers.add(new AntPathRequestMatcher("/admin/applicant/seeker", HttpMethod.POST.toString()));
		matchers.add(new AntPathRequestMatcher("/admin/questions/**", HttpMethod.PATCH.toString()));
		return matchers;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> matchers().forEach(r -> {
			web.ignoring().requestMatchers(r);
		});
	}

	@Bean
	public org.springframework.web.servlet.config.annotation.WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
								HttpMethod.PATCH.name());
			}
		};
	}
}
