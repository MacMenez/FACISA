package com.example.appaspi.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appaspi.R;
import com.example.appaspi.models.Atendimento;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Visualizar_Anotacoes_Activity extends AppCompatActivity {

    private TextView nome;
    private TextView idade;
    private TextView classificacao;
    private ListView listViewEncontrase;
    private ListView listaViewMedicamento;
    private EditText observacao;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visualizar_anotacoes);

        setTitle("Visualizar Formulário");
        this.listViewEncontrase = findViewById(R.id.visualizar_estado);
        this.listaViewMedicamento = findViewById(R.id.visualizar_medicacao);
        this.observacao = findViewById(R.id.visualizar_observacao);
        this.nome = findViewById(R.id.visualizar_informacoes_input_nome_do_paciente);
        this.idade = findViewById(R.id.visualizar_informacoes_input_idade_paciente);
        this.classificacao = findViewById(R.id.visualizar_informacoes_input_classificacao_atendimento);

        Intent intent = Lista_de_Atendimentos_Activity.listaAtendimento.getAtendimentoId(002).get(Lista_de_Atendimentos_Activity.posicao).getIntent();

        if(intent.getParcelableExtra("atendimento") != null) {
            Atendimento atendimento = intent.getParcelableExtra("atendimento");

            this.nome.setText(atendimento.getPaciente().getNome());
            this.listViewEncontrase.setAdapter(atendimento.getListViewEncontrase().getAdapter());
            this.listaViewMedicamento.setAdapter(atendimento.getListaViewMedicamento().getAdapter());

            for(int i = 0 ; i < atendimento.getListViewEncontrase().getCount(); i++) {
                this.listViewEncontrase.setItemChecked(i, atendimento.getListViewEncontrase().isItemChecked(i));
            }

            for(int i = 0; i < atendimento.getListaViewMedicamento().getCount(); i++){
                this.listaViewMedicamento.setItemChecked(i, atendimento.getListaViewMedicamento().isItemChecked(i));
            }

            this.observacao.setText(atendimento.getObservacao().getText());
        }


        this.listaViewMedicamento.setEnabled(false);
        this.listViewEncontrase.setEnabled(false);
        this.observacao.setEnabled(false);

    }
}
