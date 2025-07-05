package koneksi;

import com.toedter.calendar.JDateChooser;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class Pembayaran extends JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    // komponen GUI
    JTextField txtNamaPasien, txtTotal;
    JComboBox<String> cmbLayanan, cmbMetode;
    JDateChooser dateChooser;
    JTable table;
    JButton btnSimpan, btnEdit, btnHapus, btnBersih;

    public Pembayaran() {
        initComponents();
        conn = Koneksi.getConnection(); // koneksi ke database
        tampilData();
    }

    private void initComponents() {
        setTitle("Form Pembayaran");
        setLayout(null);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblNama = new JLabel("Nama Pasien:");
        JLabel lblLayanan = new JLabel("Jenis Layanan:");
        JLabel lblTotal = new JLabel("Total Biaya:");
        JLabel lblMetode = new JLabel("Metode Pembayaran:");
        JLabel lblTanggal = new JLabel("Tanggal:");

        txtNamaPasien = new JTextField();
        txtTotal = new JTextField();
        cmbLayanan = new JComboBox<>(new String[]{"Dokter", "Apotek", "Operasi"});
        cmbMetode = new JComboBox<>(new String[]{"Cash", "Transfer", "E-Wallet"});
        dateChooser = new com.toedter.calendar.JDateChooser(); // pastikan library JCalendar sudah ditambahkan

        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnBersih = new JButton("Bersih");

        table = new JTable();
        model = new DefaultTableModel(new String[]{
            "ID", "Nama", "Layanan", "Total", "Metode", "Tanggal"
        }, 0);
        table.setModel(model);
        JScrollPane sp = new JScrollPane(table);

        // penempatan komponen
        lblNama.setBounds(20, 20, 120, 25);
        txtNamaPasien.setBounds(150, 20, 200, 25);
        lblLayanan.setBounds(20, 60, 120, 25);
        cmbLayanan.setBounds(150, 60, 200, 25);
        lblTotal.setBounds(20, 100, 120, 25);
        txtTotal.setBounds(150, 100, 200, 25);
        lblMetode.setBounds(20, 140, 120, 25);
        cmbMetode.setBounds(150, 140, 200, 25);
        lblTanggal.setBounds(20, 180, 120, 25);
        dateChooser.setBounds(150, 180, 200, 25);

        btnSimpan.setBounds(20, 230, 80, 25);
        btnEdit.setBounds(110, 230, 80, 25);
        btnHapus.setBounds(200, 230, 80, 25);
        btnBersih.setBounds(290, 230, 80, 25);
        sp.setBounds(380, 20, 390, 300);

        add(lblNama); add(txtNamaPasien);
        add(lblLayanan); add(cmbLayanan);
        add(lblTotal); add(txtTotal);
        add(lblMetode); add(cmbMetode);
        add(lblTanggal); add(dateChooser);
        add(btnSimpan); add(btnEdit); add(btnHapus); add(btnBersih);
        add(sp);

        // event simpan
        btnSimpan.addActionListener(e -> simpanData());
    }

    private void tampilData() {
        try {
            model.setRowCount(0);
            String sql = "SELECT * FROM pembayaran";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_pembayaran"),
                    rs.getString("nama_pasien"),
                    rs.getString("jenis_layanan"),
                    rs.getDouble("total_biaya"),
                    rs.getString("metode_pembayaran"),
                    rs.getDate("tanggal_pembayaran")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error tampil data: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            String sql = "INSERT INTO pembayaran (nama_pasien, jenis_layanan, total_biaya, metode_pembayaran, tanggal_pembayaran) VALUES (?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaPasien.getText());
            pst.setString(2, cmbLayanan.getSelectedItem().toString());
            pst.setDouble(3, Double.parseDouble(txtTotal.getText()));
            pst.setString(4, cmbMetode.getSelectedItem().toString());

            java.util.Date tgl = dateChooser.getDate();
            java.sql.Date sqlDate = new java.sql.Date(tgl.getTime());
            pst.setDate(5, sqlDate);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            tampilData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Pembayaran().setVisible(true);
    }
}
