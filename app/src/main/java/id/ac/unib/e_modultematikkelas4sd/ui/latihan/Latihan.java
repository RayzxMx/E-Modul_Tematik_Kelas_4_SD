package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.util.Log;

public class Latihan {
    private String id;
    private String pertanyaan;
    private Integer bab;
    public Latihan(String pertanyaan, String id, Integer bab) {
        this.pertanyaan = pertanyaan;
        this.id = id;
        this.bab = bab;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public Integer getBab() {
        return bab;
    }

    public void setBab(Integer bab) {
        this.bab = bab;
    }
}

