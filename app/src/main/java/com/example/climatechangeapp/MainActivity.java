package com.example.climatechangeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RegisterFragment.OnSubmitListener{

    private String username = "", pass = "";
    private boolean anonymousSession = false, validSession = false;

    private EditText userEditText, passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Button logIn, register, anonymous;

        userEditText = findViewById(R.id.userEditText);
        passEditText = findViewById(R.id.passEditText);

        logIn = findViewById(R.id.logInBtn);
        logIn.setOnClickListener(view -> validateLogIn());

        register = findViewById(R.id.registerBtn);
        register.setOnClickListener(view -> showRegisterFrag());

        anonymous = findViewById(R.id.anonymousBtn);
        anonymous.setOnClickListener(view -> goAnonymous());
    }

    @Override
    protected void onResume() {
        super.onResume();
        goToPost();
    }

    private void goAnonymous(){
        this.anonymousSession = true;
        this.validSession = true;
        goToPost();
    }

    private void validateLogIn(){
        if (userEditText.getText().toString().equals("") || passEditText.getText().toString().equals("")){
            Toast.makeText(this, "Hay campos vacios, verificalos!", Toast.LENGTH_SHORT).show();
        }
        //Eliminar y agregar validación de base de datos
        else if (this.username.equals(userEditText.getText().toString()) && this.pass.equals(passEditText.getText().toString())){
            this.validSession = true;
            this.anonymousSession = false;
            Toast.makeText(this, "Bienvenido " + username, Toast.LENGTH_SHORT).show();
            goToPost();
        } else {
            Toast.makeText(this, "Nombre de usuario o contraseña invalida!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToPost(){
        if (validSession){
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra("username", this.username);
            intent.putExtra("anonymous", this.anonymousSession);
            startActivity(intent);
        }
    }

    private void showRegisterFrag(){
        //Metodo para mostrar el fragmento
        RegisterFragment rf = new RegisterFragment();
        rf.setOnSubmitListener(this);
        rf.show(getSupportFragmentManager(), "RegisterFragment");
    }

    @Override
    public void onSubmit(String username, String password){
        if (isUsernameValid()){
            //Registrar usuario a la base de datos
            this.username = username;
            this.pass = password;
            Toast.makeText(this, "Has sido registrado! Inicia Sesión para entrar.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El nombre de usuario ya está en uso!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isUsernameValid(){
        //verificar si el usuario no esta repetido en la bd
        return true;
    }
}