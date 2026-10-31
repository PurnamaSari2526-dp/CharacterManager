package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private final JPanel characterPanel;
    private final JLabel averageLabel;

    // Konstanta batas level tinggi
    private static final int HIGH_LEVEL_THRESHOLD = 5;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP10");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        // =========================
        // JUDUL
        // =========================

        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.TEXT);
        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 10, 15, 10
                )
        );

        add(titleLabel, BorderLayout.NORTH);

        // =========================
        // PANEL KARAKTER
        // =========================

        characterPanel = new JPanel();

        characterPanel.setLayout(
                new BoxLayout(
                        characterPanel,
                        BoxLayout.Y_AXIS
                )
        );

        characterPanel.setBackground(
                AppTheme.BACKGROUND
        );

        JScrollPane scrollPane =
                new JScrollPane(characterPanel);

        scrollPane.setPreferredSize(
                new Dimension(850, 400)
        );

        add(scrollPane, BorderLayout.CENTER);

        // =========================
        // PANEL BAWAH
        // =========================

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.setBackground(
                AppTheme.BACKGROUND
        );

        averageLabel = new JLabel(
                "Rata-rata Level: 0.00",
                SwingConstants.CENTER
        );

        averageLabel.setFont(AppTheme.BOLD_FONT);
        averageLabel.setForeground(AppTheme.TEXT);

        bottomPanel.add(
                averageLabel,
                BorderLayout.CENTER
        );

        // Tombol tambah karakter
        JButton addButton =
                new JButton("Tambah Karakter");

        addButton.setFont(AppTheme.BOLD_FONT);

        addButton.addActionListener(
                event -> showAddCharacterDialog()
        );

        JPanel buttonPanel = new JPanel();

        buttonPanel.setBackground(
                AppTheme.BACKGROUND
        );

        buttonPanel.add(addButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(bottomPanel, BorderLayout.SOUTH);

        // Menampilkan data karakter
        refreshData();

        // Mengatur ukuran otomatis
        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // =========================
    // FORM TAMBAH KARAKTER
    // =========================

    private void showAddCharacterDialog() {

        JTextField nameField = new JTextField(15);

        JComboBox<CharacterType> typeComboBox =
                new JComboBox<>(
                        CharacterType.values()
                );

        JSpinner levelSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1, 1, 100, 1
                        )
                );

        JSpinner manaSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                0, 0, 10000, 1
                        )
                );

        JPanel form = new JPanel(
                new GridLayout(4, 2, 8, 8)
        );

        form.add(new JLabel("Nama:"));
        form.add(nameField);

        form.add(new JLabel("Tipe:"));
        form.add(typeComboBox);

        form.add(new JLabel("Level:"));
        form.add(levelSpinner);

        form.add(new JLabel("Mana:"));
        form.add(manaSpinner);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Tambah Karakter",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nama tidak boleh kosong!"
            );

            return;
        }

        CharacterType type =
                (CharacterType) typeComboBox.getSelectedItem();

        int level =
                (Integer) levelSpinner.getValue();

        int mana =
                (Integer) manaSpinner.getValue();

        Character character;

        try {

            character = new Character(
                    name,
                    type,
                    level,
                    mana
            );

        } catch (IllegalArgumentException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage()
            );

            return;
        }

        if (manager.addCharacter(character)) {

            refreshData();

            JOptionPane.showMessageDialog(
                    this,
                    "Karakter berhasil ditambahkan!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter sudah digunakan!"
            );
        }
    }

    // =========================
    // REFRESH DATA
    // =========================

    public void refreshData() {

        characterPanel.removeAll();

        ArrayList<Character> characters =
                manager.getAllCharacters();

        for (Character character : characters) {

            JPanel card = new JPanel(
                    new BorderLayout()
            );

            // Warna berdasarkan tipe karakter
            card.setBackground(
                    AppTheme.colorFor(
                            character.getType()
                    )
            );

            // Wajib agar warna background terlihat
            card.setOpaque(true);

            card.setBorder(
                    AppTheme.CARD_BORDER
            );

            // Informasi karakter
            JLabel characterLabel = new JLabel(
                    character.getName()
                    + " | Type: " + character.getType()
                    + " | Level: " + character.getLevel()
                    + " | HP: " + character.getHitPoints()
                    + " | Attack: " + character.getAttack()
                    + " | Mana: " + character.getMana()
            );

            characterLabel.setFont(
                    AppTheme.NORMAL_FONT
            );

            characterLabel.setForeground(
                    AppTheme.TEXT
            );

            // Properti tambahan pertama:
            // Tooltip detail HP dan Attack
            characterLabel.setToolTipText(
                    "Nama: " + character.getName()
                    + " | HP: " + character.getHitPoints()
                    + " | Attack: " + character.getAttack()
                    + " | Mana: " + character.getMana()
            );

            // Properti tambahan kedua:
            // Karakter level 1 dinonaktifkan
            if (character.getLevel() == 1) {
                characterLabel.setEnabled(false);
            }

            // Mini-Challenge:
            // Level minimal 5 berwarna emas dan tebal
            if (character.getLevel()
                    >= HIGH_LEVEL_THRESHOLD) {

                characterLabel.setForeground(
                        AppTheme.GOLD
                );

                characterLabel.setFont(
                        AppTheme.BOLD_FONT
                );
            }

            card.add(
                    characterLabel,
                    BorderLayout.CENTER
            );

            card.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            65
                    )
            );

            card.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            characterPanel.add(card);

            characterPanel.add(
                    Box.createVerticalStrut(8)
            );
        }

        // Memperbarui statistik
        double average =
                manager.getAverageLevel();

        averageLabel.setText(
                String.format(
                        java.util.Locale.US,
                        "Rata-rata Level: %.2f",
                        average
                )
        );

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}