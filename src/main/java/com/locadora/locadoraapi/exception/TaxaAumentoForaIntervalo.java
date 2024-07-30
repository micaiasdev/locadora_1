package com.locadora.locadoraapi.exception;

public class TaxaAumentoForaIntervalo  extends RuntimeException{
    public TaxaAumentoForaIntervalo() {
        super("Taxa de Aumento fora do intervalo de 0 a 100%");
    }   

}
