package id.ac.unib.e_modultematikkelas4sd.ui.home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentMateriBinding;
import id.ac.unib.e_modultematikkelas4sd.databinding.FragmentPetunjukBinding;


public class PetunjukFragment extends Fragment {

    private FragmentPetunjukBinding binding;
    private ImageView pdfImageView;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPetunjukBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final InputStream inputStream = getResources().openRawResource(R.raw.petunjuk_pemakaian);

        PDFView pdfView = binding.pdfView;
        // Mendapatkan InputStream dari file PDF yang ada di res/raw

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