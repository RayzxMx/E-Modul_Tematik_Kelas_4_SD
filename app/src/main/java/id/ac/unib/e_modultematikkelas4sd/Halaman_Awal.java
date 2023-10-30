package id.ac.unib.e_modultematikkelas4sd;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import id.ac.unib.e_modultematikkelas4sd.databinding.ActivityHalamanAwalBinding;

public class Halaman_Awal extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHalamanAwalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHalamanAwalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}