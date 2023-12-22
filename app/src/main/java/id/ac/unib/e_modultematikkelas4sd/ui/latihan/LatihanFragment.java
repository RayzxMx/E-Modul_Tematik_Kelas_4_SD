package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentHomeBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentLatihanBinding;
import id.ac.unib.e_modultematikkelas4sd.ui.home.MateriFragment;
import id.ac.unib.e_modultematikkelas4sd.ui.home.PetunjukFragment;
import id.ac.unib.e_modultematikkelas4sd.ui.home.poin_integrasi;

public class LatihanFragment extends Fragment {

    private FragmentLatihanBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLatihanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView listView = binding.menuList;
        // Membuat ArrayList contoh
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Bab 1: Upaya Menjaga Keseimbangan Lingkungan");
        //itemList.add("Item 2");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        switch_fragment();
                }
            }
        });
        return root;
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
        Fragment fragment = new PertanyaanFragment(); // Gantilah TargetFragment dengan fragment yang ingin Anda tampilkan

// Mengganti fragment yang saat ini ditampilkan dengan fragment yang baru
        fragmentTransaction.replace(R.id.fragment_container, fragment);

// Menambahkan transaksi ke back stack (jika diperlukan, agar pengguna dapat kembali)
        fragmentTransaction.addToBackStack(null);
// Melakukan commit transaksi
        fragmentTransaction.commit();

    }

}