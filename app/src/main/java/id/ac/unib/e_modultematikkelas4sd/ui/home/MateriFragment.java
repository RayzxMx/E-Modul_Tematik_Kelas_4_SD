package id.ac.unib.e_modultematikkelas4sd.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.InputStream;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentMateriBinding;

public class MateriFragment extends Fragment {

    private FragmentMateriBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMateriBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PDFView pdfView = binding.pdfView;
        // Mendapatkan InputStream dari file PDF yang ada di res/raw
        final InputStream inputStream = getResources().openRawResource(R.raw.materi);
        pdfView.setVisibility(View.VISIBLE); // Tampilkan PDFView
        pdfView.fromStream(inputStream)
                .enableSwipe(true)
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .swipeHorizontal(false)
                .load();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}