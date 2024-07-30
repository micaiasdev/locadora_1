package com.locadora.locadoraapi.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(columnDefinition = "BIGINT")
    private Long cpf;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Aluguel> alugueis;

    public Cliente() {
    }

    //criar construtor com todos os atributos
    public Cliente(String nome, Long cpf,  String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

}
