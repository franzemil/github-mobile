package com.example.franzemil.gitmobile.models;

import java.io.Serializable;


public class Language implements Serializable{

    private String language;
    private String code;
    private String image;

    public String getLanguage() {
        return language;
    }

    public Language(String language, String code, String image) {
        this.language = language;
        this.code = code;
        this.image = image;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
