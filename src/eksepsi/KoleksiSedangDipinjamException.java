package eksepsi;

public class KoleksiSedangDipinjamException extends PustakaException {
    public KoleksiSedangDipinjamException(String judul) {
        super("Maaf, \"" + judul + "\" sedang dipinjam oleh anggota lain. "
                + "Silakan coba lagi setelah koleksi ini dikembalikan.");
    }
}
