package com.locadora.locadoraapi.model.DTO;

import com.locadora.locadoraapi.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(    
    @NotBlank String nome,
    @NotNull Long cpf,
    String telefone,
    String email
) {

    public Cliente toCliente() {
        return new Cliente(this.nome, this.cpf, this.telefone, this.email);
    }

    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail());
    }
}