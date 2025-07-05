package koneksi;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class layananOperasi extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    public layananOperasi() {
        initComponents();
       conn = Koneksi.getConnection();
        tampilData();
    }

    private void tampilData() {
        String[] kolom = {"ID", "Nama Operasi", "Biaya"};
        model = new DefaultTableModel(null, kolom);
        tabelOperasi.setModel(model);
        try {
            String sql = "SELECT * FROM layanan_operasi";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = {
                    rs.getString("id_operasi"),
                    rs.getString("nama_operasi"),
                    rs.getString("biaya_operasi")
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error tampil: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            String sql = "INSERT INTO layanan_operasi (id_operasi, nama_operasi, biaya_operasi) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNama.getText());
            pst.setString(3, txtBiaya.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error simpan: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtNama.setText("");
        txtBiaya.setText("");
    }

    private void initComponents() {
        setTitle("Form Layanan Operasi");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblJudul = new JLabel("Form Layanan Operasi");
        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblJudul.setBounds(150, 10, 250, 30);
        add(lblJudul);

        JLabel lblId = new JLabel("ID Operasi");
        lblId.setBounds(30, 60, 100, 20);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(130, 60, 150, 25);
        add(txtId);

        JLabel lblNama = new JLabel("Nama Operasi");
        lblNama.setBounds(30, 100, 100, 20);
        add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(130, 100, 150, 25);
        add(txtNama);

        JLabel lblBiaya = new JLabel("Biaya");
        lblBiaya.setBounds(30, 140, 100, 20);
        add(lblBiaya);

        txtBiaya = new JTextField();
        txtBiaya.setBounds(130, 140, 150, 25);
        add(txtBiaya);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(300, 60, 100, 25);
        btnSimpan.addActionListener(e -> simpanData());
        add(btnSimpan);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(300, 100, 100, 25);
        btnReset.addActionListener(e -> resetForm());
        add(btnReset);

        tabelOperasi = new JTable();
        JScrollPane scroll = new JScrollPane(tabelOperasi);
        scroll.setBounds(30, 200, 420, 150);
        add(scroll);
    }

    private JTextField txtId, txtNama, txtBiaya;
    private JTable tabelOperasi;
}
