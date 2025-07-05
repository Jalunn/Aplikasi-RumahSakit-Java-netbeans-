/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import java.sql.Connection;
import koneksi.Koneksi;

public class Main {
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();
    }
}
