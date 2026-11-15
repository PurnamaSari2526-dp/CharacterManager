package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    // Menyimpan daftar karakter
    private ArrayList<Character> characters = new ArrayList<>();

    // Menghitung total karakter yang pernah dibuat melalui manager
    private static int totalCharacterCreated = 0;

    // 1. Menambahkan karakter
    public void addCharacter(Character character) {

        if (character == null) {
            System.out.println("Karakter tidak boleh kosong!");
            return;
        }

        if (characters.contains(character)) {
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

    // 2. Mencari karakter berdasarkan nama
    public Character findByName(String name) {

        if (name == null) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }

        return null;
    }

    // 3. Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {

        Character character = findByName(name);

        if (character != null) {
            characters.remove(character);

            System.out.println("Karakter berhasil dihapus: " + name);
            return true;
        }

        System.out.println("Karakter tidak ditemukan: " + name);
        return false;
    }

    // 4. Menghitung jumlah karakter saat ini
    public int getTotalCharacters() {
        return characters.size();
    }

    // 5. Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // 6. Menghitung rata-rata level
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

    // 7. Mencari karakter berdasarkan tipe
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

    /**
     * Mencari karakter dengan nilai attack tertinggi.
     *
     * @return karakter dengan attack tertinggi,
     * atau null jika daftar karakter kosong.
     */
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

    // 8. Mengambil jumlah karakter yang pernah ditambahkan
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }
}