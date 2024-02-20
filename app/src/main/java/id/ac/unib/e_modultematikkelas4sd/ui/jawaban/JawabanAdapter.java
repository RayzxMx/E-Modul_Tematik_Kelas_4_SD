package id.ac.unib.e_modultematikkelas4sd.ui.jawaban;// CustomAdapter.java

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.unib.e_modultematikkelas4sd.R;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Jawaban;
import id.ac.unib.e_modultematikkelas4sd.ui.latihan.Latihan;

public class JawabanAdapter extends RecyclerView.Adapter<JawabanAdapter.ViewHolder> {

    private List<Latihan> dataList;
    private List<Jawaban> jawabanList;
    private List<detailJawaban> scoreList;
    private Boolean isSiswa;
    private Context context;

    public JawabanAdapter(Context context, List<Latihan> dataList,
                          List<Jawaban> jawabanList, List<detailJawaban> scoreList,
                          Boolean isSiswa) {
        this.context = context;
        this.dataList = dataList;
        this.jawabanList = jawabanList;
        this.scoreList = scoreList;
        this.isSiswa = isSiswa;
    }

    // ViewHolder untuk menahan referensi dari View
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewItem;
        public EditText jawabanET;
        public EditText scoreET;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.soalText);
            jawabanET = itemView.findViewById(R.id.jawabanET);
            scoreET = itemView.findViewById(R.id.score);
        }
    }

    // Method untuk meng-inflate layout item_list.xml ke ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_jawaban, parent, false);
        return new ViewHolder(view);
    }

    // Method untuk menghubungkan data di dalam list dengan ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = dataList.get(position).getPertanyaan();
        holder.textViewItem.setText(data);
        holder.jawabanET.setEnabled(false);

        if (!jawabanList.isEmpty()){
            String jawaban = jawabanList.get(position).getJawab();
            holder.jawabanET.setText(jawaban);
            if (!scoreList.isEmpty()){
                String score = scoreList.get(position).getScore().toString();
                holder.scoreET.setText(score);
                holder.scoreET.setEnabled(false);
            }
            if (isSiswa){
                holder.scoreET.setEnabled(false);
            }
        } else {
            holder.jawabanET.setText("");
        }

    }

    // Method untuk mendapatkan jumlah data yang akan ditampilkan
    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
