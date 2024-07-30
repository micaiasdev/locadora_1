package com.locadora.locadoraapi.exception;


public class ClienteNaoCadastrado extends RuntimeException {
    public ClienteNaoCadastrado() {
        super("Cliente não cadastrado");
    }
}
