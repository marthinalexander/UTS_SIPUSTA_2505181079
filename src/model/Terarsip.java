package model;

/**
 * Kemampuan koleksi yang memiliki lokasi fisik di rak.
 * Hanya BukuCetak dan Majalah yang menerapkannya.
 */
public interface Terarsip {
    String nomorRak();

    int lokasiLantai();

    default String ringkasanArsip() {
        return "Rak " + nomorRak() + ", Lantai " + lokasiLantai();
    }
}
