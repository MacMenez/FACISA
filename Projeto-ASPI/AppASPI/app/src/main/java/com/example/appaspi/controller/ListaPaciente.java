package com.example.appaspi.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.appaspi.models.Paciente;

import java.util.ArrayList;
import java.util.List;

public class ListaPaciente {

    private List<Paciente> pacienteList = new ArrayList<>();

    public ListaPaciente(){
        pacienteList.add(new Paciente(0001,"Roberto Nunes",1));
        pacienteList.add(new Paciente(0002,"Henrique Macedo",1));
        pacienteList.add(new Paciente(0003,"Pedro Lucas",2));
        pacienteList.add(new Paciente(0004,"Joana Souza",3));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Paciente getPaciente(int id){

        for(int i = 0; i < pacienteList.size(); i ++){

            if(pacienteList.get(i).getId() == id){
                return pacienteList.get(i);
            }
        }
        return null;
    }



}
