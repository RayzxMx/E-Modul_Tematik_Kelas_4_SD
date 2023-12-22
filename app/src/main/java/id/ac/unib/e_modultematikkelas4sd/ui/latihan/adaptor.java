package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.content.Context;
import android.hardware.lights.LightsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.R;
import kotlin.jvm.internal.Lambda;

public class adaptor extends RecyclerView.Adapter<viewHolder> {

    Context context;
    List<items> itemList;

    public adaptor(Context context, List<items> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.layout_card_pertanyaan,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.soal_tv.setText(itemList.get(position).getSoal());
        holder.jawaban_et.setText(itemList.get(position).getJawaban());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
