package com.ksv.minglex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ksv.minglex.interceptor.CSRFInterceptor;
import com.ksv.minglex.interceptor.CSRFPreventionInterceptor;
import com.ksv.minglex.service.PlainTextPasswordEncoder;
import com.ksv.minglex.service.SHA256PasswordEncoder;
import com.ksv.minglex.service.SaltSHA256PasswordEncoder;
import com.ksv.minglex.setting.SecuritySetting;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	SecuritySetting securitySetting;
	@Autowired
	private PlainTextPasswordEncoder plainTextPasswordEncoder;
	@Autowired
	private SHA256PasswordEncoder sha256PasswordEncoder;
	@Autowired
	private SaltSHA256PasswordEncoder saltSHA256PasswordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		switch (securitySetting.getStorePasswordSolution()) {
		case "Hash":
			return sha256PasswordEncoder;
		case "SaltHash":
			return saltSHA256PasswordEncoder;
		default:
			return plainTextPasswordEncoder;
		}
	}

	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CSRFInterceptor getCSRFInterceptor() {
		return new CSRFInterceptor();
	}

	@Bean
	public CSRFPreventionInterceptor getCSRFPreventionInterceptor() {
		return new CSRFPreventionInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if (securitySetting.getCsrf()) {
			// Register CSRF interceptor to generate CSRF token
			registry.addInterceptor(getCSRFInterceptor()).addPathPatterns("/login", "/registration", "/profile",
					"/explore");
			// Register CSRF prevention interceptor to check CSRF token
			registry.addInterceptor(getCSRFPreventionInterceptor()).addPathPatterns("/login", "/registration",
					"/status/new", "/profile");
		}
	}
}
