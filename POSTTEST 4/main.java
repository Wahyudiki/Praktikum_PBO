import java.util.ArrayList;
import java.util.Scanner;

// Kelas utama AlatMusik
class AlatMusik {
    private String id;
    private String nama;
    private double harga;
    protected String kategori;

    public AlatMusik(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.setHarga(harga);
        this.kategori = "Belum Dikategorikan";
    }

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

    public final void setHarga(double harga) {
        if (harga > 0) {
            this.harga = harga;
        } else {
            System.out.println("Harga tidak boleh negatif atau nol!");
        }
    }

    public String getKategori() {
        return this.kategori;
    }

    protected static void setKategori(AlatMusik alatMusik, String kategori) {
        if (kategori != null && !kategori.isEmpty()) {
            alatMusik.kategori = kategori;
        } else {
            System.out.println("Kategori tidak boleh kosong!");
        }
    }

    public String getInfo() {
        return "Alat Musik: " + this.nama + " (Kategori: " + this.kategori + ")";
    }

    public void tampilDetail() {
        System.out.println("Detail Alat Musik: " + toString());
    }

    public void tampilDetail(boolean tampilLengkap) {
        if (tampilLengkap) {
            System.out.println(getInfo());
        } else {
            System.out.println("Nama: " + getNama());
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Nama: " + this.nama + ", Harga: Rp" + this.harga + ", Kategori: " + this.kategori;
    }
}

class Gitar extends AlatMusik {
    public Gitar(String id, String nama, double harga) {
        super(id, nama, harga);
        setKategori(extracted(), "Alat Musik Petik");
    }

    private Gitar extracted() {
        return this;
    }

    @Override
    public String getInfo() {
        return "🎸 Gitar: " + getNama() + " - Harga: Rp" + getHarga();
    }
}

class Drum extends AlatMusik {
    public Drum(String id, String nama, double harga) {
        super(id, nama, harga);
        setKategori(this, "Alat Musik Perkusi");
    }

    @Override
    public String getInfo() {
        return "🥁 Drum: " + getNama() + " - Harga: Rp" + getHarga();
    }
}

class AlatMusikManager {
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<AlatMusik> daftarAlatMusik = new ArrayList<>();
    private Scanner scanner;

    public AlatMusikManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void tambahAlatMusik() {
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
        scanner.nextLine(); // menyerap newline

        System.out.println("Pilih kategori: \n1. Gitar \n2. Drum \n3. Lainnya");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // menyerap newline

        AlatMusik alatMusik;
        alatMusik = switch (pilihan) {
            case 1 -> new Gitar(id, nama, harga);
            case 2 -> new Drum(id, nama, harga);
            default -> new AlatMusik(id, nama, harga);
        };

        daftarAlatMusik.add(alatMusik);
        System.out.println("✅ Alat Musik berhasil ditambahkan!");
    }

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
                scanner.nextLine(); // menyerap newline

                alat.setNama(namaBaru);
                alat.setHarga(hargaBaru);

                System.out.println("✅ Alat Musik berhasil diperbarui!");
                return;
            }
        }
        System.out.println("⚠️ Alat Musik dengan ID " + id + " tidak ditemukan.");
    }

    public void hapusAlatMusik() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("⚠️ Tidak ada alat musik yang bisa dihapus.");
            return;
        }

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

    public void tampilkanPolymorphism() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("⚠️ Tidak ada data untuk ditampilkan.");
            return;
        }

        System.out.println("\n📦 Menampilkan info dengan Polymorphism:");
        for (AlatMusik alat : daftarAlatMusik) {
            alat.tampilDetail(); // Overloading
            alat.tampilDetail(true); // Overloading
            System.out.println(alat.getInfo()); // Overriding
            System.out.println("------------");
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

public class main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            AlatMusikManager manager = new AlatMusikManager(scanner);
            int pilihan;
            
            do {
                System.out.println("\n=== CRUD Alat Musik ===");
                System.out.println("1. Tambah Alat Musik");
                System.out.println("2. Tampilkan Alat Musik");
                System.out.println("3. Perbarui Alat Musik");
                System.out.println("4. Hapus Alat Musik");
                System.out.println("5. Keluar");
                System.out.println("6. Tampilkan Polymorphism");
                System.out.print("Pilih menu: ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("⚠️ Pilihan harus berupa angka!");
                    scanner.next();
                }
                pilihan = scanner.nextInt();
                scanner.nextLine(); // menyerap newline
                
                switch (pilihan) {
                    case 1 -> manager.tambahAlatMusik();
                    case 2 -> manager.tampilkanAlatMusik();
                    case 3 -> manager.perbaruiAlatMusik();
                    case 4 -> manager.hapusAlatMusik();
                    case 5 -> System.out.println("👋 Terima kasih!");
                    case 6 -> manager.tampilkanPolymorphism();
                    default -> System.out.println("⚠️ Pilihan tidak valid, coba lagi.");
                }
            } while (pilihan != 5);
        }
    }
}
