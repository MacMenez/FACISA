package com.example.appaspi.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appaspi.R;
import com.example.appaspi.models.Atendimento;
import com.example.appaspi.controller.ListaFuncionarios;
import com.example.appaspi.controller.ListaPaciente;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.P)
public class Fazer_Anotacoes_Activity extends AppCompatActivity {

    private List<String> listaEncontrase = Arrays.asList("Repouso","Acamado","Dormindo","Irritado","Alegre","Ansioso");
    private List<String> listaMedicamento = Arrays.asList("Omeprazol 1g", "Tylex 30 mg", "Soro","Adivíl");
    private ListaFuncionarios funcionarios = new ListaFuncionarios();
    private ListaPaciente pacientes = new ListaPaciente();
    private EditText observacao;
    protected ListView listViewEncontrase;
    protected ListView listaViewMedicamento;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fazer_anotacoes);
        setTitle("Preencher do Formulário");
        observacao = findViewById(R.id.editText_observacao);
        listViewEncontrase = findViewById(R.id.list_encontrase);
        listaViewMedicamento = findViewById(R.id.list_medicamento);
        listViewEncontrase.setAdapter(adapter(listaEncontrase));
        listaViewMedicamento.setAdapter(adapter(listaMedicamento));

    }

    public void salvar(View view){

            //Criando informações do atendimento
            Atendimento atendimento = new Atendimento(funcionarios.getFuncionario(Lista_de_Atendimentos_Activity.matricula),pacientes.getPaciente(002),new Date());

            //Atribuindo valores das Views ao objeto de atendimento
            Log.i("valor","tem valor");
            atendimento.setListaViewMedicamento(listaViewMedicamento);
            atendimento.setListViewEncontrase(listViewEncontrase);
            atendimento.setObservacao(observacao);



            //Adicionando objeto atendimento a lista de atendimentos
            Lista_de_Atendimentos_Activity.listaAtendimento.addAtendimento(atendimento);

            //Migrando de activoty
            Intent intent = new Intent(Fazer_Anotacoes_Activity.this, Lista_de_Atendimentos_Activity.class);
           // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }


    public ArrayAdapter<String> adapter(List<String> lista){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,lista);
        return adapter;
    }

}