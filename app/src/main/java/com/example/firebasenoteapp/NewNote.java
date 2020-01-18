package com.example.firebasenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewNote extends AppCompatActivity {

    TextView titlepage, addtitle, adddescription, addtime;
    EditText titlenote, descnote, timenote;
    Button btnCreate, btnCancel;

    DatabaseReference reference;
    Integer noteNumber = new Random().nextInt();
    String keynotes = Integer.toString(noteNumber);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        titlepage =  findViewById(R.id.titlepage);
        addtitle = findViewById(R.id.addtitle);
        adddescription = findViewById(R.id.adddescription);
        addtitle = findViewById(R.id.addtime);

        titlenote = findViewById(R.id.titleNotes);
        descnote = findViewById(R.id.descriptionNotes);
        timenote = findViewById(R.id.timeNotes);

        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnSaveTask);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inser data to firebase

                //note1 = new dir
                reference = FirebaseDatabase.getInstance().getReference().child("NoteData")
                        .child("Notes"+ noteNumber);


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //save data
                        dataSnapshot.getRef().child("titlenotes").setValue(titlenote.getText().toString());
                        dataSnapshot.getRef().child("descnotes").setValue(descnote.getText().toString());
                        dataSnapshot.getRef().child("datenotes").setValue(timenote.getText().toString());
                        dataSnapshot.getRef().child("keynotes").setValue(keynotes);

                        Intent a = new Intent(NewNote.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent polo = new Intent(NewNote.this, MainActivity.class);
                startActivity(polo);
            }
        });
    }
}
