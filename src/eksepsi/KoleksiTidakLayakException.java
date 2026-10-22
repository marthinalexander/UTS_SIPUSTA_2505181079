package eksepsi;

public class KoleksiTidakLayakException extends PustakaException {
    public KoleksiTidakLayakException(String judul, String alasan) {
        super("Maaf, \"" + judul + "\" belum bisa dipinjam saat ini karena " + alasan + ".");
    }
}
