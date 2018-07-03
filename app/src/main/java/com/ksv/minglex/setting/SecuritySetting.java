package com.ksv.minglex.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("KSVSecuritySetting")
@ConfigurationProperties(prefix = "security")
public class SecuritySetting {
    private Boolean sqlInjection;
    private String storePasswordSolution;
    private Boolean storedXSS;
    private Boolean sessionFixation;

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

    public Boolean getStoredXSS() {
        return storedXSS;
    }

    public void setStoredXSS(Boolean storedXSS) {
        this.storedXSS = storedXSS;
    }

	public Boolean getSessionFixation() {
		return sessionFixation;
	}

	public void setSessionFixation(Boolean sessionFixation) {
		this.sessionFixation = sessionFixation;
	}

}
