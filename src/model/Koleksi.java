package model;

/**
 * Kelas induk seluruh koleksi perpustakaan.
 * Abstract: tiap jenis koleksi punya aturan kelayakan pinjam sendiri.
 */
public abstract class Koleksi {
    private final String kode;
    private final String judul;
    private final int tahunTerbit;
    private boolean sedangDipinjam;

    protected Koleksi(String kode, String judul, int tahunTerbit) {
        this.kode = kode;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.sedangDipinjam = false;
    }

    /** Aturan kelayakan pinjam, ditentukan oleh masing-masing turunan. */
    public abstract boolean layakDipinjam();

    /** Alasan yang mudah dipahami pengguna bila koleksi tidak layak dipinjam. */
    public String alasanTidakLayak() {
        return "koleksi ini tidak memenuhi syarat peminjaman";
    }

    /** Deskripsi dasar; diperluas oleh setiap turunan memakai super. */
    public String deskripsi() {
        return "[" + kode + "] \"" + judul + "\" (" + tahunTerbit + ")";
    }

    /** Nama jenis koleksi, dipakai untuk tampilan dan ekspor. */
    public String jenis() {
        return getClass().getSimpleName();
    }

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public boolean isSedangDipinjam() {
        return sedangDipinjam;
    }

    public void setSedangDipinjam(boolean sedangDipinjam) {
        this.sedangDipinjam = sedangDipinjam;
    }
}
