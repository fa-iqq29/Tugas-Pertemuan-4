/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuankeempat;

/**
 *
 * @author Faiq
 */
import java.sql.*;
import java.util.Scanner;

public class Create extends Koneksi {

    Scanner scanner = new Scanner(System.in);

    public void createTable() {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
            System.out.println("=== Menu Create ===");

            System.out.print("Masukkan nama tabel: ");
            String namaTabel = scanner.nextLine();
            
            System.out.print("Masukkan jumlah kolom: ");
            int jumlahKolom = scanner.nextInt();
            scanner.nextLine();
            
            StringBuilder kolom = new StringBuilder();
            for (int i = 1; i <= jumlahKolom; i++) {
                System.out.print("Masukkan nama kolom ke-" + i + ": ");
                String namaKolom = scanner.nextLine();

                System.out.print("Masukkan tipe data untuk kolom " + namaKolom + " (contoh: VARCHAR(50), INT, DATE): ");
                String tipeKolom = scanner.nextLine();

                kolom.append(namaKolom).append(" ").append(tipeKolom);
                if (i < jumlahKolom) {
                    kolom.append(", ");
                }
            }

            System.out.print("Masukkan nama kolom yang akan dijadikan PRIMARY KEY (atau tekan Enter jika tidak ada): ");
            String pk = scanner.nextLine();

            if (!pk.isEmpty()) {
                kolom.append(", PRIMARY KEY (").append(pk).append(")");
            }

            String sql = "DROP TABLE IF EXISTS " + namaTabel + " CASCADE; " + "CREATE TABLE " + namaTabel + " (" + kolom.toString() + ");";

            stmt.executeUpdate(sql);
            System.out.println("Tabel '" + namaTabel + "' berhasil dibuat!");

        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel." + e.getMessage());
            e.printStackTrace();
        }

    }
}

