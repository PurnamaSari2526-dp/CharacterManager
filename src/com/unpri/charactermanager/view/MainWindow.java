package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private JPanel characterPanel;

    private JLabel statusLabel;
    private JLabel versionLabel;

    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel warriorLabel;
    private JLabel mageLabel;

    // Komponen form tambah karakter
    private JTextField nameField;
    private JComboBox<CharacterType> typeCombo;
    private JSpinner levelSpinner;
    private JSpinner manaSpinner;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager v0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout utama
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(AppTheme.BG_MAIN);

        // Empat wilayah BorderLayout
        add(createHeader(), BorderLayout.NORTH);
        add(createCharacterList(), BorderLayout.CENTER);
        add(createLegend(), BorderLayout.EAST);
        add(createStatusBar(), BorderLayout.SOUTH);

        refreshCharacters();

        setMinimumSize(new Dimension(750, 500));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =====================================
    // HEADER, STATISTIK DAN FORM TAMBAH
    // =====================================

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout(5, 10));

        header.setBackground(AppTheme.BG_MAIN);
        header.setBorder(
            new EmptyBorder(15, 15, 5, 15)
        );

        JLabel title = new JLabel("CHARACTER MANAGER");

        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.GOLD);

        header.add(title, BorderLayout.NORTH);

        // Panel statistik GridLayout 2x2
        JPanel statistics = new JPanel(
            new GridLayout(2, 2, 10, 5)
        );

        statistics.setBackground(AppTheme.BG_MAIN);

        totalLabel = createStatLabel("Total Karakter: 0");
        averageLabel = createStatLabel("Rata-rata Level: 0");
        warriorLabel = createStatLabel("Warrior: 0");
        mageLabel = createStatLabel("Mage: 0");

        statistics.add(totalLabel);
        statistics.add(averageLabel);
        statistics.add(warriorLabel);
        statistics.add(mageLabel);

        header.add(statistics, BorderLayout.CENTER);

        // Form tambah karakter
        JPanel formPanel = new JPanel(
            new GridLayout(2, 5, 6, 5)
        );

        formPanel.setBackground(AppTheme.BG_CARD);

        formPanel.setBorder(
            new EmptyBorder(8, 8, 8, 8)
        );

        // Input nama
        JLabel nameText = createFormLabel("Nama:");
        nameField = new JTextField(10);

        // Pilihan role
        JLabel typeText = createFormLabel("Role:");
        typeCombo = new JComboBox<>(CharacterType.values());

        // Input level
        JLabel levelText = createFormLabel("Level:");
        levelSpinner = new JSpinner(
            new SpinnerNumberModel(1, 1, 100, 1)
        );

        // Input mana
        JLabel manaText = createFormLabel("Mana:");
        manaSpinner = new JSpinner(
            new SpinnerNumberModel(0, 0, 1000, 10)
        );

        // Tombol tambah
        JButton addButton = new JButton("Tambah Karakter");

        addButton.setBackground(AppTheme.GOLD);
        addButton.setForeground(AppTheme.BG_MAIN);
        addButton.setFocusPainted(false);

        // Baris pertama
        formPanel.add(nameText);
        formPanel.add(nameField);
        formPanel.add(typeText);
        formPanel.add(typeCombo);
        formPanel.add(addButton);

        // Baris kedua
        formPanel.add(levelText);
        formPanel.add(levelSpinner);
        formPanel.add(manaText);
        formPanel.add(manaSpinner);
        formPanel.add(new JLabel(""));

        // Aksi tombol
        addButton.addActionListener(e -> tambahKarakter());

        header.add(formPanel, BorderLayout.SOUTH);

        return header;
    }

    private JLabel createStatLabel(String text) {

        JLabel label = new JLabel(text);

        label.setForeground(AppTheme.TEXT_MAIN);
        label.setFont(AppTheme.FONT_BODY);

        return label;
    }

    private JLabel createFormLabel(String text) {

        JLabel label = new JLabel(text);

        label.setForeground(AppTheme.TEXT_MAIN);
        label.setFont(AppTheme.FONT_BODY);

        return label;
    }

    // =====================================
    // DAFTAR KARAKTER DAN SCROLLPANE
    // =====================================

    private JScrollPane createCharacterList() {

        characterPanel = new JPanel();

        characterPanel.setLayout(
            new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );

        characterPanel.setBackground(AppTheme.BG_MAIN);

        characterPanel.setBorder(
            new EmptyBorder(10, 10, 10, 10)
        );

        JPanel wrapper = new JPanel(new BorderLayout());

        wrapper.setBackground(AppTheme.BG_MAIN);

        wrapper.add(characterPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);

        scroll.setPreferredSize(
            new Dimension(480, 350)
        );

        scroll.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        return scroll;
    }

    // =====================================
    // LEGENDA ROLE OTOMATIS
    // =====================================

    private JPanel createLegend() {

        JPanel legend = new JPanel();

        legend.setLayout(
            new BoxLayout(legend, BoxLayout.Y_AXIS)
        );

        legend.setBackground(AppTheme.BG_CARD);

        legend.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    AppTheme.BORDER_COLOR
                ),
                new EmptyBorder(15, 15, 15, 15)
            )
        );

        JLabel title = new JLabel("LEGENDA ROLE");

        title.setFont(AppTheme.FONT_SUBTITLE);
        title.setForeground(AppTheme.GOLD);
        title.setAlignmentX(LEFT_ALIGNMENT);

        legend.add(title);

        legend.add(Box.createVerticalStrut(20));

        for (CharacterType type : CharacterType.values()) {

            JPanel item = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
            );

            item.setBackground(AppTheme.BG_CARD);
            item.setAlignmentX(LEFT_ALIGNMENT);

            JPanel colorBox = new JPanel();

            colorBox.setBackground(
                AppTheme.colorFor(type)
            );

            colorBox.setPreferredSize(
                new Dimension(18, 18)
            );

            colorBox.setOpaque(true);

            JLabel name = new JLabel(type.toString());

            name.setForeground(AppTheme.TEXT_MAIN);
            name.setFont(AppTheme.FONT_BODY);

            item.add(colorBox);
            item.add(name);

            legend.add(item);

            legend.add(Box.createVerticalStrut(10));
        }

        legend.add(Box.createVerticalGlue());

        return legend;
    }

    // =====================================
    // STATUS BAR
    // =====================================

    private JPanel createStatusBar() {

        JPanel statusBar = new JPanel(
            new BorderLayout()
        );

        statusBar.setBackground(AppTheme.BG_CARD);

        statusBar.setBorder(
            new EmptyBorder(10, 15, 10, 15)
        );

        statusLabel = new JLabel(
            "Aplikasi siap digunakan"
        );

        statusLabel.setForeground(AppTheme.TEXT_MAIN);
        statusLabel.setFont(AppTheme.FONT_BODY);

        versionLabel = new JLabel("v0.5");

        versionLabel.setForeground(AppTheme.GOLD);
        versionLabel.setFont(AppTheme.FONT_SUBTITLE);

        statusBar.add(statusLabel, BorderLayout.WEST);
        statusBar.add(versionLabel, BorderLayout.EAST);

        return statusBar;
    }

    // =====================================
    // FUNGSI TAMBAH KARAKTER
    // =====================================

    private void tambahKarakter() {

        String nama = nameField.getText().trim();

        CharacterType tipe =
            (CharacterType) typeCombo.getSelectedItem();

        int level = (Integer) levelSpinner.getValue();

        int mana = (Integer) manaSpinner.getValue();

        // Validasi nama kosong
        if (nama.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Nama karakter tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();
            return;
        }

        // Validasi nama duplikat
        if (manager.findByName(nama) != null) {

            JOptionPane.showMessageDialog(
                this,
                "Karakter dengan nama tersebut sudah ada!",
                "Karakter Duplikat",
                JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();
            return;
        }

        try {

            Character karakterBaru = new Character(
                nama,
                tipe,
                level,
                mana
            );

            manager.addCharacter(karakterBaru);

            // Kosongkan form setelah berhasil
            nameField.setText("");
            typeCombo.setSelectedIndex(0);
            levelSpinner.setValue(1);
            manaSpinner.setValue(0);

            // Perbarui daftar dan statistik
            refreshCharacters();

            JOptionPane.showMessageDialog(
                this,
                "Karakter berhasil ditambahkan!",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Kesalahan",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================
    // MEMPERBARUI KARTU DAN STATISTIK
    // =====================================

    private void refreshCharacters() {

        characterPanel.removeAll();

        int warrior = 0;
        int mage = 0;

        for (Character character : manager.getAllCharacters()) {

            JPanel card = new JPanel(
                new BorderLayout(10, 5)
            );

            card.setBackground(
                AppTheme.colorFor(character.getType())
            );

            card.setBorder(AppTheme.CARD_BORDER);
            card.setOpaque(true);

            card.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 85)
            );

            JLabel name = new JLabel(
                character.getName()
            );

            name.setForeground(AppTheme.TEXT_LIGHT);
            name.setFont(AppTheme.FONT_SUBTITLE);

            JLabel details = new JLabel(
                "Role: " + character.getType()
                + " | Level: " + character.getLevel()
                + " | HP: " + character.getHitPoints()
                + " | Mana: " + character.getMana()
            );

            details.setForeground(AppTheme.TEXT_MAIN);
            details.setFont(AppTheme.FONT_BODY);

            if (character.getLevel() >= 5) {

                details.setForeground(AppTheme.GOLD);
                details.setFont(AppTheme.FONT_LEVEL);
            }

            card.add(name, BorderLayout.NORTH);
            card.add(details, BorderLayout.CENTER);

            characterPanel.add(card);

            characterPanel.add(
                Box.createVerticalStrut(10)
            );

            if (character.getType() == CharacterType.WARRIOR) {
                warrior++;
            }

            if (character.getType() == CharacterType.MAGE) {
                mage++;
            }
        }

        // Memperbarui statistik
        int total = manager.getTotalCharacters();

        totalLabel.setText(
            "Total Karakter: " + total
        );

        averageLabel.setText(
            "Rata-rata Level: " + manager.getAverageLevel()
        );

        warriorLabel.setText(
            "Warrior: " + warrior
        );

        mageLabel.setText(
            "Mage: " + mage
        );

        statusLabel.setText(
            "Menampilkan " + total + " karakter"
        );

        characterPanel.revalidate();
        characterPanel.repaint();
    }
}