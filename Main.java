package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        // Membuat tiga karakter
        Character karakter1 = new Character(
                "Arthas", CharacterType.WARRIOR);

        Character karakter2 = new Character(
                "Merlin", CharacterType.MAGE);

        Character karakter3 = new Character(
                "Legolas", CharacterType.ARCHER);

        // Menampilkan karakter
        System.out.println("=== DAFTAR KARAKTER ===");
        System.out.println(karakter1);
        System.out.println(karakter2);
        System.out.println(karakter3);

        // Demo level up
        System.out.println("\n=== DEMO LEVEL UP ===");

        karakter1.setLevel(3);
        System.out.println(karakter1);

        // Demo mana dan status hidup
        System.out.println("\n=== DEMO MANA DAN STATUS HIDUP ===");

        Character karakter4 = new Character(
                "Gandalf", CharacterType.MAGE);

        karakter4.setMana(150);

        System.out.println(karakter4);
        System.out.println("Mana Gandalf: " + karakter4.getMana());
        System.out.println("Apakah Gandalf masih hidup? "
                + karakter4.isAlive());

        // Demo validasi mana
        System.out.println("\n=== DEMO VALIDASI MANA ===");

        try {
            karakter4.setMana(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi berhasil: " + e.getMessage());
        }
    }
}
