package com.example.appaspi.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appaspi.R;
import com.example.appaspi.models.Atendimento;


@RequiresApi(api = Build.VERSION_CODES.P)
public class MeusAtendimentos_Activity extends AppCompatActivity implements Lista_de_Atendimentos_Adapter.RecycleViewOnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private RecyclerView.LayoutManager recycleManager;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meusatendimentos);
        getSupportActionBar().setTitle("Meus atendimentos");
        configuraLista();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void configuraLista(){
        //Carregando view RecyclerView
        recyclerView = findViewById(R.id.recycler_meus_atendimentos);
        recyclerView.setHasFixedSize(true);
        recycleManager = new LinearLayoutManager(this);

        //Valores do parâmetros: Objeto do tipo Atendimento e o contexto da classe ao objeto da interface.
        recycleAdapter = new Lista_de_Atendimentos_Adapter(Lista_de_Atendimentos_Activity.listaAtendimento.getByListFuncionario(LoginActivity.matricula), this,2);
        recyclerView.setLayoutManager(recycleManager);
        recyclerView.setAdapter(recycleAdapter);

    }


    @Override
    public void itemSelecionado(Atendimento objeto, int posicao, View view) {

    }
}
