package com.example.appaspi.models;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.P)
public class Atendimento  implements Parcelable
{
    private Funcionario funcionario;
    private Date data;
    private DateFormat dateFormat = DateFormat.getDateInstance();
    private DateFormat horaFormat = DateFormat.getTimeInstance();
    private Paciente paciente;
    private EditText observacao;
    private ListView listViewEncontrase;
    private ListView listaViewMedicamento;
    private Intent intent;
    public Atendimento(Funcionario funcionarios, Paciente paciente, Date data) {
        super();
        this.funcionario = funcionarios;
        this.data = data;
        this.paciente = paciente;
    }

    public Intent getIntent(){
        return intent;
    }
    public void setIntent(Intent intent){this.intent = intent;}
    protected Atendimento(Parcel in) {
        funcionario = in.readParcelable(Funcionario.class.getClassLoader());
        paciente = in.readParcelable(Paciente.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(funcionario, flags);
        dest.writeParcelable(paciente, flags);
    }

    public static final Creator<Atendimento> CREATOR = new Creator<Atendimento>() {
        @Override
        public Atendimento createFromParcel(Parcel in) {
            return new Atendimento(in);
        }

        @Override
        public Atendimento[] newArray(int size) {
            return new Atendimento[size];
        }
    };

    public String getData() { return dateFormat.format(data); }

    public Paciente getPaciente() {
        return paciente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public String getHora(){
        return horaFormat.format(data);
      }

     public ListView getListaViewMedicamento() {
        return listaViewMedicamento;
    }

    public ListView getListViewEncontrase() { return this.listViewEncontrase; }

    public EditText getObservacao() {
        return observacao;
    }

    public void setListaViewMedicamento(ListView listaViewMedicamento) { if(listaViewMedicamento != null){

        this.listaViewMedicamento = listaViewMedicamento;
        }
    }

    public void setListViewEncontrase(ListView listViewEncontrase) { if (listViewEncontrase != null ){
        this.listViewEncontrase = listViewEncontrase;
    }}

    public void setObservacao(EditText observacao) {
        this.observacao = observacao;
    }


}


