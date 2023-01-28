package com.example.appaspi.controller;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.appaspi.models.Cargos;
import com.example.appaspi.models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListaFuncionarios implements Parcelable {

    List<Funcionario> funcionariosList = new ArrayList<>();

    public ListaFuncionarios(){
        funcionariosList.add(new Funcionario("Maria Souza Carla",1234,new Cargos("Enfermeiro")));
        funcionariosList.add(new Funcionario("João Carlos",1235,new Cargos("Enfermeiro")));
        funcionariosList.add(new Funcionario("Pedro Paulo Santos",5734,new Cargos("Enfermeiro")));
        funcionariosList.add(new Funcionario("Vitor Vielela ",4567,new Cargos("Enfermeiro")));
        funcionariosList.add(new Funcionario("Henrique Pedro Barbosa",7894,new Cargos("Enfermeiro")));
        funcionariosList.add(new Funcionario("Karla Souza ",4563,new Cargos("Enfermeiro")));
    }


    protected ListaFuncionarios(Parcel in) {
        funcionariosList = in.createTypedArrayList(Funcionario.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(funcionariosList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListaFuncionarios> CREATOR = new Creator<ListaFuncionarios>() {
        @Override
        public ListaFuncionarios createFromParcel(Parcel in) {
            return new ListaFuncionarios(in);
        }

        @Override
        public ListaFuncionarios[] newArray(int size) {
            return new ListaFuncionarios[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Boolean verifyFuncionario(final int matricula){

        if(funcionariosList.stream().filter(x -> x.getMatricula() == matricula).count() > 0){
            return true;
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Funcionario getFuncionario(final int matricula){


        for( int i = 0 ; i < funcionariosList.size(); i ++){
            if(funcionariosList.get(i).getMatricula() == matricula){
                return funcionariosList.get(i);
            }
        }
        return null;
    }


}
