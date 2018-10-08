package com.ksv.minglex.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@ConfigurationProperties(prefix = "storage")
@Service
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
