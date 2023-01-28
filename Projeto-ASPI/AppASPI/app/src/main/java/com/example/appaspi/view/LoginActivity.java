package com.example.appaspi.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.appaspi.R;
import com.example.appaspi.controller.ListaFuncionarios;
import com.example.appaspi.controller.ListaPaciente;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LoginActivity extends AppCompatActivity {
    static ListaFuncionarios funcionarios = new ListaFuncionarios();
    static ListaPaciente pacientes = new ListaPaciente();
    public static int matricula = 1234;
    public EditText textMatricula;
    public Button btlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        textMatricula = findViewById(R.id.matriculalogin);

        Editable text = textMatricula.getText();
        String matriculastr = text.toString();
        this.matricula = 1234;
    }

    public void onLogin(View view){

        //Validação do usuário
        if(funcionarios.verifyFuncionario(matricula) == true){

            Intent dados = new Intent(LoginActivity.this, HomeActivity.class);
            dados.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            dados.putExtra("matricula",matricula);
            startActivity(dados);
        }
        else{
            Toast.makeText(LoginActivity.this,"Matricula não identificada",Toast.LENGTH_LONG).show();
        }

    }

}


