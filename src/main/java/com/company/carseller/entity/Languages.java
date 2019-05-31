package com.company.carseller.entity;

import java.util.Objects;

public class Languages {

    private int languageId;
    private String locale;

    public int getLanguageId() {
        return languageId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Languages languages = (Languages) o;
        return languageId == languages.languageId &&
                Objects.equals(locale, languages.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId, locale);
    }

    @Override
    public String toString() {
        return "Languages{" +
                "languageId=" + languageId +
                ", locale='" + locale + '\'' +
                '}';
    }
}
