package com.example.firebasenoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNotes extends AppCompatActivity {

    EditText titleNotes, descriptionNotes, timeNotes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        titleNotes = findViewById(R.id.titleNotes);
        descriptionNotes = findViewById(R.id.descriptionNotes);
        timeNotes = findViewById(R.id.timeNotes);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //get data intent
        titleNotes.setText(getIntent().getStringExtra("titlenote"));
        descriptionNotes.setText(getIntent().getStringExtra("descnote"));
        timeNotes.setText(getIntent().getStringExtra("datenote"));

        final String keynotes = getIntent().getStringExtra("keynotes");

        reference = FirebaseDatabase.getInstance().getReference().child("NoteData")
                .child("Notes"+ keynotes);


        //make event
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titlenotes").setValue(titleNotes.getText().toString());
                        dataSnapshot.getRef().child("descnotes").setValue(descriptionNotes.getText().toString());
                        dataSnapshot.getRef().child("datenotes").setValue(timeNotes.getText().toString());
                        Intent polo = new Intent(EditNotes.this, MainActivity.class);
                        startActivity(polo);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EditNotes.this, titleNotes.getText().toString()+ ", Deleted!", Toast.LENGTH_SHORT).show();
                            Intent polo = new Intent(EditNotes.this, MainActivity.class);
                            startActivity(polo);
                        }else {
                            Toast.makeText(EditNotes.this, "Something Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
