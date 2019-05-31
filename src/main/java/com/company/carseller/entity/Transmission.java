package com.company.carseller.entity;

import java.util.Objects;

public class Transmission {

    private int transmissionId;
    private int languageId;
    private String transmission;

    public int getTransmissionId() {
        return transmissionId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transmission that = (Transmission) o;
        return transmissionId == that.transmissionId &&
                languageId == that.languageId &&
                Objects.equals(transmission, that.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transmissionId, languageId, transmission);
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "transmissionId=" + transmissionId +
                ", languageId=" + languageId +
                ", transmission='" + transmission + '\'' +
                '}';
    }
}
