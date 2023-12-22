package id.ac.unib.e_modultematikkelas4sd.ui.latihan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.unib.e_modultematikkelas4sd.R;

public class LatihanAdapter extends RecyclerView.Adapter<LatihanViewHolder> {
    private ArrayList<Latihan> latihanList;

    public LatihanAdapter(ArrayList<Latihan> latihanList) {
        this.latihanList = latihanList;
    }

    @NonNull
    @Override
    public LatihanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_pertanyaan, parent, false);
        return new LatihanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatihanViewHolder holder, int position) {
        Latihan latihan = latihanList.get(position);
        holder.bindData(latihan);
    }

    @Override
    public int getItemCount() {
        return latihanList.size();
    }
}

