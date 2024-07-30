package com.locadora.locadoraapi.exception;

public class VeiculoNaoAlugado extends RuntimeException {
    public VeiculoNaoAlugado() {
        super("Veiculo não alugado");
    }
}
