/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuankeempat;

/**
 *
 * @author Faiq
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Read extends Koneksi {

    Scanner sc = new Scanner(System.in);

    public void lihatData() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
            System.out.println("=== Menu Read ===");

            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Daftar tabel yang ada:");
            while (tables.next()) {
                System.out.println("- " + tables.getString("TABLE_NAME"));
            }

            System.out.print("Pilih tabel yang mau dilihat: ");
            String namaTabel = sc.nextLine();

            String sql = "SELECT * FROM " + namaTabel;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsMeta = rs.getMetaData();
            int jumlahKolom = rsMeta.getColumnCount();

            while (rs.next()) {
                StringBuilder hasilBaris = new StringBuilder();

                for (int i = 1; i <= jumlahKolom; i++) {
                    hasilBaris.append(String.valueOf(rs.getObject(i)));

                    if (i < jumlahKolom) {
                        hasilBaris.append(" | ");
                    }
                }
                System.out.println(hasilBaris.toString());
            }

        } catch (SQLException e) {
            System.out.println("Gagal melihat data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
