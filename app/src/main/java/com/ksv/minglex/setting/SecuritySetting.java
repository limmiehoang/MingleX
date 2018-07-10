package com.ksv.minglex.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("KSVSecuritySetting")
@ConfigurationProperties(prefix = "security")
public class SecuritySetting {
    private Boolean sqlInjection;
    private String storePasswordSolution;
    private String storedXSS;
    private Boolean sessionFixation;
    private Boolean reflectedXSS;
    private Boolean csrf;

    public Boolean getSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(Boolean sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public String getStorePasswordSolution() {
        return storePasswordSolution;
    }

    public void setStorePasswordSolution(String storePasswordSolution) {
        this.storePasswordSolution = storePasswordSolution;
    }

    public String getStoredXSS() {
        return storedXSS;
    }

    public void setStoredXSS(String storedXSS) {
        this.storedXSS = storedXSS;
    }

    public Boolean getSessionFixation() {
        return sessionFixation;
    }

    public void setSessionFixation(Boolean sessionFixation) {
        this.sessionFixation = sessionFixation;
    }

    public Boolean getReflectedXSS() {
        return reflectedXSS;
    }

    public void setReflectedXSS(Boolean reflectedXSS) {
        this.reflectedXSS = reflectedXSS;
    }

	public Boolean getCsrf() {
		return csrf;
	}

	public void setCsrf(Boolean csrf) {
		this.csrf = csrf;
	}
}
