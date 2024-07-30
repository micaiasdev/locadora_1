package com.locadora.locadoraapi.exception;

public class VeiculoNaoCadastrado  extends RuntimeException{
    public VeiculoNaoCadastrado() {
        super("Veículo não cadastrado");
    }   

}
