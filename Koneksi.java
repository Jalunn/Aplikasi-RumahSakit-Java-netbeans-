/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/rumah_sakit_db";
            String user = "root"; // default user MySQL
            String password = ""; // default XAMPP password = kosong
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi Berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
        return conn;
    }


}
