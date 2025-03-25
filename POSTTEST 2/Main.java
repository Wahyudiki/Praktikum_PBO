import java.util.ArrayList;
import java.util.Scanner;

// Kelas utama AlatMusik
class AlatMusik {
    private String id;
    private String nama;
    private double harga;
    protected String kategori; // Menggunakan protected agar bisa diakses oleh subclass

    // Konstruktor
    public AlatMusik(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.setHarga(harga); // Menggunakan setter untuk validasi
        this.kategori = "Belum Dikategorikan"; // Default kategori
    }

    // Getter dan Setter untuk atribut private
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        } else {
            System.out.println("ID tidak boleh kosong!");
        }
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        if (nama != null && !nama.isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Nama tidak boleh kosong!");
        }
    }

    public double getHarga() {
        return this.harga;
    }

    public void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            System.out.println("Harga tidak boleh negatif atau nol!");
        }
    }

    public String getKategori() {
        return this.kategori;
    }

    protected void setKategori(String kategori) {
        if (kategori != null && !kategori.isEmpty()) {
            this.kategori = kategori;
        } else {
            System.out.println("Kategori tidak boleh kosong!");
        }
    }

    // Metode untuk menampilkan informasi alat musik
    public String getInfo() {
        return "Alat Musik: " + this.nama + " (Kategori: " + this.kategori + ")";
    }

    // Metode toString
    @Override
    public String toString() {
        return "ID: " + this.id + ", Nama: " + this.nama + ", Harga: Rp" + this.harga + ", Kategori: " + this.kategori;
    }
}

// Subclass Gitar dengan kategori yang ditentukan
class Gitar extends AlatMusik {
    public Gitar(String id, String nama, double harga) {
        super(id, nama, harga);
        this.setKategori("Alat Musik Petik");
    }
}

// Subclass Drum dengan kategori yang ditentukan
class Drum extends AlatMusik {
    public Drum(String id, String nama, double harga) {
        super(id, nama, harga);
        this.setKategori("Alat Musik Perkusi");
    }
}

// Kelas untuk menangani operasi CRUD
class AlatMusikManager {
    private ArrayList<AlatMusik> daftarAlatMusik = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Menambahkan alat musik (Create)
    public void tambahAlatMusik() {
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan ID Alat Musik: ");
        String id = scanner.nextLine();

        System.out.print("Masukkan Nama Alat Musik: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan Harga: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Input harus berupa angka!");
            scanner.next();
        }
        double harga = scanner.nextDouble();

        System.out.println("Pilih kategori: \n1. Gitar \n2. Drum \n3. Lainnya");
        int pilihan = scanner.nextInt();

        AlatMusik alatMusik;
        if (pilihan == 1) {
            alatMusik = new Gitar(id, nama, harga);
        } else if (pilihan == 2) {
            alatMusik = new Drum(id, nama, harga);
        } else {
            alatMusik = new AlatMusik(id, nama, harga);
        }

        daftarAlatMusik.add(alatMusik);
        System.out.println("✅ Alat Musik berhasil ditambahkan!");
    }

    // Menampilkan semua alat musik (Read)
    public void tampilkanAlatMusik() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("⚠️ Belum ada alat musik yang terdaftar.");
            return;
        }
        for (AlatMusik alat : daftarAlatMusik) {
            System.out.println(alat.toString());
        }
    }

    public void perbaruiAlatMusik() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("⚠️ Tidak ada alat musik untuk diperbarui.");
            return;
        }
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan ID alat musik yang ingin diperbarui: ");
        String id = scanner.nextLine();

        for (AlatMusik alat : daftarAlatMusik) {
            if (alat.getId().equals(id)) {
                System.out.print("Masukkan Nama Baru: ");
                String namaBaru = scanner.nextLine();

                System.out.print("Masukkan Harga Baru: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Input harus berupa angka!");
                    scanner.next();
                }
                double hargaBaru = scanner.nextDouble();

                alat.setNama(namaBaru);
                alat.setHarga(hargaBaru);

                System.out.println("✅ Alat Musik berhasil diperbarui!");
                return;
            }
        }
        System.out.println("⚠️ Alat Musik dengan ID " + id + " tidak ditemukan.");
    }

    // Menghapus alat musik dari daftar (Delete)
    public void hapusAlatMusik() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("⚠️ Tidak ada alat musik yang bisa dihapus.");
            return;
        }
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan ID alat musik yang ingin dihapus: ");
        String id = scanner.nextLine();

        for (int i = 0; i < daftarAlatMusik.size(); i++) {
            if (daftarAlatMusik.get(i).getId().equals(id)) {
                daftarAlatMusik.remove(i);
                System.out.println("✅ Alat Musik berhasil dihapus!");
                return;
            }
        }
        System.out.println("⚠️ Alat Musik dengan ID " + id + " tidak ditemukan.");
    }
}

// Kelas utama untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        AlatMusikManager manager = new AlatMusikManager();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== CRUD Alat Musik ===");
            System.out.println("1. Tambah Alat Musik");
            System.out.println("2. Tampilkan Alat Musik");
            System.out.println("3. Perbarui Alat Musik");
            System.out.println("4. Hapus Alat Musik");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            while (!scanner.hasNextInt()) {
                System.out.println("⚠️ Pilihan harus berupa angka!");
                scanner.next();
            }
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1 -> manager.tambahAlatMusik();
                case 2 -> manager.tampilkanAlatMusik();
                case 3 -> manager.perbaruiAlatMusik();
                case 4 -> manager.hapusAlatMusik();
                case 5 -> System.out.println("👋 Terima kasih!");
                default -> System.out.println("⚠️ Pilihan tidak valid, coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
