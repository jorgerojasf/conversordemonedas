package com.alura.conversordemonedas.modelos;

public class Moneda {
    String desde;
    String hacia;
    int valorIngresado;
    double valorHacia;

    public Moneda(String desde, String hacia, int valorIngresado, double valorHacia) {
        this.desde = desde;
        this.hacia = hacia;
        this.valorIngresado = valorIngresado;
        this.valorHacia = valorHacia;
    }

    @Override
    public String toString() {
        return "El valor de " + this.valorIngresado + "["+this.desde+"] corresponde al valor final de " + this.valorIngresado * this.valorHacia+ " ["+hacia+"]";
    }
}
