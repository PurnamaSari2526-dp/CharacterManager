package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {

    // ================= WARNA =================

    public static final Color BG_MAIN =
            Color.decode("#13131f");

    public static final Color BG_CARD =
            Color.decode("#24243a");

    public static final Color TEXT_MAIN =
            Color.decode("#dfe6e9");

    public static final Color TEXT_LIGHT =
            Color.WHITE;

    public static final Color GOLD =
            Color.decode("#fdcb6e");

    public static final Color BORDER_COLOR =
            Color.decode("#444466");


    // ================= FONT =================

    public static final Font FONT_TITLE =
            new Font("SansSerif", Font.BOLD, 22);

    public static final Font FONT_SUBTITLE =
            new Font("SansSerif", Font.BOLD, 16);

    public static final Font FONT_BODY =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font FONT_LEVEL =
            new Font("SansSerif", Font.BOLD, 14);


    // ================= BORDER =================

    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
            );


    // ================= WARNA BERDASARKAN ROLE =================

    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return BG_CARD;
        }

        switch (type) {
            case WARRIOR:
                return new Color(120, 50, 50);

            case MAGE:
                return new Color(55, 65, 130);

            case ARCHER:
                return new Color(45, 110, 75);

            case ASSASSIN:
                return new Color(100, 65, 110);

            default:
                return BG_CARD;
        }
    }
}