package com.locadora.locadoraapi.service;

import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository repositorio;


    public Cliente inserir(Cliente  c) {

        if( repositorio.existsByCpf(c.getCpf()) )
            throw new ClienteJaCadastrado();
        
        return repositorio.save(c);
    }


    
}
