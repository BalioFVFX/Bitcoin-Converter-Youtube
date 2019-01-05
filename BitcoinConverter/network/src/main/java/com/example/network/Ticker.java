package com.example.network;

import com.google.gson.annotations.SerializedName;

public class Ticker {
    @SerializedName(value = "ticker")
    private Bitcoin bitcoin;


    public Ticker() {
    }

    public Bitcoin getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(Bitcoin bitcoin) {
        this.bitcoin = bitcoin;
    }


}
