package model;

public class Movie {
    private int id;
    private String judul;
    private double alur;
    private double penokohan;
    private double akting;
    private double rating;

    public Movie() {
    }

    public Movie(String judul, double alur, double penokohan, double akting) {
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        calculateRating();
    }

    public Movie(int id, String judul, double alur, double penokohan, double akting) {
        this.id = id;
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        calculateRating();
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public double getAlur() {
        return alur;
    }

    public double getPenokohan() {
        return penokohan;
    }

    public double getAkting() {
        return akting;
    }

    public double getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setAlur(double alur) {
        if (alur >= 0 && alur <= 5) {
            this.alur = alur;
            calculateRating();
        } else {
            System.err.println("Nilai Alur Cerita harus antara 0 dan 5.");
        }
    }

    public void setPenokohan(double penokohan) {
        if (penokohan >= 0 && penokohan <= 5) {
            this.penokohan = penokohan;
            calculateRating();
        } else {
            System.err.println("Nilai Penokohan harus antara 0 dan 5.");
        }
    }

    public void setAkting(double akting) {
        if (akting >= 0 && akting <= 5) {
            this.akting = akting;
            calculateRating();
        } else {
            System.err.println("Nilai Akting harus antara 0 dan 5.");
        }
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private void calculateRating() {
        this.rating = (this.alur + this.penokohan + this.akting) / 3.0;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Judul: " + judul + ", Alur: " + String.format("%.1f", alur) +
               ", Penokohan: " + String.format("%.1f", penokohan) + ", Akting: " + String.format("%.1f", akting) +
               ", Rating: " + String.format("%.2f", rating);
    }
}