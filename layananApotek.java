/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

/**
 *
 * @author Lenovo
 */



import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class layananApotek extends JFrame {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    public layananApotek() {
        initComponents();
       conn = Koneksi.getConnection();
        tampilData();
    }

    private void initComponents() {
        setTitle("Layanan Apotek");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama Obat:");
        lblNama.setBounds(20, 20, 100, 25);
        add(lblNama);

        txtNamaObat = new JTextField();
        txtNamaObat.setBounds(130, 20, 200, 25);
        add(txtNamaObat);

        JLabel lblJenis = new JLabel("Jenis Obat:");
        lblJenis.setBounds(20, 60, 100, 25);
        add(lblJenis);

        txtJenisObat = new JTextField();
        txtJenisObat.setBounds(130, 60, 200, 25);
        add(txtJenisObat);

        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setBounds(20, 100, 100, 25);
        add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(130, 100, 200, 25);
        add(txtHarga);

        JLabel lblStok = new JLabel("Stok:");
        lblStok.setBounds(20, 140, 100, 25);
        add(lblStok);

        txtStok = new JTextField();
        txtStok.setBounds(130, 140, 200, 25);
        add(txtStok);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 180, 90, 30);
        add(btnSimpan);

        btnReset = new JButton("Reset");
        btnReset.setBounds(130, 180, 90, 30);
        add(btnReset);

        btnKembali = new JButton("Kembali");
        btnKembali.setBounds(240, 180, 90, 30);
        add(btnKembali);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(350, 20, 320, 200);
        add(scrollPane);

        tableApotek = new JTable();
        scrollPane.setViewportView(tableApotek);

        // Event Button
        btnSimpan.addActionListener(e -> simpanData());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new dashboard().setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    private void tampilData() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Obat");
        model.addColumn("Jenis");
        model.addColumn("Harga");
        model.addColumn("Stok");

        try {
            String sql = "SELECT * FROM apotek";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_obat"),
                    rs.getString("nama_obat"),
                    rs.getString("jenis_obat"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                });
            }

            tableApotek.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Data: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            String sql = "INSERT INTO apotek (nama_obat, jenis_obat, harga, stok) VALUES (?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaObat.getText());
            pst.setString(2, txtJenisObat.getText());
            pst.setInt(3, Integer.parseInt(txtHarga.getText()));
            pst.setInt(4, Integer.parseInt(txtStok.getText()));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Obat Berhasil Disimpan!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtNamaObat.setText("");
        txtJenisObat.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }

    // Komponen global
    private JTextField txtNamaObat, txtJenisObat, txtHarga, txtStok;
    private JButton btnSimpan, btnReset, btnKembali;
    private JTable tableApotek;
}


