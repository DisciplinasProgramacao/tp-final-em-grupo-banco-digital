package Banco;

import java.io.Serializable;
import java.time.LocalDate;

public class Opercao implements Serializable {
    private LocalDate data;
    private String tipoOperacao;
    private double valor;



    public Opercao(String tipoOperacao, double valor) {
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.data = LocalDate.now();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("---------------------------------------- \n");
        sb.append("DATA: " + this.data + "\n");
        sb.append("TIPO: " + this.tipoOperacao + "\n");
        sb.append("DESCRIÇÃO: " + this.valor + "\n");
        return sb.toString();
    }
}

