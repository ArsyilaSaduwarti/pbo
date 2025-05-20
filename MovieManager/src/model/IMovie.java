package model;

import java.sql.SQLException;
import java.util.List;

public interface IMovie {
    void addMovie(Movie movie) throws SQLException;
    Movie getMovieById(int id) throws SQLException;
    List<Movie> getAllMovies() throws SQLException;
    void updateMovie(Movie movie) throws SQLException;
    void deleteMovie(int id) throws SQLException;
}