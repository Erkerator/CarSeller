package com.company.carseller.entity;

import java.util.Objects;

public class Category {

    private int categoryId;
    private int languageId;
    private String category;

    public int getCategoryId() {
        return categoryId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getCategory() {
        return category;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return categoryId == category1.categoryId &&
                languageId == category1.languageId &&
                Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, languageId, category);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", languageId=" + languageId +
                ", category='" + category + '\'' +
                '}';
    }
}
