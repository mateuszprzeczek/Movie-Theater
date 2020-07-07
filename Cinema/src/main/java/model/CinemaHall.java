package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CinemaHall {
    private int number;
    private Map<Integer, Integer> audience;

    public static class Builder {
        private int number;

        private Map<Integer, Integer> audience = new HashMap<>();

        public Builder(int number) {
            this.number = number;
        }

        public Builder audience(Map<Integer, Integer> seatNr) {
            audience = seatNr; return this;
        }
        public CinemaHall build() {
            return new CinemaHall(this);
        }
    }


    public CinemaHall(Builder builder) {
        number = builder.number;
        audience = builder.audience;
    }

    public CinemaHall(int number, Map<Integer, Integer> audience) {
        this.number = number;
        this.audience = audience;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Map<Integer, Integer> getAudience() {
        return audience;
    }

    public void setAudience(Map<Integer, Integer> audience) {
        this.audience = audience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return getNumber() == that.getNumber() &&
                Objects.equals(getAudience(), that.getAudience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAudience());
    }

    @Override
    public String toString() {
        return   Integer.toString(number);
    }
}
