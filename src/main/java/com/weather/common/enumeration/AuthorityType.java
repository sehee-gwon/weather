package com.weather.common.enumeration;

public enum AuthorityType {
    ROLE_USER("μΌλ°νμ");

    private String title;

    AuthorityType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
