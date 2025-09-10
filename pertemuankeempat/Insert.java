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

public class Insert extends Koneksi {

    Scanner sc = new Scanner(System.in);

    public void inputData() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
            System.out.println("=== Menu Insert ===");

            // 1. tampilkan daftar tabel yang ada
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Daftar tabel yang ada:");
            while (tables.next()) {
                System.out.println("- " + tables.getString("TABLE_NAME"));
            }

            // 2. pilih tabel
            System.out.print("Pilih nama tabel yang akan diinput: ");
            String namaTabel = sc.nextLine();

            // 3. cek struktur kolom
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + namaTabel + " LIMIT 1");
            ResultSetMetaData rsMeta = rs.getMetaData();
            int jumlahKolom = rsMeta.getColumnCount();

            // 4. minta input sesuai kolom
            StringBuilder kolomKolom = new StringBuilder();
            StringBuilder nilaiNilai = new StringBuilder();

            for (int i = 1; i <= jumlahKolom; i++) {
                String kolomNama = rsMeta.getColumnName(i);
                String kolomTipe = rsMeta.getColumnTypeName(i);

                // skip kolom auto increment
                if (kolomTipe.equalsIgnoreCase("serial") || kolomNama.equalsIgnoreCase("id")) {
                    continue;
                }

                System.out.print("Masukkan nilai untuk kolom " + kolomNama + " (" + kolomTipe + "): ");
                String nilai = sc.nextLine();

                kolomKolom.append(kolomNama);
                nilaiNilai.append("'").append(nilai).append("'");

                if (i < jumlahKolom) {
                    kolomKolom.append(", ");
                    nilaiNilai.append(", ");
                }

            }

            // 5. jalankan query insert
            String sql = "INSERT INTO " + namaTabel
                    + " (" + kolomKolom.toString() + ") "
                    + "VALUES (" + nilaiNilai.toString() + ")";
            stmt.executeUpdate(sql);
            System.out.println("Data berhasil dimasukkan ke tabel " + namaTabel + "!");

        } catch (SQLException e) {
            System.out.println("Gagal input data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
