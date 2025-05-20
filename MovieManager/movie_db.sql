-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Bulan Mei 2025 pada 10.12
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movie_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `judul` varchar(100) DEFAULT NULL,
  `alur` double DEFAULT NULL,
  `penokohan` double DEFAULT NULL,
  `akting` double DEFAULT NULL,
  `rating` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `movie`
--

INSERT INTO `movie` (`id`, `judul`, `alur`, `penokohan`, `akting`, `rating`) VALUES
(1, 'Avengers: Endgame', 4.8, 4.7, 4.9, 4.8),
(2, 'Interstellar', 4.9, 4.5, 4.6, 4.666666666),
(3, 'Parasite', 4.7, 4.8, 4.4, 4.633333333),
(4, 'The Batman', 4.2, 4.3, 4.5, 4.333333333),
(5, 'Joker', 4.6, 4.7, 5, 4.766666666),
(6, 'Frozen II', 4.1, 3.9, 4.2, 4.066666666),
(7, 'Inception', 4.9, 4.6, 4.8, 4.766666666),
(8, 'Dilan 1990', 4, 4.2, 4.1, 4.1),
(9, 'KKN di Desa Penari', 3.7, 3.7, 3.8, 3.733333333333333),
(10, 'Top Gun: Maverick', 4.6, 4.3, 4.4, 4.433333333);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
