/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import koneksi.layananDokter;
import koneksi.layananApotek;
import koneksi.layananOperasi;
import koneksi.Pembayaran;


public class dashboard extends JFrame {

    public dashboard() {
        setTitle("Dashboard Rumah Sakit");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        URL imageUrl = getClass().getResource("/gambar/background.png");
if (imageUrl != null) {
    ImageIcon bg = new ImageIcon(imageUrl);
} else {
    System.out.println("Gambar tidak ditemukan!");
}

        JPanel panel = new JPanel() {
            ImageIcon bg = new ImageIcon(getClass().getResource("/gambar/background.png"));

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);

        JLabel judul = new JLabel("DASHBOARD RUMAH SAKIT");
        judul.setFont(new Font("Segoe UI", Font.BOLD, 24));
        judul.setForeground(Color.WHITE);
        judul.setBounds(330, 30, 400, 30);
        panel.add(judul);

        JButton btnDokter = new JButton("Layanan Dokter");
        btnDokter.setBounds(100, 120, 200, 40);
        panel.add(btnDokter);

        JButton btnApotek = new JButton("Obat & Apotek");
        btnApotek.setBounds(100, 180, 200, 40);
        panel.add(btnApotek);

        JButton btnOperasi = new JButton("Layanan Operasi");
        btnOperasi.setBounds(100, 240, 200, 40);
        panel.add(btnOperasi);

        JButton btnBayar = new JButton("Pembayaran");
        btnBayar.setBounds(100, 300, 200, 40);
        panel.add(btnBayar);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(100, 360, 200, 40);
        panel.add(btnLogout);
        
    btnDokter.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Menuju Layanan Dokter");
        new layananDokter().setVisible(true);
        dispose(); // kalau kamu ingin dashboard-nya ditutup
    }
});


        btnApotek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Menuju Layanan Apotek");
            new layananApotek().setVisible(true);
        dispose();
            }
        });

        btnOperasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Menuju Layanan Operasi");
           new layananOperasi().setVisible(true);
           dispose();
            }
        });
        btnBayar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        new Pembayaran().setVisible(true);
        dispose(); // opsional, jika ingin dashboard ditutup
    }
});


        btnLogout.addActionListener(e -> {
            int jawab = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == JOptionPane.YES_OPTION) {
                new loginForm().setVisible(true);
                this.dispose();
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        new dashboard().setVisible(true);
    }
}
