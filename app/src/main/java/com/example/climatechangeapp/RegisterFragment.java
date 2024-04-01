package com.example.climatechangeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

public class RegisterFragment extends DialogFragment {
    //Definir interfaz
    public interface OnSubmitListener{
        void onSubmit(String username, String password);
    }

    //Declarar widgets
    private OnSubmitListener onSubmitListener;
    private EditText username;
    private EditText pass;
    private EditText passConfirm;
    private Button register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View view = inflater.inflate(R.layout.register_layout, container, false);

        //Definir los widgets
        username = view.findViewById(R.id.usernameFrag);
        pass = view.findViewById(R.id.passFrag);
        passConfirm = view.findViewById(R.id.confirmPassFrag);
        register = view.findViewById(R.id.registerBtnFrag);

        register.setOnClickListener(view1 -> onRegisterSubmit());

        return view;
    }

    private void onRegisterSubmit(){
        if(onSubmitListener != null){
            if (!checkRegErrors()){
                onSubmitListener.onSubmit(username.getText().toString(), pass.getText().toString());
                dismiss();
            }
        }
    }

    private boolean checkRegErrors(){
        boolean error = false;
        if(!pass.getText().toString().equals(passConfirm.getText().toString())){
            error = true;
            Toast.makeText(getActivity(), "Las contraseñas no son iguales!", Toast.LENGTH_SHORT).show();
        }
        if(username.getText().toString().equals("") || pass.getText().toString().equals("") || passConfirm.getText().toString().equals("")){
            error = true;
            Toast.makeText(getActivity(), "Hay campos vacios, verificalos!", Toast.LENGTH_SHORT).show();
        }
        return error;
    }

    public void setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }
}
