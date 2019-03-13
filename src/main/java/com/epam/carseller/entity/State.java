package com.epam.carseller.entity;

import java.util.Objects;

public class State {

    private int stateId;
    private int languageId;
    private String state;

    public int getStateId() {
        return stateId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getState() {
        return state;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return stateId == state1.stateId &&
                languageId == state1.languageId &&
                Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId, languageId, state);
    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", languageId=" + languageId +
                ", state='" + state + '\'' +
                '}';
    }
}
