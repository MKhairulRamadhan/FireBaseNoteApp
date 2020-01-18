package com.example.firebasenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage;
    Button btnAddnew;

    DatabaseReference reference;
    RecyclerView ournotes;
    ArrayList<MyNotes> list;
    NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddnew = findViewById(R.id.btnAddNew);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitilepage);
        endpage = findViewById(R.id.endpage);

        btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, NewNote.class);
                startActivity(a);
            }
        });

//        work data
        ournotes = findViewById(R.id.ournote);
        ournotes.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyNotes>();

//        get data firebase
        reference = FirebaseDatabase.getInstance().getReference().child("NoteData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //retrieve daata
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyNotes p = dataSnapshot1.getValue(MyNotes.class);
                    list.add(p);
                }

                //get data from firebase
                adapter = new NotesAdapter(MainActivity.this, list);
                ournotes.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
