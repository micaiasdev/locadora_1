package com.locadora.locadoraapi.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locadora.locadoraapi.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

        boolean existsByCpf(Long cpf);
        Cliente findByCpf(Long cpf);
        ArrayList<Cliente> findByNomeIgnoreCase(String nome);
 
    
}
