package com.ksv.minglex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksv.minglex.configuration.WebMvcConfig;
import com.ksv.minglex.setting.SecuritySetting;

@RestController
public class ConfigurationRestController {

	@Autowired
	SecuritySetting securitySetting;
	@Autowired
	WebMvcConfig webMvcConfig;

	@RequestMapping(value = "/configuration/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateConfiguration(@RequestBody SecuritySetting settings, HttpServletRequest request) {

		securitySetting.setCsrf(settings.getCsrf());
		securitySetting.setReflectedXSS(settings.getReflectedXSS());
		securitySetting.setSessionFixation(settings.getSessionFixation());
		securitySetting.setSqlInjection(settings.getSqlInjection());
		securitySetting.setStoredXSS(settings.getStoredXSS());
		securitySetting.setStorePasswordSolution(settings.getStorePasswordSolution());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
