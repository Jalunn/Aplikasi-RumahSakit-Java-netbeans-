-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 05, 2025 at 06:48 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rumah_sakit_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `apotek`
--

CREATE TABLE `apotek` (
  `id_apotek` int(11) NOT NULL,
  `nama_apotek` varchar(100) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `no_telepon` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `apotek`
--

INSERT INTO `apotek` (`id_apotek`, `nama_apotek`, `alamat`, `no_telepon`) VALUES
(1, 'ajiwaras', 'jln.cilandak kko', '08712312312');

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` int(11) NOT NULL,
  `nama_dokter` varchar(100) DEFAULT NULL,
  `spesialis` varchar(100) DEFAULT NULL,
  `jadwal` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `nama_dokter`, `spesialis`, `jadwal`) VALUES
(1, 'dr. Budi Hartono', 'Jantung', 'Senin & Rabu'),
(2, 'dr. Sari Melati', 'Anak', 'Selasa & Kamis'),
(3, 'dr. Doni Prasetyo', 'Bedah Umum', 'Senin & Jumat'),
(4, 'dr. Intan Safitri', 'Gigi', 'Rabu & Sabtu'),
(5, 'dr. Agus Riyanto', 'THT', 'Kamis & Sabtu');

-- --------------------------------------------------------

--
-- Table structure for table `layanan_operasi`
--

CREATE TABLE `layanan_operasi` (
  `id_operasi` int(11) NOT NULL,
  `nama_operasi` varchar(100) DEFAULT NULL,
  `deskripsi` text DEFAULT NULL,
  `biaya` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `layanan_operasi`
--

INSERT INTO `layanan_operasi` (`id_operasi`, `nama_operasi`, `deskripsi`, `biaya`) VALUES
(1, 'Sunat', 'Prosedur sunat dengan metode klem modern', 1500000.00),
(2, 'Operasi Jantung', 'Operasi bypass atau pemasangan ring jantung', 35000000.00),
(3, 'Operasi Usus Buntu', 'Pengangkatan usus buntu secara laparaskopi', 12000000.00),
(4, 'Operasi Caesar', 'Persalinan melalui pembedahan perut & rahim', 18000000.00),
(5, 'Operasi Gigi Bungsu', 'Pengangkatan gigi bungsu tumbuh miring', 5000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` int(11) NOT NULL,
  `nama_obat` varchar(100) DEFAULT NULL,
  `jenis_obat` varchar(50) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama_obat`, `jenis_obat`, `stok`, `harga`) VALUES
(1, 'Paracetamol', 'Tablet', 100, 5000.00),
(2, 'Amoxicillin', 'Kapsul', 80, 8000.00),
(3, 'Salep Kulit', 'Salep', 50, 12000.00),
(4, 'Vitamin C', 'Tablet', 200, 3000.00),
(5, 'Ibuprofen', 'Tablet', 75, 6000.00),
(6, 'Antasida', 'Tablet Kunyah', 60, 4000.00),
(7, 'Betadine', 'Cair', 30, 15000.00);

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `jenis_layanan` varchar(50) DEFAULT NULL,
  `total_biaya` decimal(12,2) DEFAULT NULL,
  `metode_pembayaran` enum('Tunai','Kartu','Transfer') DEFAULT NULL,
  `tanggal_pembayaran` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `nama_pasien`, `jenis_layanan`, `total_biaya`, `metode_pembayaran`, `tanggal_pembayaran`) VALUES
(1, 'Ahmad Setiawan', 'Sunat', 1500000.00, 'Tunai', '2025-06-17'),
(2, 'Nur Aisyah', 'Operasi Caesar', 18000000.00, 'Transfer', '2025-06-16'),
(3, 'Dimas Anggara', 'Paracetamol + Vitamin C', 8000.00, 'Tunai', '2025-06-17'),
(4, 'Siti Aminah', 'Operasi Jantung', 35000000.00, 'Kartu', '2025-06-15'),
(5, 'naufal', 'Apotek', 100000.00, 'Transfer', '2025-06-16');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin123'),
(2, 'petugas1', 'petugas123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apotek`
--
ALTER TABLE `apotek`
  ADD PRIMARY KEY (`id_apotek`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`);

--
-- Indexes for table `layanan_operasi`
--
ALTER TABLE `layanan_operasi`
  ADD PRIMARY KEY (`id_operasi`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apotek`
--
ALTER TABLE `apotek`
  MODIFY `id_apotek` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `dokter`
--
ALTER TABLE `dokter`
  MODIFY `id_dokter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `layanan_operasi`
--
ALTER TABLE `layanan_operasi`
  MODIFY `id_operasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `obat`
--
ALTER TABLE `obat`
  MODIFY `id_obat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
