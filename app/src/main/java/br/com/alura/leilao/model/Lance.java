package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Lance implements Serializable, Comparable {

    private final Usuario usuario;
    private final double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }


    //compara dois valores e tem como retorno 3 valores :
    // -1 para quando o valor do objeto for menor
    // 0 para quando o valor do objeto for igual
    // 1 para quando  valor do objeto for maior
    @Override
    public int compareTo(@NonNull Object o) {
        Lance lance = (Lance) o;
        if (valor > lance.getValor()) {
            return -1;
        }
        if (valor < lance.getValor()) {
            return 1;
        }
        return 0;
    }

    public Usuario getUsuario() {
        return usuario;
    }


}
