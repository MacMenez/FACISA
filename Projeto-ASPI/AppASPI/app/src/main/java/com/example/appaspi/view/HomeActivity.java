package com.example.appaspi.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.appaspi.R;
import com.example.appaspi.controller.ListaFuncionarios;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Button button;
    private  Activity activity;
    private int idPaciente = 002;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_menu);
        configuraNavigationView();

        button = findViewById(R.id.btqr);
        activity = this;
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void configuraNavigationView(){
        //Obtendo acesso as funcionalidades do navigationView
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Adicionado um headerView e inflando as views
        View view = navigationView.inflateHeaderView(R.layout.header_menu_activity);

        //Atribuindo valores as views do header
        TextView nome = view.findViewById(R.id.nome_menu);
        TextView matricula = view.findViewById(R.id.matricula_menu);
        TextView cargo = view.findViewById(R.id.cargo_menu);
        nome.setText(new ListaFuncionarios().getFuncionario(LoginActivity.matricula).getNome());
        cargo.setText(new ListaFuncionarios().getFuncionario(LoginActivity.matricula).getCargo());
        matricula.setText(String.valueOf(new ListaFuncionarios().getFuncionario(LoginActivity.matricula).getMatricula()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.tooblar_menu,menu);
        return true;
    }

    public void onSair(MenuItem menuItem){
        onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    public void onMeusAtendimentos(MenuItem item){
        startActivity(new Intent(HomeActivity.this,MeusAtendimentos_Activity.class));
    }

    public void abrirCamera(View view){
        startActivity(new Intent(HomeActivity.this, Lista_de_Atendimentos_Activity.class));
        /*
        IntentIntegrator integrator = new IntentIntegrator((activity));
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Capturando dados do paciente");
        integrator.setCameraId(0);
        integrator.initiateScan();
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null && result.getContents() != null){

            //capturou algo
            // idPaciente = Integer.parseInt(result.getContents());

            startActivity(new Intent(HomeActivity.this, Lista_de_Atendimentos_Activity.class)); //Migrando para AtendimentosActivity
        }
        else {

            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
