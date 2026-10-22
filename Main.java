package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

public static void main(String[] args) {

    CharacterManager manager = new CharacterManager();

    Character arthas = new Character(
            "Arthas", CharacterType.WARRIOR
    );
    arthas.setLevel(3);

    Character merlin = new Character(
            "Merlin", CharacterType.MAGE
    );
    merlin.setLevel(5);

    Character legolas = new Character(
            "Legolas", CharacterType.ARCHER
    );
    legolas.setLevel(2);

    // Dua karakter baru untuk TP8
    Character gandalf = new Character(
            "Gandalf", CharacterType.MAGE
    );
    gandalf.setLevel(8);

    Character thor = new Character(
            "Thor", CharacterType.WARRIOR
    );
    thor.setLevel(7);

    manager.addCharacter(arthas);
    manager.addCharacter(merlin);
    manager.addCharacter(legolas);
    manager.addCharacter(gandalf);
    manager.addCharacter(thor);

    SwingUtilities.invokeLater(() -> {
        new MainWindow(manager);
    });
}

}
