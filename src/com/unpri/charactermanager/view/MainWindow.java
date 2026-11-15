package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private CharacterManager manager;

    private JPanel listPanel;
    private JLabel statisticLabel;

    private JTextField nameField;
    private JTextField levelField;
    private JTextField manaField;
    private JComboBox<CharacterType> typeComboBox;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(15, 15));

        // JUDUL
        JLabel titleLabel = new JLabel(
            "CHARACTER MANAGER",
            SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel, BorderLayout.NORTH);

        // FORM TAMBAH KARAKTER
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.setBorder(
            BorderFactory.createTitledBorder("Tambah Karakter")
        );

        nameField = new JTextField();
        levelField = new JTextField();
        manaField = new JTextField();

        typeComboBox = new JComboBox<>(CharacterType.values());

        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Tipe:"));
        formPanel.add(typeComboBox);

        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelField);

        formPanel.add(new JLabel("Mana:"));
        formPanel.add(manaField);

        JButton addButton = new JButton("Tambah Karakter");

        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        // DAFTAR KARAKTER
        listPanel = new JPanel();

        listPanel.setLayout(
            new BoxLayout(listPanel, BoxLayout.Y_AXIS)
        );

        JScrollPane scrollPane = new JScrollPane(listPanel);

        scrollPane.setBorder(
            BorderFactory.createTitledBorder("Daftar Karakter")
        );

        add(scrollPane, BorderLayout.CENTER);

        // STATISTIK
        statisticLabel = new JLabel();

        statisticLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );

        statisticLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        add(statisticLabel, BorderLayout.SOUTH);

        // EVENT TOMBOL
        addButton.addActionListener(e -> tambahKarakter());

        refreshCharacterList();

        setSize(750, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void tambahKarakter() {

        try {

            String name = nameField.getText().trim();

            CharacterType type =
                (CharacterType) typeComboBox.getSelectedItem();

            int level = Integer.parseInt(levelField.getText().trim());

            int mana = Integer.parseInt(manaField.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter tidak boleh kosong!"
                );
                return;
            }

            if (level < 1 || mana < 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Level minimal 1 dan mana tidak boleh negatif!"
                );
                return;
            }

            if (manager.findByName(name) != null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter sudah digunakan!"
                );
                return;
            }

            Character character = new Character(
                name,
                type,
                level,
                mana
            );

            manager.addCharacter(character);

            nameField.setText("");
            levelField.setText("");
            manaField.setText("");

            refreshCharacterList();

            JOptionPane.showMessageDialog(
                this,
                "Karakter berhasil ditambahkan!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Level dan Mana harus berupa angka!"
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage()
            );
        }
    }

    private void refreshCharacterList() {

        listPanel.removeAll();

        ArrayList<Character> characters =
            manager.getAllCharacters();

        for (Character character : characters) {

            JLabel label = new JLabel(
                character.getName()
                + " | " + character.getType()
                + " | Level: " + character.getLevel()
                + " | HP: " + character.getHitPoints()
                + " | Attack: " + character.getAttack()
                + " | Mana: " + character.getMana()
            );

            label.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            );

            label.setFont(
                new Font("Arial", Font.PLAIN, 14)
            );

            listPanel.add(label);
        }

        double average = manager.getAverageLevel();

        statisticLabel.setText(
            String.format(
                "Total Karakter: %d | Rata-rata Level: %.2f",
                manager.getTotalCharacters(),
                average
            )
        );

        listPanel.revalidate();
        listPanel.repaint();
    }
}