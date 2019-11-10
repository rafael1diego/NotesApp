package com.dflores.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dflores.notesapp.R;
import com.dflores.notesapp.adapters.NoteAdapter;
import com.dflores.notesapp.models.Notes;
import com.dflores.notesapp.models.User;
import com.dflores.notesapp.repositories.NoteRepository;
import com.dflores.notesapp.repositories.UserRepository;

import java.util.List;

public class Notas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonregisternota;
    private TextView nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        recyclerView = findViewById(R.id.notes_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapters=new NoteAdapter(this);

        List<Notes> notes = NoteRepository.list();

        adapters.setNotes(notes);

        recyclerView.setAdapter(adapters);

        buttonregisternota=findViewById(R.id.button_register_nota);
        buttonregisternota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showregister();
            }
        });

        nameUser=findViewById(R.id.nameuser_view);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Long id = sp.getLong("id",0);

        User user= UserRepository.load(id);

        if (user==null){
            startActivity(new Intent(this,Login.class));
            finish();

        }

        nameUser.setText("Bienvenido "+user.getFullname());

    }
    private void showregister(){
        startActivity(new Intent(this,RegisterNote.class));
    }
}