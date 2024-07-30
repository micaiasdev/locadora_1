package com.locadora.locadoraapi.model;

import com.locadora.locadoraapi.helpers.PorcentagemSeguro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Moto extends Veiculo {
    @Column   
    private int cilindrada;

    public Moto(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, int cilindrada) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.cilindrada = cilindrada;
    }

    public Moto() {
    }

    @Override
    public double seguro() {
        return (getValorDoBem()*PorcentagemSeguro.MOTO)/365;
    }
    
}
