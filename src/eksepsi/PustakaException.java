package eksepsi;

/** Induk seluruh eksepsi layanan perpustakaan. */
public class PustakaException extends Exception {
    public PustakaException(String pesan) {
        super(pesan);
    }
}
