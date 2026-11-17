package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    // Menyimpan daftar karakter
    private ArrayList<Character> characters = new ArrayList<>();

    // Menghitung total karakter yang pernah dibuat
    private static int totalCharacterCreated = 0;

    // 1. Menambahkan karakter
    public void addCharacter(Character character) {

        if (character == null) {
            System.out.println("Karakter tidak boleh kosong!");
            return;
        }

        if (isNameTaken(character.getName())) {
            System.out.println(
                "Karakter duplikat ditolak: " + character.getName()
            );
            return;
        }

        characters.add(character);
        totalCharacterCreated++;

        System.out.println(
            "Karakter berhasil ditambahkan: " + character.getName()
        );
    }

    // 2. Memeriksa apakah nama karakter sudah digunakan
    public boolean isNameTaken(String name) {

        if (name == null || name.isBlank()) {
            return false;
        }

        for (Character character : characters) {

            if (character.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }

        return false;
    }

    // 3. Mencari karakter berdasarkan nama
    public Character findByName(String name) {

        if (name == null) {
            return null;
        }

        for (Character character : characters) {

            if (character.getName().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }

        return null;
    }

    // 4. Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {

        Character character = findByName(name);

        if (character != null) {
            characters.remove(character);

            System.out.println(
                "Karakter berhasil dihapus: " + name
            );

            return true;
        }

        System.out.println(
            "Karakter tidak ditemukan: " + name
        );

        return false;
    }

    // 5. Menghitung jumlah karakter saat ini
    public int getTotalCharacters() {
        return characters.size();
    }

    // 6. Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // 7. Menghitung rata-rata level
    public double getAverageLevel() {

        if (characters.isEmpty()) {
            return 0.0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }

    // 8. Mencari karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {

        ArrayList<Character> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Character character : characters) {

            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    // 9. Mencari karakter dengan attack tertinggi
    public Character findStrongest() {

        if (characters.isEmpty()) {
            return null;
        }

        Character strongest = characters.get(0);

        for (Character character : characters) {

            if (character.getAttack() > strongest.getAttack()) {
                strongest = character;
            }
        }

        return strongest;
    }

    // 10. Mengambil jumlah karakter yang pernah ditambahkan
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }
}