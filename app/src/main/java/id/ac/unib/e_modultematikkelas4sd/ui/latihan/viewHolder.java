package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.unib.e_modultematikkelas4sd.R;

public class viewHolder extends RecyclerView.ViewHolder {

    TextView soal_tv, nilai_tv;
    EditText jawaban_et;
    Button jawab_btn;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        soal_tv = itemView.findViewById(R.id.soalText);
        nilai_tv = itemView.findViewById(R.id.nilaiText);
        jawaban_et = itemView.findViewById(R.id.jawabanET);
        jawab_btn = itemView.findViewById(R.id.btn_jawab);

        jawab_btn.setOnClickListener(v -> {

        });
    }
}
