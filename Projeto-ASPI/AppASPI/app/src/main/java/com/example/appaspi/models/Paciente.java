package com.example.appaspi.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Paciente implements Parcelable {
    private int id;
    private String nome;
    private List<Atendimento> atendimentos;
    private int tipo;

    public Paciente(int id, String nome, int tipo){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    protected Paciente(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        atendimentos = in.createTypedArrayList(Atendimento.CREATOR);
        tipo = in.readInt();
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeTypedList(atendimentos);
        dest.writeInt(tipo);
    }

    public void setAtendimentos(Atendimento atendimentos) {
        this.atendimentos.add(atendimentos);
    }

    public Atendimento getAtendimentos(int i) {
        return atendimentos.get(i);
    }


    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
