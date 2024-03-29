package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.unib.e_modultematikkelas4sd.R;

public class LatihanViewHolder extends RecyclerView.ViewHolder {
    TextView pertanyaanTextView;

    public LatihanViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d("coba", "View Hilder Terbentuk");
        pertanyaanTextView = itemView.findViewById(R.id.soalText);
    }

    public void bindData(Latihan latihan) {
        pertanyaanTextView.setText(latihan.getPertanyaan());
    }
}
