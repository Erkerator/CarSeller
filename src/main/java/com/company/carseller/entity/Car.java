package com.company.carseller.entity;

import java.util.Date;
import java.util.Objects;

public class Car {

    private int carId;
    private String modelId;
    private String transmissionId;
    private String categoryId;
    private String stateId;
    private Date yearOfProduce;
    private Date dateOfCreation;
    private float engineVolume;
    private int languageId;
    private byte[] photo;
    private String userId;

    public int getCarId() {
        return carId;
    }

    public String getModelId() {
        return modelId;
    }

    public String getTransmissionId() {
        return transmissionId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getStateId() {
        return stateId;
    }

    public Date getYearOfProduce() {
        return yearOfProduce;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public int getLanguageId() {
        return languageId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public void setTransmissionId(String transmissionId) {
        this.transmissionId = transmissionId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public void setYearOfProduce(Date yearOfProduce) {
        this.yearOfProduce = yearOfProduce;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setEngineVolume(float engineVolume) {
        this.engineVolume = engineVolume;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                modelId == car.modelId &&
                transmissionId == car.transmissionId &&
                categoryId == car.categoryId &&
                stateId == car.stateId &&
                Float.compare(car.engineVolume, engineVolume) == 0 &&
                photo == car.photo &&
                userId == car.userId &&
                Objects.equals(yearOfProduce, car.yearOfProduce) &&
                Objects.equals(dateOfCreation, car.dateOfCreation) &&
                Objects.equals(languageId, car.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, modelId, transmissionId, categoryId, stateId, yearOfProduce, dateOfCreation, engineVolume, languageId, photo, userId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", modelId=" + modelId +
                ", transmissionId=" + transmissionId +
                ", categoryId=" + categoryId +
                ", stateId=" + stateId +
                ", yearOfProduce=" + yearOfProduce +
                ", dateOfCreation=" + dateOfCreation +
                ", engineVolume=" + engineVolume +
                ", color='" + languageId + '\'' +
                ", photo=" + photo +
                ", userId=" + userId +
                '}';
    }
}
