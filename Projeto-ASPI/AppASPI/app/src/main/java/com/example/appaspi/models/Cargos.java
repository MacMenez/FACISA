package com.example.appaspi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class  Cargos implements Parcelable {
    private String cargo;

    public Cargos(String cargo){
        this.cargo = cargo;
    }

    public Cargos(Parcel in) {
        cargo = in.readString();
    }

    public static final Creator<Cargos> CREATOR = new Creator<Cargos>() {
        @Override
        public Cargos createFromParcel(Parcel in) {
            return new Cargos(in);
        }

        @Override
        public Cargos[] newArray(int size) {
            return new Cargos[size];
        }
    };

    public String getCargo() {
        return cargo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cargo);
    }
}
