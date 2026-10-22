package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Membuat pengelola karakter
        CharacterManager manager = new CharacterManager();

        // Membuat karakter
        Character karakter1 = new Character(
                "Arthas", CharacterType.WARRIOR);

        Character karakter2 = new Character(
                "Merlin", CharacterType.MAGE);

        Character karakter3 = new Character(
                "Legolas", CharacterType.ARCHER);

        // =====================================
        // 1. MENAMBAHKAN KARAKTER
        // =====================================
        System.out.println("=== TP5: CHARACTER MANAGER ===");

        System.out.println("Arthas ditambahkan: "
                + manager.addCharacter(karakter1));

        System.out.println("Merlin ditambahkan: "
                + manager.addCharacter(karakter2));

        System.out.println("Legolas ditambahkan: "
                + manager.addCharacter(karakter3));

        // Menguji penolakan nama duplikat
        Character duplikat = new Character(
                "Arthas", CharacterType.MAGE);

        System.out.println("\n=== UJI NAMA DUPLIKAT ===");
        System.out.println("Arthas duplikat ditambahkan: "
                + manager.addCharacter(duplikat));

        // =====================================
        // 2. MENAMPILKAN SEMUA KARAKTER
        // =====================================
        System.out.println("\n=== DAFTAR KARAKTER ===");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        System.out.println("Total karakter: "
                + manager.getTotalCharacters());

        // =====================================
        // 3. MENCARI KARAKTER
        // =====================================
        System.out.println("\n=== PENCARIAN KARAKTER ===");

        Character hasil = manager.findByName("Merlin");

        if (hasil != null) {
            System.out.println("Karakter ditemukan: " + hasil);
        } else {
            System.out.println("Karakter tidak ditemukan.");
        }

        // =====================================
        // 4. DEMO LEVEL DAN RATA-RATA
        // =====================================
        System.out.println("\n=== ANALISIS LEVEL ===");

        karakter1.setLevel(3);
        karakter2.setLevel(5);
        karakter3.setLevel(2);

        System.out.println("Level Arthas: "
                + karakter1.getLevel());

        System.out.println("Level Merlin: "
                + karakter2.getLevel());

        System.out.println("Level Legolas: "
                + karakter3.getLevel());

        System.out.println("Rata-rata level: "
                + manager.getAverageLevel());

        // =====================================
        // 5. MENCARI BERDASARKAN TIPE
        // =====================================
        System.out.println("\n=== KARAKTER TIPE MAGE ===");

        ArrayList<Character> daftarMage =
                manager.findByType(CharacterType.MAGE);

        for (Character character : daftarMage) {
            System.out.println(character);
        }

        // =====================================
        // 6. FITUR TAMBAHAN: KARAKTER TERKUAT
        // =====================================
        System.out.println("\n=== KARAKTER LEVEL TERTINGGI ===");

        Character terkuat = manager.findStrongest();

        if (terkuat != null) {
            System.out.println("Karakter terkuat: " + terkuat);
        }

        // =====================================
        // 7. UJI SALINAN DAFTAR
        // =====================================
        System.out.println("\n=== UJI ENCAPSULATION ===");

        ArrayList<Character> salinan = manager.getAllCharacters();

        salinan.clear();

        System.out.println("Jumlah karakter di salinan: "
                + salinan.size());

        System.out.println("Jumlah karakter asli: "
                + manager.getTotalCharacters());

        // =====================================
        // 8. MENGHAPUS KARAKTER
        // =====================================
        System.out.println("\n=== UJI HAPUS KARAKTER ===");

        System.out.println("Merlin berhasil dihapus: "
                + manager.removeCharacter("Merlin"));

        System.out.println("Total setelah penghapusan: "
                + manager.getTotalCharacters());

        System.out.println("\n=== PROGRAM SELESAI ===");
    }
}

