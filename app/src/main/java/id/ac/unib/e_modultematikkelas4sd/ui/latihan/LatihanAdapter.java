package id.ac.unib.e_modultematikkelas4sd.ui.latihan;// CustomAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.R;

public class LatihanAdapter extends RecyclerView.Adapter<LatihanAdapter.ViewHolder> {

    private List<Latihan> dataList;
    private Context context;

    public LatihanAdapter(Context context, List<Latihan> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // ViewHolder untuk menahan referensi dari View
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItem;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.soalText);
        }
    }

    // Method untuk meng-inflate layout item_list.xml ke ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_pertanyaan, parent, false);
        return new ViewHolder(view);
    }

    // Method untuk menghubungkan data di dalam list dengan ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = dataList.get(position).getPertanyaan();
        holder.textViewItem.setText(data);
    }

    // Method untuk mendapatkan jumlah data yang akan ditampilkan
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
