package com.example.colleaugeinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colleaugeinfo.model.Colleague;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColleagueAdapter extends RecyclerView.Adapter<ColleagueAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private Context context ;
    private List<Colleague> list ;
    private ClickListener clickListener ;

    public ColleagueAdapter(Context context, List<Colleague> list){
        this.context = context ;
        this.list = list ;
        inflater = LayoutInflater.from(context) ;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_colleague, parent, false) ;
        MyViewHolder myViewHolder = new MyViewHolder(view) ;
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface ClickListener{

        public void itemClicked(int pos);
    }
}
