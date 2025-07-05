/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FormDokter extends JFrame {
    JTable tabel;
    DefaultTableModel model;

    public FormDokter() {
        setTitle("Data Dokter");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header kolom
        String[] kolom = {"ID", "Nama", "Spesialis", "Jadwal"};
        model = new DefaultTableModel(kolom, 0);
        tabel = new JTable(model);

        JScrollPane scroll = new JScrollPane(tabel);
        add(scroll, BorderLayout.CENTER);

        tampilkanData();
    }

    private void tampilkanData() {
        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dokter");

            // Bersihkan dulu tabelnya
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("spesialis"),
                    rs.getString("jadwal")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + e.getMessage());
        }
    }
}
