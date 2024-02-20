package id.ac.unib.e_modultematikkelas4sd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.unib.e_modultematikkelas4sd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setVisibility(View.GONE);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        View nav_header = navigationView.getHeaderView(0);
        TextView nama = nav_header.findViewById(R.id.nama_user);
        String _nama = sharedPreferences.getString("name", "null");
        String role = sharedPreferences.getString("role", "null");
        nama.setText(_nama);
        Menu navigationMenu = navigationView.getMenu();
        MenuItem latihanMenuItem = navigationMenu.findItem(R.id.nav_latihan);
        MenuItem jawabanMenuItem = navigationMenu.findItem(R.id.nav_jawaban);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_materi, R.id.nav_latihan, R.id.nav_jawaban, R.id.nav_lihat_Nilai)
                .setOpenableLayout(drawer)
                .build();
        if (role.equals("guru")){
            jawabanMenuItem.setVisible(true);
            latihanMenuItem.setVisible(false);
        } else {
            latihanMenuItem.setVisible(true);
            jawabanMenuItem.setVisible(false);
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item) {
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // Lakukan logout dan arahkan pengguna ke halaman login
        editor.putString("username", "");
        editor.putString("nama", "");
        editor.putBoolean("isLoggedIn", false);

        // Menyimpan perubahan
        editor.apply();

        startActivity(new Intent(getApplicationContext(), Halaman_Awal.class));
        finish();
    }

}