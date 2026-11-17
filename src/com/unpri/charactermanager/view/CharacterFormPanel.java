package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;

public class CharacterFormPanel extends JPanel {

    private CharacterManager manager;
    private Runnable onAdded;

    // Komponen formulir
    private JTextField nameField;
    private JComboBox<CharacterType> roleBox;
    private JSpinner levelSpinner;

    // Pesan validasi
    private JLabel messageLabel;

    // Menyimpan border asli
    private Border originalNameBorder;
    private Border originalLevelBorder;

    public CharacterFormPanel(
            CharacterManager manager,
            Runnable onAdded) {

        this.manager = manager;
        this.onAdded = onAdded;

        setLayout(new GridBagLayout());
        setBackground(AppTheme.BG_CARD);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.GOLD),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        createForm();
    }

    // Membuat tampilan formulir
    private void createForm() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(7, 5, 7, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Judul
        JLabel title = new JLabel("Tambah Karakter");
        title.setFont(AppTheme.FONT_SUBTITLE);
        title.setForeground(AppTheme.TEXT_LIGHT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        add(title, gbc);

        gbc.gridwidth = 1;

        // Nama karakter
        JLabel nameLabel = createLabel("Nama Karakter");

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        nameField = new JTextField(15);
        nameField.setFont(AppTheme.FONT_BODY);

        originalNameBorder = nameField.getBorder();

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(nameField, gbc);

        // Role karakter
        JLabel roleLabel = createLabel("Role Karakter");

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(roleLabel, gbc);

        roleBox = new JComboBox<>(CharacterType.values());
        roleBox.setFont(AppTheme.FONT_BODY);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(roleBox, gbc);

        // Level karakter
        JLabel levelLabel = createLabel("Level (1-99)");

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(levelLabel, gbc);

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        levelSpinner.setFont(AppTheme.FONT_BODY);

        originalLevelBorder = levelSpinner.getBorder();

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(levelSpinner, gbc);

        // Pesan validasi
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(AppTheme.TEXT_MAIN);
        messageLabel.setFont(AppTheme.FONT_BODY);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(messageLabel, gbc);

        // Tombol tambah
        JButton addButton = new JButton("Tambah Karakter");

        addButton.setFont(AppTheme.FONT_BODY);
        addButton.setBackground(AppTheme.GOLD);
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 8;

        add(addButton, gbc);

        // Aksi tombol
        addButton.addActionListener(e -> saveCharacter());
    }

    // Membuat label
    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_LIGHT);

        return label;
    }

    // Validasi nama
    // Mengembalikan pesan error atau null jika valid
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter wajib diisi!";
        }

        String cleanName = name.trim();

        // 2. Panjang nama
        if (cleanName.length() < 3 || cleanName.length() > 20) {
            return "Nama harus terdiri dari 3 sampai 20 karakter!";
        }

        // 3. Format nama
        if (!cleanName.matches("[A-Za-z ]+")) {
            return "Nama hanya boleh berisi huruf dan spasi!";
        }

        // Aturan tambahan: tidak boleh ada spasi berulang
        if (cleanName.contains("  ")) {
            return "Nama tidak boleh memiliki spasi berulang!";
        }

        // 4. Keunikan nama
        if (manager.isNameTaken(cleanName)) {
            return "Nama karakter sudah digunakan!";
        }

        return null;
    }

    // Menyimpan karakter
    private void saveCharacter() {

        clearMessage();

        String name = nameField.getText().trim();

        // Validasi nama
        String error = validateName(name);

        if (error != null) {
            showError(error, nameField);
            nameField.requestFocusInWindow();
            return;
        }

        // Membaca nilai spinner
        try {

            levelSpinner.commitEdit();

            int level = (Integer) levelSpinner.getValue();

            CharacterType type =
                    (CharacterType) roleBox.getSelectedItem();

            // Membuat karakter
            Character character = new Character(
                    name,
                    type,
                    level,
                    0
            );

            // Menambahkan ke manager
            manager.addCharacter(character);

            showSuccess("Karakter berhasil ditambahkan!");

            // Membersihkan formulir
            nameField.setText("");
            roleBox.setSelectedIndex(0);
            levelSpinner.setValue(1);

            // Memperbarui daftar karakter
            if (onAdded != null) {
                onAdded.run();
            }

            nameField.requestFocusInWindow();

        } catch (ParseException ex) {

            showError(
                    "Level harus berupa angka yang valid!",
                    levelSpinner
            );

            levelSpinner.requestFocusInWindow();

        } catch (IllegalArgumentException ex) {

            showError(
                    ex.getMessage(),
                    levelSpinner
            );

            levelSpinner.requestFocusInWindow();
        }
    }

    // Menampilkan pesan error
    private void showError(String message, JComponent component) {

        messageLabel.setText(message);
        messageLabel.setForeground(Color.RED);

        component.setBorder(
                BorderFactory.createLineBorder(Color.RED, 2)
        );
    }

    // Menampilkan pesan berhasil
    private void showSuccess(String message) {

        messageLabel.setText(message);
        messageLabel.setForeground(new Color(85, 239, 196));
    }

    // Menghapus pesan dan mengembalikan border
    private void clearMessage() {

        messageLabel.setText(" ");

        nameField.setBorder(originalNameBorder);
        levelSpinner.setBorder(originalLevelBorder);
    }
}