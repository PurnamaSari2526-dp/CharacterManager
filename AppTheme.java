package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public class AppTheme {

    // =========================
    // WARNA APLIKASI
    // =========================

    public static final Color BACKGROUND =
            new Color(245, 247, 250);

    public static final Color TEXT =
            new Color(40, 45, 55);

    public static final Color GOLD =
            new Color(218, 165, 32);

    public static final Color WHITE =
            Color.WHITE;

    public static final Color BORDER_COLOR =
            new Color(210, 215, 225);

    // =========================
    // FONT APLIKASI
    // =========================

    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 25);

    public static final Font NORMAL_FONT =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font BOLD_FONT =
            new Font("SansSerif", Font.BOLD, 14);

    // =========================
    // BORDER APLIKASI
    // =========================

    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            BORDER_COLOR, 1
                    ),
                    BorderFactory.createEmptyBorder(
                            12, 15, 12, 15
                    )
            );

    // =========================
    // WARNA BERDASARKAN ROLE
    // =========================

    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return WHITE;
        }

        switch (type) {

            case WARRIOR:
                return new Color(255, 220, 220);

            case MAGE:
                return new Color(220, 225, 255);

            case ARCHER:
                return new Color(220, 250, 225);

            case ASSASSIN:
                return new Color(235, 220, 250);

            default:
                return WHITE;
        }
    }
}