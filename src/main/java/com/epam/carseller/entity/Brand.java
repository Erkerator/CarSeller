package com.epam.carseller.entity;

import java.util.Objects;

public class Brand {

    private int brandId;
    private String brand;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand1 = (Brand) o;
        return brandId == brand1.brandId &&
                Objects.equals(brand, brand1.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, brand);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brand='" + brand + '\'' +
                '}';
    }
}
