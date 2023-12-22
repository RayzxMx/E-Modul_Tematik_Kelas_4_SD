package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.unib.e_modultematikkelas4sd.R;

public class LatihanViewHolder extends RecyclerView.ViewHolder {
    TextView judulTextView;

    public LatihanViewHolder(@NonNull View itemView) {
        super(itemView);
        judulTextView = itemView.findViewById(R.id.soalText);
    }

    public void bindData(Latihan latihan) {
        judulTextView.setText(latihan.getJudul());
    }
}
