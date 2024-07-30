package com.locadora.locadoraapi.model;

import com.locadora.locadoraapi.helpers.PorcentagemSeguro;
import com.locadora.locadoraapi.helpers.TipoCarroEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Carro  extends Veiculo { 
    
    @Enumerated
    private TipoCarroEnum tipo;


    public Carro(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria, String placa, TipoCarroEnum tipo) {
        super(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa);
        this.tipo = tipo;
    }

    public  Carro() {
    }

    @Override
    public double seguro() {
        return (getValorDoBem()* PorcentagemSeguro.CARRO)/365;
    }
    
}
