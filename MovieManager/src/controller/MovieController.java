package controller;

import model.Movie;
import model.MovieDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class MovieController {
    private MovieDAO movieDAO;

    public MovieController() {
        this.movieDAO = new MovieDAO();
    }

    public void addMovie(Movie movie) {
        try {
            movieDAO.addMovie(movie);
            JOptionPane.showMessageDialog(null, "Data Movie Berhasil Ditambahkan", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {
        try {
            return movieDAO.getAllMovies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    public void updateMovie(Movie movie) {
        try {
            movieDAO.updateMovie(movie);
            JOptionPane.showMessageDialog(null, "Data Movie Berhasil Diupdate", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteMovie(int id) {
        try {
            movieDAO.deleteMovie(id);
            JOptionPane.showMessageDialog(null, "Data Movie Berhasil Dihapus", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public Movie getMovieById(int id) {
        try {
            return movieDAO.getMovieById(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data movie: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }
}