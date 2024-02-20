package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

public class score {
    private Integer score;
    private String username;
    private Integer bab;

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

    public score(Integer score, String username, Integer bab) {
        this.score = score;
        this.username = username;
        this.bab = bab;
    }
}
