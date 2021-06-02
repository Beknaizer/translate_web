package com.example.demo.models;

public class TranslateHistory {
    private String id;
    private String authorEmail;
    private String textToTranslate;
    private String translatedText;
    private String fromLanguage;
    private String toLanguage;

    public TranslateHistory() {
    }

    public TranslateHistory(String authorEmail, String textToTranslate, String translatedText, String fromLanguage, String toLanguage) {
        this.authorEmail = authorEmail;
        this.textToTranslate = textToTranslate;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translatedText = translatedText;
    }

    public TranslateHistory(String id, String authorEmail, String textToTranslate, String translatedText, String fromLanguage, String toLanguage) {
        this.id = id;
        this.authorEmail = authorEmail;
        this.textToTranslate = textToTranslate;
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        this.translatedText = translatedText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorId(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
