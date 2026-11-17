package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat manager
            CharacterManager manager = new CharacterManager();

            // Tiga karakter awal sesuai panduan dosen
            manager.addCharacter(
                new Character("Arjuna", CharacterType.WARRIOR, 5, 0)
            );

            manager.addCharacter(
                new Character("Merlin", CharacterType.MAGE, 3, 0)
            );

            manager.addCharacter(
                new Character("Athena", CharacterType.ARCHER, 4, 0)
            );

            // Menampilkan aplikasi
            new MainWindow(manager);

        });
    }
}