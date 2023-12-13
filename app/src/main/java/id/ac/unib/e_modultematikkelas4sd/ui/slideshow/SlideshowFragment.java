package id.ac.unib.e_modultematikkelas4sd.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.InputStream;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        PDFView pdfView = binding.pdfView;
        InputStream inputStream = getResources().openRawResource(R.raw.poin_integrasi);

        // Menampilkan PDF dari InputStream
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