package com.locadora.locadoraapi.model;

import com.locadora.locadoraapi.helpers.PorcentagemSeguro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Onibus extends Veiculo {
    @Column   
    private int capacidadePassageiros;

    public Onibus(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, int capacidadePassageiros) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.capacidadePassageiros = capacidadePassageiros;
    }

    public Onibus() {
    }

    @Override
    public double seguro() {
        return (getValorDoBem()*PorcentagemSeguro.ONIBUS)/365;
    }

    
}
