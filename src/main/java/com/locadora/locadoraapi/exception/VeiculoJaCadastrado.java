package com.locadora.locadoraapi.exception;


public class VeiculoJaCadastrado extends RuntimeException{
    
    public VeiculoJaCadastrado() {
        super("Veículo já cadastrado");
    }
    
}
