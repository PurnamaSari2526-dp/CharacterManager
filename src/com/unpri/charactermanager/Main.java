package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("     CHARACTER MANAGER TP3");
        System.out.println("==================================");

        // Membuat tiga karakter
        Character arjun = new Character(
            "Arjun", CharacterType.WARRIOR
        );

        Character merlin = new Character(
            "Merlin", CharacterType.MAGE
        );

        Character athena = new Character(
            "Athena", CharacterType.ARCHER
        );

        // Menampilkan tiga karakter
        System.out.println("\n--- DATA KARAKTER ---");

        System.out.println(arjun);
        System.out.println(merlin);
        System.out.println(athena);

        // Demo level up
        System.out.println("\n--- DEMO LEVEL UP ---");

        arjun.setLevel(3);

        System.out.println("Arjun naik level!");
        System.out.println(arjun);

        // Mini-Challenge
        System.out.println("\n--- MINI CHALLENGE ---");

        Character gandalf = new Character(
            "Gandalf", CharacterType.MAGE, 5, 100
        );

        gandalf.setMana(150);

        System.out.println(gandalf);

        System.out.println(
            "Apakah Gandalf masih hidup? "
            + gandalf.isAlive()
        );

        System.out.println(
            "Mana Gandalf: " + gandalf.getMana()
        );

        System.out.println("\n==================================");
        System.out.println("       PROGRAM SELESAI");
        System.out.println("==================================");
    }
}
