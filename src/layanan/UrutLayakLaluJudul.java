package layanan;

import model.Koleksi;

import java.util.Comparator;

/** Koleksi layak pinjam tampil lebih dulu, lalu diurutkan berdasarkan judul. */
public class UrutLayakLaluJudul implements Comparator<Koleksi> {
    private static final Comparator<Koleksi> BERLAPIS =
            Comparator.comparing((Koleksi k) -> !k.layakDipinjam())
                    .thenComparing(Koleksi::getJudul, String.CASE_INSENSITIVE_ORDER);

    @Override
    public int compare(Koleksi a, Koleksi b) {
        return BERLAPIS.compare(a, b);
    }
}
