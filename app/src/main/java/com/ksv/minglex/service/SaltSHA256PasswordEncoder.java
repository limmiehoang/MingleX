package com.ksv.minglex.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("saltSHA256PasswordEncoder")
public class SaltSHA256PasswordEncoder implements PasswordEncoder {

	@Autowired
	RandomStringService randomStringService;

	@Override
	public String encode(CharSequence rawPassword) {
		String salt = randomStringService.nextString();
		if (rawPassword == null || rawPassword.length() == 0) {
			System.out.println("Empty password");
			return null;
		}
		return sha256(rawPassword.toString() + salt) + salt;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String salt = encodedPassword.substring(encodedPassword.length()-21, encodedPassword.length());
		if (encodedPassword == null || encodedPassword.length() == 0) {
			System.out.println("Empty encoded password");
			return false;
		}

		return (encodedPassword.equals(sha256(rawPassword.toString() + salt) + salt));
	}

	private String sha256(String rawPassword) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
			StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null;
	}

}
