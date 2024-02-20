package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentLatihanBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentListJawabanUserDalamBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.PertanyaanFragment;

public class ListJawabanDalamFragment extends Fragment {

    private FragmentListJawabanUserDalamBinding binding;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListJawabanUserDalamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView listView = binding.menuList;
        // Mendapatkan objek SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        String userSelected = sharedPreferences.getString("userSelected", "null");
        // Membuat objek Editor untuk mengedit SharedPreferences
        editor = sharedPreferences.edit();

        // Membuat ArrayList contoh
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("list_sub_latihan");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> itemList = new ArrayList<>();
                    ArrayList<Integer> idList = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        itemList.add(snapshot.child("judul").getValue(String.class));
                        idList.add(snapshot.child("id").getValue(Integer.class));
                    }
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Integer selectedItem = idList.get(position);
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



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void switch_fragment( Integer item){
        editor.putInt("listLatihanId", item);
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