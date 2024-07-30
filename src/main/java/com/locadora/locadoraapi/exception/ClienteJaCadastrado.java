package com.locadora.locadoraapi.exception;

public class ClienteJaCadastrado extends RuntimeException{
    public ClienteJaCadastrado() {
        super("Cliente já cadastrado");
    }
}
