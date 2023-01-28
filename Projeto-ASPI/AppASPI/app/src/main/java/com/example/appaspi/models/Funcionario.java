package com.example.appaspi.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.List;

public class Funcionario implements Parcelable {
    private int matricula;
    private Cargos cargo;
    private String nome;
    private List<Atendimento> atendimentos;

    public Funcionario(String nome, int matricula, Cargos cargo){
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    protected Funcionario(Parcel in) {
        matricula = in.readInt();
        cargo = in.readParcelable(Cargos.class.getClassLoader());
        nome = in.readString();
        atendimentos = in.createTypedArrayList(Atendimento.CREATOR);
    }

    public static final Creator<Funcionario> CREATOR = new Creator<Funcionario>() {
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public Funcionario createFromParcel(Parcel in) {
            return new Funcionario(in);
        }

        @Override
        public Funcionario[] newArray(int size) {
            return new Funcionario[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(matricula);
        dest.writeParcelable(cargo, flags);
        dest.writeString(nome);
        dest.writeTypedList(atendimentos);
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo.getCargo();
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setAtendimentos(Atendimento atendimento){
        this.atendimentos.add(atendimento);
    }
    public List<Atendimento> getAtendimentos(){return atendimentos;}
}
