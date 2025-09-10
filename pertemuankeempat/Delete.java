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

public class Delete extends Koneksi {

        Scanner sc = new Scanner(System.in);

        public void hapusData() {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement()) {
                System.out.println("=== Menu Delete ===");
                System.out.println("1. Hapus Tabel");
                System.out.println("2. Hapus Atribut (Kolom)");
                System.out.println("3. Hapus Nilai (Baris)");
                System.out.print("Pilih opsi: ");
                int pilihan = sc.nextInt();
                sc.nextLine();

                switch (pilihan) {
                    case 1: 
                        DatabaseMetaData meta = conn.getMetaData();
                        ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
                        System.out.println("Daftar tabel yang ada:");
                        while (tables.next()) {
                            System.out.println("- " + tables.getString("TABLE_NAME"));
                        }

                        System.out.print("Masukkan nama tabel yang mau dihapus: ");
                        String tabel = sc.nextLine();

                        String sqlDrop = "DROP TABLE IF EXISTS " + tabel + " CASCADE;";
                        stmt.executeUpdate(sqlDrop);
                        System.out.println("Tabel '" + tabel + "' berhasil dihapus!");
                        break;

                    case 2: 
                        DatabaseMetaData meta2 = conn.getMetaData();
                        ResultSet tables2 = meta2.getTables(null, null, "%", new String[]{"TABLE"});
                        System.out.println("Daftar tabel yang ada:");
                        while (tables2.next()) {
                            System.out.println("- " + tables2.getString("TABLE_NAME"));
                        }

                        System.out.print("Masukkan nama tabel: ");
                        String tabelKolom = sc.nextLine();

                        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabelKolom + " LIMIT 1");
                        ResultSetMetaData rsMeta = rs.getMetaData();
                        int kolomCount = rsMeta.getColumnCount();

                        System.out.println("Kolom dalam tabel " + tabelKolom + ":");
                        for (int i = 1; i <= kolomCount; i++) {
                            System.out.println("- " + rsMeta.getColumnName(i));
                        }

                        System.out.print("Masukkan nama kolom yang mau dihapus: ");
                        String kolom = sc.nextLine();

                        String sqlDropCol = "ALTER TABLE " + tabelKolom + " DROP COLUMN " + kolom + ";";
                        stmt.executeUpdate(sqlDropCol);
                        System.out.println("Kolom '" + kolom + "' berhasil dihapus dari tabel '" + tabelKolom + "'!");
                        break;

                    case 3: 
                        DatabaseMetaData meta3 = conn.getMetaData();
                        ResultSet tables3 = meta3.getTables(null, null, "%", new String[]{"TABLE"});
                        System.out.println("Daftar tabel yang ada:");
                        while (tables3.next()) {
                            System.out.println("- " + tables3.getString("TABLE_NAME"));
                        }

                        System.out.print("Masukkan nama tabel: ");
                        String tabelRow = sc.nextLine();

                        ResultSet rs2 = stmt.executeQuery("SELECT * FROM " + tabelRow + " LIMIT 1");
                        ResultSetMetaData rsMeta2 = rs2.getMetaData();
                        int kolomCount2 = rsMeta2.getColumnCount();

                        System.out.println("Kolom dalam tabel " + tabelRow + ":");
                        for (int i = 1; i <= kolomCount2; i++) {
                            System.out.println("- " + rsMeta2.getColumnName(i));
                        }

                        System.out.print("Masukkan nama kolom yang akan dihapus untuk kondisi WHERE: ");
                        String kolomWhere = sc.nextLine();

                        System.out.print("Masukkan nilai sesuai kolom yang akan dihapus untuk kondisi WHERE: ");
                        String nilaiWhere = sc.nextLine();

                        String sqlDelete = "DELETE FROM " + tabelRow
                                + " WHERE " + kolomWhere + " = '" + nilaiWhere + "';";

                        int affected = stmt.executeUpdate(sqlDelete);
                        if (affected > 0) {
                            System.out.println("Berhasil menghapus " + affected + " baris dari tabel '" + tabelRow + "'.");
                        } else {
                            System.out.println("Tidak ada data yang cocok dengan kondisi WHERE.");
                        }
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (SQLException e) {
                System.out.println("Gagal hapus data: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

