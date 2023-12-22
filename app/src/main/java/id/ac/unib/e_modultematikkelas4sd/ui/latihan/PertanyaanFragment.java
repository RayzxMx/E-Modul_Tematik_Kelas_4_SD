package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentLatihanBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentPertanyaanBinding;

public class PertanyaanFragment extends Fragment {
    private FragmentPertanyaanBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPertanyaanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Mendapatkan DatabaseReference untuk child "latihan"
        DatabaseReference latihanRef = FirebaseDatabase.getInstance().getReference().child("latihan");

// Membuat query untuk mendapatkan data dengan key "bab" yang sama dengan 1
        Query query = latihanRef.orderByChild("bab").equalTo(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<String> pertanyaan = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    TextView textView = binding.textView2;

                    // Iterasi melalui dataSnapshot untuk mendapatkan data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String latihan = snapshot.child("pertanyaan").getValue(String.class);
                        pertanyaan.add(latihan);

                    }

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}