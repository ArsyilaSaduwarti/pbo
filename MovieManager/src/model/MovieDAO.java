package model;

import config.DatabaseConnection; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovie {

    @Override
    public void addMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO movie (judul, alur, penokohan, akting, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, movie.getJudul());
            pstmt.setDouble(2, movie.getAlur());
            pstmt.setDouble(3, movie.getPenokohan());
            pstmt.setDouble(4, movie.getAkting());
            pstmt.setDouble(5, movie.getRating());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        movie.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public Movie getMovieById(int id) throws SQLException {
        String sql = "SELECT * FROM movie WHERE id = ?";
        Movie movie = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getDouble("alur"),
                        rs.getDouble("penokohan"),
                        rs.getDouble("akting")
                    );
                    movie.setRating(rs.getDouble("rating")); 
                }
            }
        }
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getDouble("alur"),
                    rs.getDouble("penokohan"),
                    rs.getDouble("akting")
                );
                movie.setRating(rs.getDouble("rating")); 
                movies.add(movie);
            }
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) throws SQLException {
        String sql = "UPDATE movie SET judul = ?, alur = ?, penokohan = ?, akting = ?, rating = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movie.getJudul());
            pstmt.setDouble(2, movie.getAlur());
            pstmt.setDouble(3, movie.getPenokohan());
            pstmt.setDouble(4, movie.getAkting());
            pstmt.setDouble(5, movie.getRating());
            pstmt.setInt(6, movie.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws SQLException {
        String sql = "DELETE FROM movie WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}