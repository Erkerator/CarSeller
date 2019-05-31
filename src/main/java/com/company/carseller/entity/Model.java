package com.company.carseller.entity;

import java.util.Objects;

public class Model {

    private int modelId;
    private int brandId;
    private String model;

    public int getModelId() {
        return modelId;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getModel() {
        return model;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model1 = (Model) o;
        return modelId == model1.modelId &&
                brandId == model1.brandId &&
                Objects.equals(model, model1.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, brandId, model);
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelId=" + modelId +
                ", brandId=" + brandId +
                ", model='" + model + '\'' +
                '}';
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
