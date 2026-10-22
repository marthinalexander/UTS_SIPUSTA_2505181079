import eksepsi.PustakaException;
import layanan.EksporKatalog;
import layanan.KatalogPerpustakaan;
import layanan.UrutLayakLaluJudul;
import model.BerkasDigital;
import model.BukuCetak;
import model.Koleksi;
import model.Majalah;
import model.Terarsip;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BukuCetak bk1 = new BukuCetak("BK-001", "Java How to Program", 2022, 620, "A-12", 1, false);
        BukuCetak bk2 = new BukuCetak("BK-002", "Basis Data Relasional", 2021, 410, "B-04", 2, true);
        Majalah mj1 = new Majalah("MJ-001", "Info Teknologi Edisi 88", 2026, 88, "Agustus", true, "M-01", 1);
        Majalah mj2 = new Majalah("MJ-002", "Info Teknologi Edisi 87", 2026, 87, "Juli", false, "M-02", 1);
        BerkasDigital bd1 = new BerkasDigital("BD-001", "Panduan Pemrograman Java", 2024, 15, "unduh.polmed.ac.id/bd001");
        BerkasDigital bd2 = new BerkasDigital("BD-002", "Jurnal Rekayasa Perangkat", 2025, 8, "unduh.polmed.ac.id/bd002");

        KatalogPerpustakaan katalog = new KatalogPerpustakaan();
        for (Koleksi k : new Koleksi[]{bk1, bk2, mj1, mj2, bd1, bd2}) {
            katalog.tambah(k);
        }

        System.out.println("=== Soal 1 & 3: Status seluruh koleksi (polimorfik) ===");
        katalog.cetakStatus();

        System.out.println("\n=== Soal 2: Ringkasan arsip (hanya yang Terarsip) ===");
        Terarsip[] terarsip = {bk1, bk2, mj1, mj2};
        for (Terarsip t : terarsip) {
            System.out.println(t.ringkasanArsip());
        }

        System.out.println("\n=== Soal 4: Urut layak lalu judul ===");
        for (Koleksi k : katalog.urut(new UrutLayakLaluJudul())) {
            System.out.println((k.layakDipinjam() ? "[layak]  " : "[tidak]  ") + k.getJudul());
        }
        System.out.println("--- Urut tahun terbit (Comparator dari luar) ---");
        for (Koleksi k : katalog.urut(Comparator.comparingInt(Koleksi::getTahunTerbit))) {
            System.out.println(k.getTahunTerbit() + "  " + k.getJudul());
        }

        System.out.println("\n=== Soal 5: Peminjaman ===");
        coba(katalog, "BK-001");
        coba(katalog, "BK-001");
        coba(katalog, "BK-002");
        coba(katalog, "MJ-001");
        coba(katalog, "MJ-002");
        coba(katalog, "BD-001");
        coba(katalog, "XX-999");

        System.out.println("\n=== Soal 6: Ekspor katalog ===");
        try {
            new EksporKatalog().ekspor(katalog.getDaftar(), "katalog.txt");
            System.out.println("Berhasil menulis katalog.txt");
        } catch (IOException e) {
            System.out.println("Gagal menulis berkas: " + e.getMessage());
        }
    }

    private static void coba(KatalogPerpustakaan katalog, String kode) {
        try {
            katalog.pinjam(kode);
            System.out.println("Berhasil: " + kode + " dipinjam.");
        } catch (PustakaException e) {
            System.out.println("Gagal   : " + e.getMessage());
        }
    }
}
