package com.locadora.locadoraapi.exception;

public class VeiculoAlugado extends RuntimeException {
    public VeiculoAlugado() {
        super("Veiculo já alugado");
    }
}
