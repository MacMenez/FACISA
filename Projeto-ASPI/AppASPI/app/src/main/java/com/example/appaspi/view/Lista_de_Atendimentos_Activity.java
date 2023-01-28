package com.example.appaspi.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appaspi.R;
import com.example.appaspi.models.Atendimento;
import com.example.appaspi.controller.ListaAtendimento;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.P)
public class Lista_de_Atendimentos_Activity extends AppCompatActivity implements Lista_de_Atendimentos_Adapter.RecycleViewOnClickListener {
    public static ListaAtendimento listaAtendimento = new ListaAtendimento();
    private EditText editText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;
    private RecyclerView.LayoutManager recycleManager;
    public static int matricula;
    public  static int posicao;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_atendimentos);
        getSupportActionBar().setTitle("Lista de Formulários");

        //Capturando dados enviados do LoginActivity, que no caso é o login do profissional
        Intent capturarIntent = getIntent();
        Bundle capturandoExtras = capturarIntent.getExtras();
        matricula = LoginActivity.matricula;

        configuraLista();

    }


    public void configuraLista(){
        //Carregando view RecyclerView
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recycleManager = new LinearLayoutManager(this);

        //Valores do parâmetros: Objeto do tipo Atendimento e o contexto da classe ao objeto da interface.
        recycleAdapter = new Lista_de_Atendimentos_Adapter(listaAtendimento.getAtendimentoId(002),this,1);
        recyclerView.setLayoutManager(recycleManager);
        recyclerView.setAdapter(recycleAdapter);

    }

    public void addAtendimento(View view){

        //Evento de transferencia de activity ao clicar no botão
        Intent intent = new Intent(Lista_de_Atendimentos_Activity.this, Fazer_Anotacoes_Activity.class);
        startActivity(intent);
    }

    @Override
    public void itemSelecionado(Atendimento atendimento, int posicao, View view) {
        Intent intent = new Intent();
        intent.putExtra("atendimento",atendimento);
        //pegando lista de atendimento de um paciente
        //pegando um atendimento de acordo com sua posição
        //setando uma intent dentro deste atendimento
        listaAtendimento.getAtendimentoId(002).get(posicao).setIntent(intent);
        Lista_de_Atendimentos_Activity.posicao = posicao;
        startActivity(new Intent(Lista_de_Atendimentos_Activity.this,Visualizar_Anotacoes_Activity.class));
    }

}

class Lista_de_Atendimentos_Adapter extends RecyclerView.Adapter<Lista_de_Atendimentos_Adapter.AtendimentoViewHolder> {
    private List<Atendimento> listaAtendimento;
    RecycleViewOnClickListener listener;
     private int tipo;

    public Lista_de_Atendimentos_Adapter(List<Atendimento> listaAtendimento, RecycleViewOnClickListener listener, int tipo) {
        this.listaAtendimento = listaAtendimento;
        this.listener = listener;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public AtendimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      //Inflando todos das as views do layout de itens a atribuindo a um objeto do tipo view, pesteriormente este objeto
      //será passando como parametro da classe AtedimentoViewHolder que será instanciada dentro deste método.
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens, parent, false);

        return new AtendimentoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itens, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull AtendimentoViewHolder holder, int position) {
        //O objeto hoder do tipo viewHolder tem todos os elementos que foram inflados através do método onCreateViewHolder
        //e será através do mesmo que outras views poderam ser instanciadas para atribuição de valor.

        Atendimento atendimento = this.listaAtendimento.get(position);

        //Atribuindo valores as views da classe viewHolder
        if(tipo == 1) {
            holder.nome.setText(atendimento.getFuncionario().getNome());
            holder.funcao.setText(atendimento.getFuncionario().getCargo());
            holder.data.setText(atendimento.getData());
            holder.hora.setText(atendimento.getHora());
        }
        else if(tipo == 2) {
            holder.nome.setText(atendimento.getPaciente().getNome());
            holder.cargo.setText("Status:");
            holder.funcao.setText("Paciente");
            holder.data.setText(atendimento.getData());
            holder.hora.setText(atendimento.getHora());
        }
    }

    //Quantidade de elementos a ser listados
    @Override
    public int getItemCount() {
        return listaAtendimento.size();
    }

    //Criando uma interface para eventos de click e determinando os atributos que quero capturar após o click.
    public interface RecycleViewOnClickListener {
        void itemSelecionado(Atendimento objeto, int posicao, View view);
    }

    class AtendimentoViewHolder extends RecyclerView.ViewHolder{

        //As views serão atribuidas na classe ViewHolder
        protected TextView nome;
        protected TextView funcao;
        protected TextView data;
        protected TextView hora;
        protected TextView cargo;

        //Construtor
        public AtendimentoViewHolder(@NonNull View itemView) {
            super(itemView);

            //Atribuindo aos atributos do tipo view da classe interna
            nome = itemView.findViewById(R.id.nome);
            funcao = itemView.findViewById(R.id.funcao);
            data = itemView.findViewById(R.id.data);
            hora = itemView.findViewById(R.id.hora);
            cargo = itemView.findViewById(R.id.cargo);
            //Chamando um evento de clique.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Carregando valores aos atributos dos parâmetros do método da interface de evento de clicks (objeto,posicao,view)
                    listener.itemSelecionado(listaAtendimento.get(getAdapterPosition()),getAdapterPosition(),itemView);
                }
            });


        }


    }
}
