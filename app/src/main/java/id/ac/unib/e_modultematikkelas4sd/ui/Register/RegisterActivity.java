package id.ac.unib.e_modultematikkelas4sd.ui.Register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.MainActivity;
import id.ac.unib.e_modultematikkelas4sd.databinding.ActivityRegisterBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DatabaseReference mDatabase;
    private List<User> dataList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Mendapatkan objek SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Membuat objek Editor untuk mengedit SharedPreferences
        editor = sharedPreferences.edit();
        String[] data = {"Role","Guru", "Siswa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                // Jika ini adalah placeholder, atur teks menjadi abu-abu atau sesuai kebutuhan
                if (position == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                }

                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final EditText konfirmEditText = binding.konfPassword;
        final EditText namaEditText = binding.nama;
        final Button daftarButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
        final Spinner spinner = binding.role;
        spinner.setAdapter(adapter);
        daftarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                daftar(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        konfirmEditText.getText().toString(),
                        spinner,
                        namaEditText.getText().toString());
            }
        });

    }

    public void daftar(String username, String password, String konfirm, Spinner roleSpin, String nama){
        if (username.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username Kosong, Mohon diisi", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password Kosong, Mohon diisi", Toast.LENGTH_SHORT).show();
        } else if (konfirm.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Konfirmasi Password Kosong, Mohon diisi", Toast.LENGTH_SHORT).show();
        } else if (nama.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nama Kosong, Mohon diisi", Toast.LENGTH_SHORT).show();
        } else if (!konfirm.equals(password)) {
            Toast.makeText(getApplicationContext(), "Konfirmasi Password Tidak Sama Dengan Password, Mohon diisi Ulang", Toast.LENGTH_SHORT).show();
        } else if (roleSpin.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Tolong Pilih Role Anda", Toast.LENGTH_SHORT).show();
        } else {
            dataList.add(new User(nama, password, username, roleSpin.getSelectedItem().toString().toLowerCase()));
            addDataToFirebase(dataList);
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    private void addDataToFirebase(List<User> data) {
        DatabaseReference jawabanRef = FirebaseDatabase.getInstance().getReference().child("user");
        for (User user : data) {
            String key = jawabanRef.push().getKey(); // Dapatkan kunci unik baru untuk setiap entri

            // Simpan JawabanModel ke Firebase dengan kunci unik
            jawabanRef.child(key).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Data added to Firebase", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to add data to Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}