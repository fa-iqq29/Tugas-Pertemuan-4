/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuankeempat;

/**
 *
 * @author Faiq
 */
import java.util.Scanner;

public class Database {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n========= MENU UTAMA =========");
            System.out.println("1. Create Table");
            System.out.println("2. Insert Data");
            System.out.println("3. Read Data");
            System.out.println("4. Update Data");
            System.out.println("5. Delete Data");
            System.out.println("0. Keluar");
            System.out.print("PILIHAN> ");
            pilih = Integer.parseInt(sc.nextLine());

            switch (pilih) {
                case 1:
                    Create create = new Create();
                    create.createTable();
                    break;
                case 2:
                    Insert insert = new Insert();
                    insert.inputData();
                    break;
                case 3:
                    Read read = new Read();
                    read.lihatData();
                    break;
                case 4:
                    Update update = new Update();
                    update.updateData();
                    break;
                case 5:
                    Delete delete = new Delete();
                    delete.hapusData();
                    break;
                case 0:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 0);

    }
}
