package id.ac.unib.e_modultematikkelas4sd.ui.Register;

public class User {
    String nama;
    String password;
    String username;
    String role;

    public User(String nama, String password, String username, String role) {
        this.nama = nama;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
