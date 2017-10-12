package com.example.businessinfoservice;

import java.util.List;

public class Subscriber {

    private String username;

    private List<String> packages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }
}
