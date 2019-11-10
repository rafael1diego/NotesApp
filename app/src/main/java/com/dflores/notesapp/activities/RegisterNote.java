package com.dflores.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dflores.notesapp.R;
import com.dflores.notesapp.repositories.NoteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterNote extends AppCompatActivity {
    private EditText titleInput;
    private EditText conteinerInput;
    private Button  btn_savenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);
        titleInput=findViewById(R.id.title_input);
        conteinerInput=findViewById(R.id.content_input);
        btn_savenote=findViewById(R.id.btn_register);
        btn_savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }
    private void saveNote(){
        try{

            String title=titleInput.getText().toString();
            String content=conteinerInput.getText().toString();

            Date now = new Date();
            String date= new SimpleDateFormat("dd/MM/yyyy").format(now);

            if (title.isEmpty()||content.isEmpty()){
                Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show();
                finish();
            }

            NoteRepository.create(title,content,date);
            Toast.makeText(this,"Nota registrada",Toast.LENGTH_SHORT).show();
            finish();


        }catch (Exception e){
            Log.e("RegisterNote", e.toString(), e);
            Toast.makeText(this,"No pudo registrar la nota",Toast.LENGTH_SHORT).show();
        }
    }
}
