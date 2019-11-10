package com.dflores.notesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dflores.notesapp.R;
import com.dflores.notesapp.repositories.UserRepository;

public class Register extends AppCompatActivity {

    private EditText userInput;
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordnput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userInput = findViewById(R.id.user_input);
        fullnameInput = findViewById(R.id.fullname_input);
        emailInput = findViewById(R.id.email_input);
        passwordnput = findViewById(R.id.password_input);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegister();
            }
        });
    }
    private void callRegister(){
        try{
            String user=userInput.getText().toString();
            String fullname = fullnameInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordnput.getText().toString();

            if (user.isEmpty()||fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
                return;
            }

            UserRepository.create(user,fullname,email,password);

            Toast.makeText(this,"Usuario  Registrado",Toast.LENGTH_SHORT).show();
            finish();

        }catch (Exception e){
            Toast.makeText(this,"No se pudo registrar el usuario "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
