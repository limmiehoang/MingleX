package com.ksv.minglex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ksv.minglex.service.PlainTextPasswordEncoder;
import com.ksv.minglex.service.SHA256PasswordEncoder;
import com.ksv.minglex.service.SaltSHA256PasswordEncoder;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${security.storePasswordSolution}")
	private String storePasswordSolution;
	@Autowired
	private PlainTextPasswordEncoder plainTextPasswordEncoder;
	@Autowired
	private SHA256PasswordEncoder sha256PasswordEncoder;
	@Autowired
	private SaltSHA256PasswordEncoder saltSHA256PasswordEncoder;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		switch (storePasswordSolution) {
		case "Hash":
			return sha256PasswordEncoder;
		case "SaltHash":
			return saltSHA256PasswordEncoder;
		case "BCrypt":
			bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		default:
			return plainTextPasswordEncoder;
		}
	}

}
