package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager =
                new CharacterManager();

        // Karakter 1
        Character bima = new Character(
                "Bima",
                CharacterType.WARRIOR,
                10,
                20
        );

        // Karakter 2
        Character maya = new Character(
                "Maya",
                CharacterType.MAGE,
                3,
                50
        );

        // Karakter 3
        Character raka = new Character(
                "Raka",
                CharacterType.ARCHER,
                7,
                15
        );

        // Karakter 4
        Character sinta = new Character(
                "Sinta",
                CharacterType.MAGE,
                4,
                40
        );

        // Karakter 5
        Character damar = new Character(
                "Damar",
                CharacterType.WARRIOR,
                6,
                25
        );

        // Karakter 6
        Character kirana = new Character(
                "Kirana",
                CharacterType.ASSASSIN,
                8,
                60
        );

        // Menambahkan karakter
        manager.addCharacter(bima);
        manager.addCharacter(maya);
        manager.addCharacter(raka);
        manager.addCharacter(sinta);
        manager.addCharacter(damar);
        manager.addCharacter(kirana);

        // Menjalankan aplikasi GUI
        SwingUtilities.invokeLater(() -> {

            new MainWindow(manager);

        });
    }
}