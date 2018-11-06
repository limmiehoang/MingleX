package com.ksv.minglex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ksv.minglex.service.RandomStringService;
import com.ksv.minglex.setting.SecuritySetting;

public class CSRFInterceptor implements HandlerInterceptor {

	@Autowired
	RandomStringService randomStringService;
	@Autowired
	SecuritySetting securitySetting;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (securitySetting.getCsrf() && "GET".equals(request.getMethod())) {
			HttpSession httpSession = request.getSession();
			String _csrf = (String) httpSession.getAttribute("_csrf");
			// Create csrf token
			if (_csrf == null) {
				_csrf = randomStringService.nextString();
				httpSession.setAttribute("_csrf", _csrf);
				System.out.println("create _csrf");
			}
			System.out.println("add csrf token");
			request.setAttribute("_csrfToken", _csrf);
		}
		return true;
	}
}
