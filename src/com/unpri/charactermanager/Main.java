package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CharacterManager manager = new CharacterManager();

            manager.addCharacter(
                new Character("Arjun", CharacterType.WARRIOR, 3, 20)
            );

            manager.addCharacter(
                new Character("Merlin", CharacterType.MAGE, 5, 100)
            );

            manager.addCharacter(
                new Character("Athena", CharacterType.ARCHER, 4, 50)
            );

            manager.addCharacter(
                new Character("Gandalf", CharacterType.MAGE, 7, 150)
            );

            manager.addCharacter(
                new Character("Bima", CharacterType.WARRIOR, 8, 40)
            );

            new MainWindow(manager);

        });
    }
}