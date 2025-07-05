/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class FormApotek extends JFrame {
    private JTextField txtNamaApotek, txtAlamat, txtNoTelp;
    private JButton btnSimpan, btnKembali;

    public FormApotek() {
        setTitle("Form Apotek");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama Apotek");
        lblNama.setBounds(20, 20, 100, 25);
        add(lblNama);

        txtNamaApotek = new JTextField();
        txtNamaApotek.setBounds(140, 20, 200, 25);
        add(txtNamaApotek);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(20, 60, 100, 25);
        add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(140, 60, 200, 25);
        add(txtAlamat);

        JLabel lblNoTelp = new JLabel("No. Telepon");
        lblNoTelp.setBounds(20, 100, 100, 25);
        add(lblNoTelp);

        txtNoTelp = new JTextField();
        txtNoTelp.setBounds(140, 100, 200, 25);
        add(txtNoTelp);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(140, 140, 90, 30);
        add(btnSimpan);

        btnKembali = new JButton("Kembali");
        btnKembali.setBounds(250, 140, 90, 30);
        add(btnKembali);

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        btnKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new dashboard().setVisible(true);
                dispose();
            }
        });
    }

    private void simpanData() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO apotek (nama_apotek, alamat, no_telepon) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtNamaApotek.getText());
            stmt.setString(2, txtAlamat.getText());
            stmt.setString(3, txtNoTelp.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new FormApotek().setVisible(true);
    }
}
