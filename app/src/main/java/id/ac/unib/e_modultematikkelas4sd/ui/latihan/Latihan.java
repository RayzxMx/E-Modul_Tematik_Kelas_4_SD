package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

public class Latihan {
    private String soal;

    public Latihan() {
        // Diperlukan untuk Firebase (default constructor)
    }

    public Latihan(String soal) {
        this.soal = soal;
    }

    // Getter dan setter
    public String getJudul() {
        return soal;
    }

    public void setJudul(String soal) {
        this.soal = soal;
    }

}
