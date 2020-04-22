package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Movie implements Serializable {
    private String title;
    private int length;
    private Map<LocalDateTime, Seance> seances;

    public static class Builder {
        private String title;
        private int length;
        private Map<LocalDateTime, Seance> seances = new HashMap<>();

        public Builder(String title, int length) {
            this.title = title;
            this.length = length;
        }
        public Builder addSeance(Map<LocalDateTime, Seance> addedSeance) {
            seances = addedSeance;
            return this;
        }
        public Movie build() {
            return new Movie(this);
        }
    }
    private Movie(Builder builder) {
        title = builder.title;
        length = builder.length;
        seances = builder.seances;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<LocalDateTime, Seance> getSeances() {
        return seances;
    }

    public void setSeances(Map<LocalDateTime, Seance> seances) {
        this.seances = seances;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", length=" + length +
                ", seances=" + seances +
                '}';
    }
}