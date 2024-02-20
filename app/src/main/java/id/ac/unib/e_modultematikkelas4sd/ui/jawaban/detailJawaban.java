package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

public class detailJawaban {
    private Integer score;
    private String username;
    private Integer bab;
    private String latihanId;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBab() {
        return bab;
    }

    public void setBab(Integer bab) {
        this.bab = bab;
    }

    public String getLatihanId() {
        return latihanId;
    }

    public void setLatihanId(String latihanId) {
        this.latihanId = latihanId;
    }

    public detailJawaban(Integer score, String username, Integer bab, String latihanId) {
        this.score = score;
        this.username = username;
        this.bab = bab;
        this.latihanId = latihanId;
    }
}
