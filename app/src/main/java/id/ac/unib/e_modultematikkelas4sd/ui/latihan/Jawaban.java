package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

public class Jawaban {
    private String jawab;
    private String latihanId;
    private Integer bab;
    private String username;
    public Jawaban(String jawab, String latihanId, Integer bab, String username) {
        this.jawab = jawab;
        this.latihanId = latihanId;
        this.bab = bab;
        this.username = username;
    }

    public String getJawab() {
        return jawab;
    }

    public void setJawab(String jawab) {
        this.jawab = jawab;
    }

    public String getLatihanId() {
        return latihanId;
    }

    public void setLatihanId(String latihanId) {
        this.latihanId = latihanId;
    }

    public Integer getBab() {
        return bab;
    }

    public void setBab(Integer bab) {
        this.bab = bab;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
