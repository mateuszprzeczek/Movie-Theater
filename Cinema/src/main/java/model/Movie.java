package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private int length;
    private List<LocalDateTime> seances;

    public static class Builder {
        private final String title;
        private final int length;
        private final List<LocalDateTime> seances = new LinkedList<>();

        public Builder(String title, int length) {
            this.title = title;
            this.length = length;
        }
        public Builder addSeance(List<LocalDateTime> seances) {
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

    public List<LocalDateTime> getSeances() {
        return seances;
    }

    public void setSeances(List<LocalDateTime> seances) {
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