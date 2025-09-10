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

public class Update extends Koneksi {

    Scanner sc = new Scanner(System.in);

    public void updateData() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
            System.out.println("=== Menu Update ===");

            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Daftar tabel yang ada:");
            while (tables.next()) {
                System.out.println("- " + tables.getString("TABLE_NAME"));
            }

            System.out.print("Pilih nama tabel: ");
            String namaTabel = sc.nextLine();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + namaTabel + " LIMIT 1");
            ResultSetMetaData rsMeta = rs.getMetaData();
            int kolomCount = rsMeta.getColumnCount();

            System.out.println("Kolom dalam tabel " + namaTabel + ":");
            for (int i = 1; i <= kolomCount; i++) {
                System.out.println("- " + rsMeta.getColumnName(i) + " (" + rsMeta.getColumnTypeName(i) + ")");
            }

            System.out.print("Masukkan nama kolom yang mau diubah: ");
            String kolomUpdate = sc.nextLine();

            System.out.print("Masukkan nilai baru untuk kolom " + kolomUpdate + ": ");
            String nilaiBaru = sc.nextLine();

            System.out.print("Masukkan nama kolom untuk kondisi WHERE: ");
            String kolomWhere = sc.nextLine();

            System.out.print("Masukkan nilai untuk kondisi WHERE: ");
            String nilaiWhere = sc.nextLine();

            String sql = "UPDATE " + namaTabel
                    + " SET " + kolomUpdate + " = '" + nilaiBaru + "'"
                    + " WHERE " + kolomWhere + " = '" + nilaiWhere + "';";

            int affected = stmt.executeUpdate(sql);

            if (affected > 0) {
                System.out.println("Data berhasil diupdate! (" + affected + " row)");
            } else {
                System.out.println("Tidak ada data yang cocok dengan kondisi WHERE.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal update data: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
