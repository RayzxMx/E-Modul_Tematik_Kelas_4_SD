package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Latihan;

public class JawabanViewHolder extends RecyclerView.ViewHolder {
    TextView pertanyaanTextView;

    public JawabanViewHolder(@NonNull View itemView) {
        super(itemView);
        pertanyaanTextView = itemView.findViewById(R.id.soalText);
    }

    public void bindData(Latihan latihan) {
        pertanyaanTextView.setText(latihan.getPertanyaan());
    }
}
