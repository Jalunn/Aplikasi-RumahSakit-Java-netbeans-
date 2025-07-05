package koneksi;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class layananDokter extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    private JTextField txtIdDokter, txtNamaDokter, txtSpesialis, txtJadwal;
    private JButton btnSimpan, btnReset, btnHapus, btnEdit, btnKembali;
    private JTable tableDokter;

    public layananDokter() {
        initComponents();
      conn = Koneksi.getConnection();
        tampilData();
    }

    private void initComponents() {
        setTitle("Layanan Dokter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID Dokter:");
        lblId.setBounds(20, 20, 100, 25);
        add(lblId);

        txtIdDokter = new JTextField();
        txtIdDokter.setBounds(130, 20, 200, 25);
        txtIdDokter.setEditable(false);
        add(txtIdDokter);

        JLabel lblNama = new JLabel("Nama Dokter:");
        lblNama.setBounds(20, 60, 100, 25);
        add(lblNama);

        txtNamaDokter = new JTextField();
        txtNamaDokter.setBounds(130, 60, 200, 25);
        add(txtNamaDokter);

        JLabel lblSpesialis = new JLabel("Spesialis:");
        lblSpesialis.setBounds(20, 100, 100, 25);
        add(lblSpesialis);

        txtSpesialis = new JTextField();
        txtSpesialis.setBounds(130, 100, 200, 25);
        add(txtSpesialis);


        JLabel lblJadwal = new JLabel("Jadwal:");
        lblJadwal.setBounds(20, 180, 100, 25);
        add(lblJadwal);

        txtJadwal = new JTextField();
        txtJadwal.setBounds(130, 180, 200, 25);
        add(txtJadwal);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 230, 90, 30);
        add(btnSimpan);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(120, 230, 90, 30);
        add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBounds(220, 230, 90, 30);
        add(btnHapus);

        btnReset = new JButton("Reset");
        btnReset.setBounds(20, 270, 90, 30);
        add(btnReset);

        btnKembali = new JButton("Kembali");
        btnKembali.setBounds(120, 270, 190, 30);
        add(btnKembali);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(350, 20, 420, 360);
        add(scrollPane);

        tableDokter = new JTable();
        scrollPane.setViewportView(tableDokter);

        // Aksi tombol
        btnSimpan.addActionListener(e -> simpanData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnReset.addActionListener(e -> resetForm());
        btnKembali.addActionListener(e -> {
            new dashboard().setVisible(true);
            dispose();
        });

        tableDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int baris = tableDokter.getSelectedRow();
                txtIdDokter.setText(model.getValueAt(baris, 0).toString());
                txtNamaDokter.setText(model.getValueAt(baris, 1).toString());
                txtSpesialis.setText(model.getValueAt(baris, 2).toString());
             
                txtJadwal.setText(model.getValueAt(baris, 4).toString());
            }
        });

        setVisible(true);
    }

    private void tampilData() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Spesialis");
       
        model.addColumn("Jadwal");

        try {
            String sql = "SELECT * FROM dokter";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_dokter"),
                    rs.getString("nama_dokter"),
                    rs.getString("spesialis"),
             
                    rs.getString("jadwal")
                });
            }

            tableDokter.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Data Dokter: " + e.getMessage());
        }
    }

    private void simpanData() {
        try {
            String sql = "INSERT INTO dokter (nama_dokter, spesialis, jadwal) VALUES (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaDokter.getText());
            pst.setString(2, txtSpesialis.getText());
        
            pst.setString(4, txtJadwal.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Dokter Berhasil Disimpan!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void editData() {
        try {
            String sql = "UPDATE dokter SET nama_dokter=?, spesialis=?, jadwal=? WHERE id_dokter=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaDokter.getText());
            pst.setString(2, txtSpesialis.getText());
           
            pst.setString(4, txtJadwal.getText());
            pst.setString(5, txtIdDokter.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Dokter Berhasil Diedit!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            String sql = "DELETE FROM dokter WHERE id_dokter=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtIdDokter.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Dokter Berhasil Dihapus!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtIdDokter.setText("");
        txtNamaDokter.setText("");
        txtSpesialis.setText("");
    
        txtJadwal.setText("");
        tableDokter.clearSelection();
    }
}
