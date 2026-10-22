package layanan;

import model.Koleksi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/** Menulis seluruh koleksi ke berkas teks dengan kolom sejajar rapi. */
public class EksporKatalog {

    public void ekspor(List<Koleksi> daftar, String namaBerkas) throws IOException {
        int lebarJudul = "Judul".length();
        int lebarJenis = "Jenis".length();
        for (Koleksi k : daftar) {
            lebarJudul = Math.max(lebarJudul, k.getJudul().length());
            lebarJenis = Math.max(lebarJenis, k.jenis().length());
        }
        String format = "%-8s | %-" + lebarJenis + "s | %-" + lebarJudul + "s | %-5s | %s%n";

        try (BufferedWriter out = Files.newBufferedWriter(Paths.get(namaBerkas), StandardCharsets.UTF_8)) {
            out.write(String.format(format, "Kode", "Jenis", "Judul", "Tahun", "Status"));
            out.write("-".repeat(8 + lebarJenis + lebarJudul + 5 + 11 + 12));
            out.newLine();
            for (Koleksi k : daftar) {
                out.write(String.format(format, k.getKode(), k.jenis(), k.getJudul(),
                        k.getTahunTerbit(), k.layakDipinjam() ? "Layak dipinjam" : "Tidak layak"));
            }
        }
    }
}
