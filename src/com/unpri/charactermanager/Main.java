package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("     CHARACTER MANAGER TP5");
        System.out.println("==================================");

        // ==================================
        // BAGIAN 1: MEMBUAT KARAKTER
        // ==================================

        Character arjun = new Character(
            "Arjun", CharacterType.WARRIOR, 3, 20
        );

        Character merlin = new Character(
            "Merlin", CharacterType.MAGE, 5, 100
        );

        Character athena = new Character(
            "Athena", CharacterType.ARCHER, 4, 50
        );

        Character gandalf = new Character(
            "Gandalf", CharacterType.MAGE, 7, 150
        );

        // ==================================
        // BAGIAN 2: CHARACTER MANAGER
        // ==================================

        System.out.println("\n--- MENAMBAHKAN KARAKTER ---");

        CharacterManager manager = new CharacterManager();

        manager.addCharacter(arjun);
        manager.addCharacter(merlin);
        manager.addCharacter(athena);
        manager.addCharacter(gandalf);

        // Menguji karakter duplikat
        System.out.println("\n--- UJI KARAKTER DUPLIKAT ---");

        Character duplicate = new Character(
            "Arjun", CharacterType.WARRIOR
        );

        manager.addCharacter(duplicate);

        // ==================================
        // BAGIAN 3: MENAMPILKAN SEMUA KARAKTER
        // ==================================

        System.out.println("\n--- SEMUA DATA KARAKTER ---");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        System.out.println(
            "Total karakter: " + manager.getTotalCharacters()
        );

        // ==================================
        // BAGIAN 4: MENCARI KARAKTER
        // ==================================

        System.out.println("\n--- MENCARI KARAKTER ---");

        Character hasil = manager.findByName("Merlin");

        if (hasil != null) {
            System.out.println("Karakter ditemukan: " + hasil);
        } else {
            System.out.println("Karakter tidak ditemukan.");
        }

        // ==================================
        // BAGIAN 5: MENGHAPUS KARAKTER
        // ==================================

        System.out.println("\n--- MENGHAPUS KARAKTER ---");

        manager.removeCharacter("Athena");

        System.out.println(
            "Total karakter setelah penghapusan: "
            + manager.getTotalCharacters()
        );

        // ==================================
        // BAGIAN 6: ANALISIS KARAKTER
        // ==================================

        System.out.println("\n--- ANALISIS KARAKTER ---");

        System.out.println(
            "Rata-rata level: " + manager.getAverageLevel()
        );

        System.out.println("\nKarakter dengan tipe MAGE:");

        ArrayList<Character> mageList =
            manager.findByType(CharacterType.MAGE);

        for (Character character : mageList) {
            System.out.println(character);
        }

        System.out.println("\nKarakter terkuat:");

        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println(strongest);
        }

        // ==================================
        // BAGIAN 7: UJI SALINAN ARRAYLIST
        // ==================================

        System.out.println("\n--- UJI GET ALL CHARACTERS ---");

        ArrayList<Character> salinan = manager.getAllCharacters();

        System.out.println(
            "Jumlah data pada salinan sebelum dihapus: "
            + salinan.size()
        );

        salinan.clear();

        System.out.println(
            "Jumlah data pada salinan setelah dihapus: "
            + salinan.size()
        );

        System.out.println(
            "Jumlah data asli pada manager: "
            + manager.getTotalCharacters()
        );

        // ==================================
        // BAGIAN 8: UJI OBJECT REFERENCE
        // ==================================

        System.out.println("\n--- UJI OBJECT REFERENCE ---");

        Character a = new Character(
            "Bima", CharacterType.WARRIOR, 2, 20
        );

        Character b = a;

        b.setLevel(8);

        System.out.println("Level a: " + a.getLevel());
        System.out.println("Level b: " + b.getLevel());

        System.out.println("Apakah a == b? " + (a == b));

        // Membuat objek baru yang independen
        Character c = new Character(
            a.getName(),
            a.getType(),
            a.getLevel(),
            a.getMana()
        );

        c.setLevel(10);

        System.out.println("Level a setelah c berubah: " + a.getLevel());
        System.out.println("Level c: " + c.getLevel());

        // ==================================
        // BAGIAN 9: TOTAL KARAKTER DIBUAT
        // ==================================

        System.out.println("\n--- TOTAL KARAKTER DITAMBAHKAN ---");

        System.out.println(
            "Total karakter berhasil ditambahkan: "
            + CharacterManager.getTotalCharacterCreated()
        );

        System.out.println("\n==================================");
        System.out.println("       PROGRAM SELESAI");
        System.out.println("==================================");
    }
}