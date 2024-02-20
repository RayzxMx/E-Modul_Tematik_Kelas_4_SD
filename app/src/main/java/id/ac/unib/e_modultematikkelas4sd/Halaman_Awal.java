package id.ac.unib.e_modultematikkelas4sd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import id.ac.unib.e_modultematikkelas4sd.databinding.ActivityHalamanAwalBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.Register.RegisterActivity;
import id.ac.unib.e_modultematikkelas4sd.ui.login.LoginActivity;

public class Halaman_Awal extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHalamanAwalBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        binding = ActivityHalamanAwalBinding.inflate(getLayoutInflater());
        Button btnMasuk = binding.btnMasuk;
        Button btnDaftar = binding.btnDaftar;

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        // Pengecekan status login
        if (isLoggedIn) {
            // Jika sudah login, arahkan ke halaman beranda
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), RegisterActivity.class));
            }
        });
        setContentView(binding.getRoot());
    }

}