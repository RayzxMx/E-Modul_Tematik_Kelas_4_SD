package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentJawabanBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentPertanyaanBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.home.HomeFragment;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Jawaban;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Latihan;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.LatihanAdapter;

public class HasilFragment extends Fragment {
    private FragmentJawabanBinding binding;
    private SharedPreferences sharedPreferences;
    List<Latihan> latihanList = new ArrayList<>();
    List<detailJawaban> detailJawabanList = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJawabanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Mendapatkan DatabaseReference untuk child "latihan"
        DatabaseReference latihanRef = FirebaseDatabase.getInstance().getReference().child("latihan");
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        int listLatihanId = sharedPreferences.getInt("listLatihanId", 1);
        // Membuat query untuk mendapatkan data dengan key "bab" yang sama dengan 1
        Query query = latihanRef.orderByChild("bab").equalTo(listLatihanId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    // Iterasi melalui dataSnapshot untuk mendapatkan data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String pertanyaan = snapshot.child("pertanyaan").getValue(String.class);
                        String id = snapshot.child("id").getValue(String.class);
                        int bab = snapshot.child("bab").getValue(Integer.class);
                        latihanList.add(new Latihan(pertanyaan, id, bab));
                    }
                    cekKoreksi();
                    //getDataJawaban();

                } else {
                    // Data tidak ditemukan untuk key "bab" dengan nilai 1
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi error saat mengambil data
            }
        });

        return root;
    }
    private void cekKoreksi(){
        int listLatihanId = sharedPreferences.getInt("listLatihanId", 1);
        // Mendapatkan DatabaseReference untuk child "latihan"
        DatabaseReference latihanRef = FirebaseDatabase.getInstance().getReference().child("detail_jawaban");
        Query query = latihanRef.orderByChild("bab").equalTo(listLatihanId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("coba", "Masuk: ");
                if (dataSnapshot.exists()) {
                    // Iterasi melalui dataSnapshot untuk mendapatkan data
                    int i = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String username = snapshot.child("username").getValue(String.class);
                        String selectedUser = sharedPreferences.getString("userSelected","null");
                        if (username.equals(selectedUser)){
                            Integer bab = snapshot.child("bab").getValue(Integer.class);
                            Integer score = snapshot.child("score").getValue(Integer.class);
                            String latihanId = latihanList.get(i).getId();
                            detailJawabanList.add(new detailJawaban(score,username,bab,latihanId));
                            i++;
                        }
                        Log.d("coba", "selected User & username: "+selectedUser+" | "+username);
                    }
                } else {
                    // Data tidak ditemukan untuk key "bab" dengan nilai 1
                }
                Log.d("coba", "scoreList | Size: "+ detailJawabanList.isEmpty() + " | "+detailJawabanList.size());
                getDataJawaban();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi error saat mengambil data
            }
        });
    }
    private void getDataJawaban(){
        DatabaseReference jawabanRef = FirebaseDatabase.getInstance().getReference().child("jawaban");
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        int listLatihanId = sharedPreferences.getInt("listLatihanId", 1);

        // Membuat query untuk mendapatkan data dengan key "bab" yang sama dengan 1
        Query query = jawabanRef.orderByChild("bab").equalTo(listLatihanId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Jawaban> jawabanList = new ArrayList<>();
                    String selectedUser = sharedPreferences.getString("userSelected","null");
                    //Log.d("coba", "selectedUser: " + selectedUser);
                    // Iterasi melalui dataSnapshot untuk mendapatkan data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String jawab = snapshot.child("jawab").getValue(String.class);
                        String id = snapshot.child("latihanId").getValue(String.class);
                        Integer bab = snapshot.child("bab").getValue(Integer.class);
                        String username = snapshot.child("username").getValue(String.class);

                        assert username != null;
                        if (username.equals(selectedUser)){
                            jawabanList.add(new Jawaban(jawab, id, bab,selectedUser));
                        }


                    }
                    // Inisialisasi RecyclerView
                    RecyclerView recyclerView = binding.recyclerView;
                    String role = sharedPreferences.getString("role", "null");


                    // Inisialisasi Adapter
                    Boolean isSiswa = false;
                    if (role.equals("siswa")){
                        isSiswa = true;
                    } else {
                        isSiswa = false;
                    }
                    JawabanAdapter adapter = new JawabanAdapter(getContext(), latihanList,
                            jawabanList, detailJawabanList, isSiswa);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    int totalNilai = 0;
                    for (int i = 0; i < detailJawabanList.size();i++){
                        totalNilai += detailJawabanList.get(i).getScore();
                    }
                    TextView total = binding.totalScore;
                    total.setText(String.valueOf(totalNilai)+"/100");
                    // Set adapter ke RecyclerView
                    recyclerView.setAdapter(adapter);

                    Button btnSubmit = binding.btnSubmit;
                    if(!detailJawabanList.isEmpty() || role.equals("siswa")){
                        btnSubmit.setVisibility(View.GONE);
                    } else {
                        btnSubmit.setVisibility(View.VISIBLE);
                    }
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Iterasi melalui setiap item dalam RecyclerView
                            detailJawaban detail = null;
                            int nilai = 0;
                            String username = sharedPreferences.getString("userSelected", "null");
                            int bab = sharedPreferences.getInt("listLatihanId", 1);
                            for (int i = 0; i < latihanList.size(); i++) {
                                View viewChild = recyclerView.getChildAt(i);
                                EditText editText = viewChild.findViewById(R.id.score);
                                int ans = Integer.parseInt(String.valueOf(editText.getText()));
                                String latihanId = latihanList.get(i).getId();
                                nilai += ans;
                                detail = new detailJawaban(ans,username,bab,latihanId);
                                addDataToFirebaseDetail(detail);
                            }

                            score saveNilai = new score(nilai,username,listLatihanId);
                            addDataToFirebase(saveNilai);
                            switch_fragment();
                        }
                    });
                    Log.d("coba", "JawabanList: "+jawabanList.isEmpty());
                    Log.d("coba", "LatihanList: "+latihanList.isEmpty());
                } else {
                    // Data tidak ditemukan untuk key "bab" dengan nilai 1
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika terjadi error saat mengambil data
            }
        });
    };
    private void addDataToFirebase(score hasil) {
        DatabaseReference jawabanRef = FirebaseDatabase.getInstance().getReference().child("nilai");
            String key = jawabanRef.push().getKey(); // Dapatkan kunci unik baru untuk setiap entri
            // Simpan JawabanModel ke Firebase dengan kunci unik
            jawabanRef.child(key).setValue(hasil)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(getContext(), "Data added to Firebase", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(getContext(), "Failed to add data to Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
    private void addDataToFirebaseDetail(detailJawaban detail) {
        DatabaseReference jawabanRef = FirebaseDatabase.getInstance().getReference().child("detail_jawaban");
        String key = jawabanRef.push().getKey(); // Dapatkan kunci unik baru untuk setiap entri
        // Simpan JawabanModel ke Firebase dengan kunci unik
        jawabanRef.child(key).setValue(detail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Data added to Firebase", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to add data to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void switch_fragment(){

        // Mendapatkan instance dari FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Membuat transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Membuat instance dari fragment yang akan ditampilkan
        Fragment fragment = new HomeFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

        // Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
        // Melakukan commit transaksi
        fragmentTransaction.commit();

    }
}