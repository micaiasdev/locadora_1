package com.locadora.locadoraapi.model;

import com.locadora.locadoraapi.helpers.PorcentagemSeguro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Caminhao extends Veiculo {
    @Column   
    private int carga;

    public Caminhao(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, int carga) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.carga = carga;
    }

    public  Caminhao() {
    }
    @Override
    public double seguro() {
        return (getValorDoBem()*PorcentagemSeguro.CAMINHAO)/365;
    }

}
