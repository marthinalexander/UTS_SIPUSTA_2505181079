package layanan;

import eksepsi.KoleksiSedangDipinjamException;
import eksepsi.KoleksiTidakLayakException;
import eksepsi.PustakaException;
import model.Koleksi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KatalogPerpustakaan {
    private final List<Koleksi> daftar = new ArrayList<>();

    public void tambah(Koleksi koleksi) {
        daftar.add(koleksi);
    }

    public List<Koleksi> getDaftar() {
        return new ArrayList<>(daftar);
    }

    /** Memproses seluruh koleksi secara polimorfik, tanpa percabangan tipe. */
    public void cetakStatus() {
        for (Koleksi k : daftar) {
            String status = k.layakDipinjam() ? "LAYAK" : "TIDAK LAYAK";
            String dipinjam = k.isSedangDipinjam() ? " | sedang dipinjam" : "";
            System.out.println(k.jenis() + " " + k.deskripsi() + " -> " + status + dipinjam);
        }
    }

    /** Mengembalikan daftar baru yang diurutkan memakai aturan dari luar. */
    public List<Koleksi> urut(Comparator<? super Koleksi> pembanding) {
        List<Koleksi> hasil = new ArrayList<>(daftar);
        hasil.sort(pembanding);
        return hasil;
    }

    public void pinjam(String kode) throws PustakaException {
        Koleksi koleksi = cari(kode);
        if (koleksi.isSedangDipinjam()) {
            throw new KoleksiSedangDipinjamException(koleksi.getJudul());
        }
        if (!koleksi.layakDipinjam()) {
            throw new KoleksiTidakLayakException(koleksi.getJudul(), koleksi.alasanTidakLayak());
        }
        koleksi.setSedangDipinjam(true);
    }

    public void kembalikan(String kode) throws PustakaException {
        cari(kode).setSedangDipinjam(false);
    }

    private Koleksi cari(String kode) throws PustakaException {
        for (Koleksi k : daftar) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        throw new PustakaException("Koleksi dengan kode " + kode + " tidak ditemukan di katalog.");
    }
}
