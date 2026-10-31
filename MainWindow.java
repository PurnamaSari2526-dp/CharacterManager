package com.unpri.charactermanager.view;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class MainWindow extends JFrame {

private final CharacterManager manager;
private final JPanel characterPanel;
private final JLabel averageLabel;

public MainWindow(CharacterManager manager) {
    this.manager = manager;

    setTitle("Character Manager - TP8");
    setSize(550, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10, 10));

    JLabel titleLabel = new JLabel(
            "DAFTAR KARAKTER",
            SwingConstants.CENTER
    );
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setBorder(
            BorderFactory.createEmptyBorder(15, 10, 10, 10)
    );
    add(titleLabel, BorderLayout.NORTH);

    characterPanel = new JPanel();
    characterPanel.setLayout(
            new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
    );

    JScrollPane scrollPane = new JScrollPane(characterPanel);
    add(scrollPane, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new BorderLayout());

    averageLabel = new JLabel("", SwingConstants.CENTER);
    averageLabel.setFont(new Font("Arial", Font.BOLD, 15));
    averageLabel.setBorder(
            BorderFactory.createEmptyBorder(10, 5, 10, 5)
    );

    JButton addButton = new JButton("Tambah Karakter");
    addButton.addActionListener(e -> showAddCharacterDialog());

    bottomPanel.add(averageLabel, BorderLayout.CENTER);
    bottomPanel.add(addButton, BorderLayout.SOUTH);

    add(bottomPanel, BorderLayout.SOUTH);

    refreshCharacters();

    setVisible(true);
}

private void showAddCharacterDialog() {
    JTextField nameField = new JTextField(15);

    String[] types = {"WARRIOR", "MAGE", "ARCHER"};
    JComboBox<String> typeBox = new JComboBox<>(types);

    JSpinner levelSpinner = new JSpinner(
            new SpinnerNumberModel(1, 1, 100, 1)
    );

    JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
    form.add(new JLabel("Nama:"));
    form.add(nameField);
    form.add(new JLabel("Tipe:"));
    form.add(typeBox);
    form.add(new JLabel("Level:"));
    form.add(levelSpinner);

    int result = JOptionPane.showConfirmDialog(
            this,
            form,
            "Tambah Karakter Baru",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    String name = nameField.getText().trim();

    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Nama karakter tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    CharacterType selectedType = CharacterType.valueOf(
            typeBox.getSelectedItem().toString()
    );

    Character character = new Character(name, selectedType);
    character.setLevel((Integer) levelSpinner.getValue());

    boolean added = manager.addCharacter(character);

    if (added) {
        refreshCharacters();

        JOptionPane.showMessageDialog(
                this,
                "Karakter berhasil ditambahkan!"
        );
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Karakter gagal ditambahkan. Nama mungkin sudah terdaftar.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
        );
    }
}

public void refreshCharacters() {
    characterPanel.removeAll();

    ArrayList<Character> characters = manager.getAllCharacters();

    for (Character character : characters) {
        JLabel characterLabel = new JLabel(
                character.getName()
                + " | Tipe: " + character.getType()
                + " | Level: " + character.getLevel()
        );

        characterLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        characterLabel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );

        characterPanel.add(characterLabel);
    }

    double average = manager.getAverageLevel();

    averageLabel.setText(
            String.format(
                    Locale.US,
                    "Rata-rata Level: %.2f",
                    average
            )
    );

    characterPanel.revalidate();
    characterPanel.repaint();
}

}
