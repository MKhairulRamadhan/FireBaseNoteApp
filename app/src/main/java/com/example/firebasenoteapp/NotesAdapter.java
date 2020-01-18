package com.example.firebasenoteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyNotes> myNotes;

    public NotesAdapter(Context c, ArrayList<MyNotes> p){
        context = c;
        myNotes = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titlenotes.setText(myNotes.get(position).getTitlenotes());
        holder.descnotes.setText(myNotes.get(position).getDescnotes());
        holder.datenotes.setText(myNotes.get(position).getDatenotes());

        final String title = myNotes.get(position).getTitlenotes();
        final String desc = myNotes.get(position).getDescnotes();
        final String date = myNotes.get(position).getDatenotes();
        final String key = myNotes.get(position).getKeynotes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent polo = new Intent(context, EditNotes.class);
                polo.putExtra("titlenote", title);
                polo.putExtra("descnote", desc);
                polo.putExtra("datenote", date);
                polo.putExtra("keynotes", key);
                context.startActivity(polo);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titlenotes, descnotes, datenotes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titlenotes = (TextView) itemView.findViewById(R.id.titlenotes);
            descnotes = (TextView) itemView.findViewById(R.id.descnotes);
            datenotes = (TextView) itemView.findViewById(R.id.datenotes);
        }
    }

}
