package id.ac.unib.e_modultematikkelas4sd.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.InputStream;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentPoinIntegrasiBinding;


public class poin_integrasi extends Fragment {
    private FragmentPoinIntegrasiBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPoinIntegrasiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PDFView pdfView = binding.pdfView;
        // Mendapatkan InputStream dari file PDF yang ada di res/raw
        final InputStream inputStream = getResources().openRawResource(R.raw.poin_integrasi);
        pdfView.setVisibility(View.VISIBLE); // Tampilkan PDFView
        pdfView.fromStream(inputStream)
                .pages(0) // Menampilkan halaman pertama (0-based index)
                .load();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}