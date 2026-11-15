package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat pengelola karakter
            CharacterManager manager = new CharacterManager();

            // Menambahkan 6 karakter dengan role dan level berbeda
            manager.addCharacter(
                    new Character("Arjun", CharacterType.WARRIOR, 8, 50)
            );

            manager.addCharacter(
                    new Character("Merlin", CharacterType.MAGE, 10, 150)
            );

            manager.addCharacter(
                    new Character("Athena", CharacterType.ARCHER, 5, 80)
            );

            manager.addCharacter(
                    new Character("Gandalf", CharacterType.MAGE, 3, 120)
            );

            manager.addCharacter(
                    new Character("Bima", CharacterType.WARRIOR, 1, 30)
            );

            manager.addCharacter(
                    new Character("Luna", CharacterType.HEALER, 7, 100)
            );

            // Menampilkan jendela aplikasi
            new MainWindow(manager);
        });
    }
}