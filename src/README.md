# Character Manager — TP3 (Java Visual SIH-6406)

## 1. Deskripsi Project

Character Manager merupakan aplikasi sederhana berbasis Java yang dikembangkan untuk memenuhi tugas TP3 (AP3 Belajar Praktik) mata kuliah Java Visual SIH-6406.

Project ini menerapkan konsep dasar Object-Oriented Programming (OOP), meliputi Class, Object, Attribute, Constructor, Method, dan Encapsulation. Program digunakan untuk membuat, mengatur, serta menampilkan informasi karakter berdasarkan tipe dan atribut yang dimiliki.

## 2. Fitur Program

Fitur yang telah dikembangkan dalam TP3:

- Membuat class `Character` sebagai representasi objek karakter.
- Menggunakan enum `CharacterType` untuk menentukan jenis karakter.
- Membuat objek menggunakan dua constructor.
- Mengatur atribut karakter seperti nama, level, hit points, attack, dan mana.
- Menggunakan getter dan setter untuk mengakses atribut.
- Menerapkan validasi pada atribut mana.
- Menggunakan method `toString()` untuk menampilkan informasi karakter.
- Menggunakan method `isAlive()` untuk mengetahui apakah karakter masih hidup berdasarkan hit points.
- Menampilkan tiga karakter dan demonstrasi level up melalui terminal.

## 3. Screenshot Hasil Program

Berikut merupakan dokumentasi hasil pengujian program Character Manager TP3 melalui terminal Visual Studio Code.

### Output Terminal

![Screenshot Output TP3](Screenshot/output_tp3.png)

Screenshot tersebut memperlihatkan hasil program ketika dijalankan, termasuk informasi karakter, demonstrasi level up, dan hasil pengujian method `isAlive()`.

## 4. Struktur Folder Project

Berikut adalah struktur folder project Character Manager:

```text
CharacterManager/
│
├── src/
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               ├── Main.java
│               │
│               └── model/
│                   ├── Character.java
│                   └── CharacterType.java
│
├── Screenshot/
│   └── output_tp3.png
│
└── README.md
```

## 5. Teknologi yang Digunakan

| Teknologi | Keterangan |
|---|---|
| Java JDK 17 | Bahasa pemrograman dan development kit |
| Visual Studio Code | Code editor |
| Git | Version control |
| GitHub | Penyimpanan repository |

## 6. Identitas Mahasiswa

| Keterangan | Informasi |
|---|---|
| Nama | Purnama Sari |
| NIM | 253303621208 |
| Mata Kuliah | Java Visual SIH-6406 |
| Tugas | TP3 – Character Manager Sprint #1 |

## 7. Link Dokumentasi Google Docs

Dokumentasi analisis desain dan ekosistem Java untuk memenuhi Bagian D tugas TP3 dapat diakses melalui tautan berikut:

**Google Docs:** [https://1drv.ms/w/c/EF681E4F4E3CE288/IQAz1p2p37D5Q5d16b9GdislAadJElEDL_bpfsny-L73cIs?e=xOdoln]

## 8. Kesimpulan

Melalui tugas TP3 Character Manager, saya mempelajari penerapan konsep dasar Object-Oriented Programming menggunakan bahasa Java. Saya memahami cara membuat class, object, constructor, attribute, dan method untuk membangun program pengelolaan karakter sederhana.

Selain itu, saya juga mempelajari penerapan encapsulation, penggunaan enum, validasi data, serta pengembangan method tambahan seperti `isAlive()`.

Project ini menjadi dasar pengembangan Character Manager pada sprint berikutnya.


--------------------------------------
# TP5 – Character Manager Sprint #2

## CharacterManager, Analytics & Object Reference

### Deskripsi
Pada TP5, proyek Character Manager dikembangkan dengan menambahkan kelas CharacterManager untuk mengelola data karakter. Program dilengkapi fitur penambahan, pencarian, penghapusan, dan perhitungan jumlah karakter.

Selain itu, program menerapkan konsep Object Reference, penggunaan ArrayList, serta analisis data karakter.

### Fitur yang Dikembangkan
1. `addCharacter()` – Menambahkan karakter dan menolak nama duplikat.
2. `findByName()` – Mencari karakter berdasarkan nama.
3. `removeCharacter()` – Menghapus karakter.
4. `getTotalCharacters()` – Menghitung jumlah karakter.
5. `getAllCharacters()` – Mengembalikan salinan daftar karakter.
6. `getAverageLevel()` – Menghitung rata-rata level karakter.
7. `findByType()` – Mencari karakter berdasarkan jenis.
8. `findStrongest()` – Mencari karakter dengan attack tertinggi.
9. `equals()` – Membandingkan karakter berdasarkan nama.

### Pengujian Program
Program diuji melalui beberapa percobaan:
- Penambahan karakter.
- Penolakan karakter duplikat.
- Pencarian dan penghapusan karakter.
- Perhitungan rata-rata level.
- Pencarian berdasarkan tipe karakter.
- Pencarian karakter terkuat.
- Pengujian salinan ArrayList.
- Pengujian Object Reference.

### Screenshot Hasil Program

**1. Pengujian CRUD dan Duplikat**

![Screenshot CRUD](Screenshot/output_tp5_crud.png)

**2. Pengujian Analisis Karakter**

![Screenshot Analisis](Screenshot/output_tp5_analisis.png)

### Identitas
- Nama: Purnama Sari
- NIM: 253303621208
- Mata Kuliah: Java Visual
- Tugas: TP5 / AP6
- Sprint: 2

### Link Google Docs

Dokumentasi analisis TP5 yang berisi pembahasan Object Reference, Primitive vs Reference, Static Variable, penggunaan equals(), dan Encapsulation.

Google Docs: [https://1drv.ms/w/c/EF681E4F4E3CE288/IQA4FnerNc4XQYRnqw-o7hjPAQjro83O_OJ4Mw9Ll9GR_IA?e=xlnIAI]

### Analisis Metode Tambahan
Metode `findStrongest()` digunakan untuk mencari karakter dengan nilai attack tertinggi. Metode ini membantu mengetahui karakter yang memiliki kekuatan serangan paling besar tanpa memberikan akses langsung ke ArrayList internal milik CharacterManager.

### Kesimpulan
Melalui TP5, proyek Character Manager memiliki kemampuan pengelolaan data yang lebih lengkap. Saya juga memahami konsep encapsulation, object reference, static, equals(), dan penggunaan ArrayList dalam Java.

