package id.ac.unib.e_modultematikkelas4sd.ui.Nilai;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentListJawabanUserBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.jawaban.HasilFragment;
import id.ac.unib.e_modultematikkelas4sd.ui.jawaban.ListJawabanDalamFragment;

public class ListUserFragment extends Fragment {

    private FragmentListJawabanUserBinding binding;
    private SharedPreferences sharedPreferences;
    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListJawabanUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d("coba", "onCreateView: ");
        // Mendapatkan objek SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Membuat objek Editor untuk mengedit SharedPreferences
        editor = sharedPreferences.edit();
        // Membuat ArrayList contoh
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.orderByChild("role").equalTo("siswa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        nama.add(snapshot.child("nama").getValue(String.class));
                        username.add(snapshot.child("username").getValue(String.class));
                    }
                    showNilai();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika pembatalan terjadi
            }
        });
        return root;
    }

    public void showNilai(){
        ListView listView = binding.menuList;
        int listLatihanId = sharedPreferences.getInt("listLatihanId", 1);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("nilai");
        databaseReference.orderByChild("bab").equalTo(listLatihanId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> itemList = new ArrayList<>();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String _username = snapshot.child("username").getValue(String.class);
                        for (int i = 0; i < username.size(); i++) {
                            if (username.get(i).equals(_username)){
                                score.add(i,snapshot.child("score").getValue(Integer.class));
                                itemList.add(nama.get(i) + " | "
                                        + username.get(i) + " | "
                                        + snapshot.child("score").getValue(Integer.class));
                            }
                        }
                    }
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = username.get(position);
                            switch_fragment (selectedItem);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Penanganan kesalahan jika pembatalan terjadi
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void switch_fragment( String item){
        editor.putString("userSelected", item);
        editor.apply();
        // Mendapatkan instance dari FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        // Membuat transaksi fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Membuat instance dari fragment yang akan ditampilkan
        Fragment fragment = new HasilFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

        // Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
        // Melakukan commit transaksi
        fragmentTransaction.commit();

    }

}