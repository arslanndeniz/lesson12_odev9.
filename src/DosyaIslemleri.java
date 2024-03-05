import java.io.*;

public class DosyaIslemleri {

    public static String dosyayiOku(String dosyaYolu) throws DosyaIslemleriException {
        File dosya = new File(dosyaYolu);
        StringBuilder icerik = new StringBuilder();

        try {
            BufferedReader okuyucu = new BufferedReader(new FileReader(dosya));
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                icerik.append(satir).append("\n");
            }
            okuyucu.close();
        } catch (FileNotFoundException ex) {
            throw new DosyaIslemleriException("Dosya bulunamadı: " + dosyaYolu);
        } catch (IOException ex) {
            throw new DosyaIslemleriException("Dosya okunurken bir hata oluştu: " + dosyaYolu);
        }

        return icerik.toString();
    }

    public static void dosyayaYaz(String dosyaYolu, String icerik) throws DosyaIslemleriException {
        try {
            BufferedWriter yazici = new BufferedWriter(new FileWriter(dosyaYolu));
            yazici.write(icerik);
            yazici.close();
            System.out.println("Dosya başarıyla yazıldı: " + dosyaYolu);
        } catch (IOException ex) {
            throw new DosyaIslemleriException("Dosya yazma hatası: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        String okunacakDosyaYolu = "okunacakDosya.txt";
        String yazilacakDosyaYolu = "yazilacakDosya.txt";

        try {
            String okunanIcerik = dosyayiOku(okunacakDosyaYolu);
            System.out.println("Okunan dosya içeriği:\n" + okunanIcerik);

            // Okunan içeriği yazma işlemi
            dosyayaYaz(yazilacakDosyaYolu, okunanIcerik);
        } catch (DosyaIslemleriException ex) {
            System.err.println("Hata: " + ex.getMessage());
        }
    }
}