package com.example.appaspi.controller;

import android.app.Activity;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.appaspi.models.Atendimento;
import com.example.appaspi.models.Funcionario;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ListaAtendimento  implements Parcelable{

    private List<Atendimento> atendimentoList = new ArrayList<>();
    private ListaFuncionarios listaFuncionarios = new ListaFuncionarios();
    private  ListaPaciente listaPaciente = new ListaPaciente();

    public ListaAtendimento(){
    }

    protected ListaAtendimento(Parcel in) {
        atendimentoList = in.createTypedArrayList(Atendimento.CREATOR);
    }

    public static final Creator<ListaAtendimento> CREATOR = new Creator<ListaAtendimento>() {
        @Override
        public ListaAtendimento createFromParcel(Parcel in) {
            return new ListaAtendimento(in);
        }

        @Override
        public ListaAtendimento[] newArray(int size) {
            return new ListaAtendimento[size];
        }
    };

    public List<Atendimento> getByListFuncionario(int matricula){
        List<Atendimento> listFuncionario = new ArrayList<>();

        for(Atendimento atendimento : this.atendimentoList){
            if(atendimento.getFuncionario().getMatricula() == matricula) {
                listFuncionario.add(atendimento);
            }
        }
        return listFuncionario;
    }

    public int qtdAtendimentosId(int id){
       return (int) atendimentoList.stream().filter(x-> x.getPaciente().getId() == id).count();
    }

    public void addAtendimento(Atendimento atendimento) {
        this.atendimentoList.add(atendimento);
    }

    public List<Atendimento> getAtendimentoId(int id) {

        List<Atendimento> list = new ArrayList<>();


        for(int i = 0 ; i < atendimentoList.size(); i ++){
            if(atendimentoList.get(i).getPaciente().getId() == id){

                list.add(atendimentoList.get(i));
            }
        }
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(atendimentoList);
    }
}