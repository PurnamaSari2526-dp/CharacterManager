package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    // Batas level untuk highlight
    private static final int LEVEL_HIGHLIGHT = 5;

    private final CharacterManager manager;

    private JPanel listPanel;
    private JLabel statisticLabel;

    private JTextField nameField;
    private JTextField levelField;
    private JTextField manaField;

    private JComboBox<CharacterType> typeComboBox;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(AppTheme.BG_MAIN);
        setLayout(new BorderLayout(15, 15));

        // ================= JUDUL =================

        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.GOLD);
        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );

        // ================= FORM TAMBAH KARAKTER =================

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(AppTheme.BG_CARD);
        formPanel.setBorder(AppTheme.CARD_BORDER);

        nameField = new JTextField();
        levelField = new JTextField();
        manaField = new JTextField();

        typeComboBox = new JComboBox<>(CharacterType.values());

        JButton addButton = new JButton("Tambah Karakter");
        addButton.setFont(AppTheme.FONT_BODY);
        addButton.setToolTipText("Klik untuk menambahkan karakter baru");

        formPanel.add(createLabel("Nama:"));
        formPanel.add(nameField);

        formPanel.add(createLabel("Tipe:"));
        formPanel.add(typeComboBox);

        formPanel.add(createLabel("Level:"));
        formPanel.add(levelField);

        formPanel.add(createLabel("Mana:"));
        formPanel.add(manaField);

        formPanel.add(new JLabel(""));
        formPanel.add(addButton);

        // ================= PANEL ATAS =================

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(AppTheme.BG_MAIN);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ================= DAFTAR KARAKTER =================

        listPanel = new JPanel();
        listPanel.setLayout(
                new BoxLayout(listPanel, BoxLayout.Y_AXIS)
        );

        listPanel.setBackground(AppTheme.BG_MAIN);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(AppTheme.CARD_BORDER);
        scrollPane.getViewport().setBackground(AppTheme.BG_MAIN);

        add(scrollPane, BorderLayout.CENTER);

        // ================= STATISTIK =================

        statisticLabel = new JLabel("", SwingConstants.CENTER);
        statisticLabel.setFont(AppTheme.FONT_SUBTITLE);
        statisticLabel.setForeground(AppTheme.TEXT_LIGHT);
        statisticLabel.setBorder(
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        );

        add(statisticLabel, BorderLayout.SOUTH);

        // ================= EVENT BUTTON =================

        addButton.addActionListener(e -> tambahKarakter());

        refreshCharacterList();

        setMinimumSize(new Dimension(650, 450));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ================= LABEL =================

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_MAIN);

        return label;
    }

    // ================= TAMBAH KARAKTER =================

    private void tambahKarakter() {

        try {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nama karakter tidak boleh kosong!"
                );
                return;
            }

            int level = Integer.parseInt(levelField.getText().trim());
            int mana = Integer.parseInt(manaField.getText().trim());

            CharacterType type =
                    (CharacterType) typeComboBox.getSelectedItem();

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

            Character character =
                    new Character(name, type, level, mana);

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

    // ================= REFRESH DAFTAR =================

    private void refreshCharacterList() {

        listPanel.removeAll();

        ArrayList<Character> characters =
                manager.getAllCharacters();

        for (Character character : characters) {

            JPanel card = new JPanel(new BorderLayout(10, 5));

            // Warna kartu berdasarkan role
            card.setBackground(
                    AppTheme.colorFor(character.getType())
            );

            // Wajib agar warna kartu terlihat
            card.setOpaque(true);

            // Border dari AppTheme
            card.setBorder(AppTheme.CARD_BORDER);

            JLabel nameLabel = new JLabel(
                    character.getName() + " | " + character.getType()
            );

            nameLabel.setFont(AppTheme.FONT_SUBTITLE);
            nameLabel.setForeground(AppTheme.TEXT_LIGHT);

            JLabel detailLabel = new JLabel(
                    "Level: " + character.getLevel()
                    + " | HP: " + character.getHitPoints()
                    + " | Attack: " + character.getAttack()
                    + " | Mana: " + character.getMana()
            );

            detailLabel.setFont(AppTheme.FONT_BODY);
            detailLabel.setForeground(AppTheme.TEXT_MAIN);

            // MINI CHALLENGE:
            // Level minimal 5 tampil emas dan tebal
            if (character.getLevel() >= LEVEL_HIGHLIGHT) {

                detailLabel.setForeground(AppTheme.GOLD);
                detailLabel.setFont(AppTheme.FONT_LEVEL);
            }

            // Properti tambahan 1:
            // Tooltip berisi informasi lengkap karakter
            card.setToolTipText(
                    "Nama: " + character.getName()
                    + " | Role: " + character.getType()
                    + " | Level: " + character.getLevel()
                    + " | HP: " + character.getHitPoints()
                    + " | Attack: " + character.getAttack()
                    + " | Mana: " + character.getMana()
            );

            // Properti tambahan 2:
            // Karakter level 1 dibuat tidak aktif
            card.setEnabled(character.getLevel() != 1);

            card.add(nameLabel, BorderLayout.NORTH);
            card.add(detailLabel, BorderLayout.CENTER);

            card.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 85)
            );

            listPanel.add(card);
            listPanel.add(Box.createVerticalStrut(8));
        }

        statisticLabel.setText(
                "Total Karakter: " + manager.getTotalCharacters()
                + " | Rata-rata Level: "
                + String.format("%.2f", manager.getAverageLevel())
        );

        listPanel.revalidate();
        listPanel.repaint();
    }
}