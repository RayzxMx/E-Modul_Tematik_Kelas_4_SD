package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentJawabanBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentPertanyaanBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Jawaban;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Latihan;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.LatihanAdapter;

public class JawabanFragment extends Fragment {
    private FragmentJawabanBinding binding;
    private final List<Latihan> latihanList = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentJawabanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Mendapatkan DatabaseReference untuk child "latihan"
        DatabaseReference latihanRef = FirebaseDatabase.getInstance().getReference().child("jawaban");
        sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        String selectedItem = sharedPreferences.getString("selectedItem", "null");
        // Membuat query untuk mendapatkan data dengan key "bab" yang sama dengan 1
        Query query = latihanRef.orderByChild("username").equalTo(selectedItem);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Latihan> latihanList = new ArrayList<>();
                    List<Jawaban> jawabanList = new ArrayList<>();
                    // Iterasi melalui dataSnapshot untuk mendapatkan data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String pertanyaan = snapshot.child("pertanyaan").getValue(String.class);
                        String id = snapshot.child("id").getValue(String.class);
                        int bab = snapshot.child("bab").getValue(Integer.class);
                        latihanList.add(new Latihan(pertanyaan, id, bab));
                    }
                    // Inisialisasi RecyclerView
                    RecyclerView recyclerView = binding.recyclerView;
                    // Inisialisasi Adapter
                    LatihanAdapter adapter = new LatihanAdapter(getContext(), latihanList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    // Set adapter ke RecyclerView
                    recyclerView.setAdapter(adapter);
                    Button btnSubmit = binding.btnSubmit;
                    btnSubmit.setVisibility(View.VISIBLE);
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Iterasi melalui setiap item dalam RecyclerView
                            for (int i = 0; i < latihanList.size(); i++) {
                                View viewChild = recyclerView.getChildAt(i);
                                EditText editText = viewChild.findViewById(R.id.jawabanET);
                                String ans = String.valueOf(editText.getText());
                                jawabanList.add(new Jawaban(ans,
                                        latihanList.get(i).getId(),
                                        latihanList.get(i).getBab(),
                                        "null"));
                            }
                            addDataToFirebase(jawabanList);
                        }
                    });

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

    private void addDataToFirebase(List<Jawaban> jawabanL) {
        DatabaseReference jawabanRef = FirebaseDatabase.getInstance().getReference().child("jawaban");
        for (Jawaban jawaban : jawabanL) {
            String key = jawabanRef.push().getKey(); // Dapatkan kunci unik baru untuk setiap entri

            // Simpan JawabanModel ke Firebase dengan kunci unik
            jawabanRef.child(key).setValue(jawaban)
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
    }

    private void getAllDataFromFirebase(int bab) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("jawaban");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> itemList = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.child("bab").getValue(Integer.class) == bab){
                            itemList.add((snapshot.child("jawab").getValue(String.class)));
                        }
                    }
                    //listView.setAdapter(adapter);
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
}