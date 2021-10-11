package com.weather.common.enumeration;

public enum AuthorityType {
    ROLE_USER("일반회원");

    private String title;

    AuthorityType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
