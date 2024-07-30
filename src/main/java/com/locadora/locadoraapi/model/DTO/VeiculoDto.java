package com.locadora.locadoraapi.model.DTO;

import com.locadora.locadoraapi.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;
import com.locadora.locadoraapi.model.Veiculo;

public record VeiculoDto(   String marca, 
                            String modelo,
                            Integer anoDeFabricacao,
                            Double valorDoBem,
                            Double valorDiaria,
                            String placa,
                            Integer capacidadePassageiros,
                            Integer cilindrada,
                            Integer carga,
                            TipoCarroEnum tipo,
                            Double valorDiariaAnterior
                        ) { 
    
    public Veiculo toVeiculo() {
        int count = 0;
        if (capacidadePassageiros != null) count++;
        if (cilindrada != null) count++;
        if (carga != null) count++;
        if (tipo != null) count++;

        if (count > 1) {
            throw new IllegalArgumentException("Apenas um tipo de veículo deve ser informado: capacidadePassageiros / cilindrada / carga / tipo.");
        }
        if(capacidadePassageiros != null) return new Onibus(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, capacidadePassageiros);
        if(cilindrada != null) return new Moto(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, cilindrada);   
        if(carga != null) return new Caminhao(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, carga); 
        if(tipo != null) return new Carro(marca, modelo, anoDeFabricacao, valorDoBem, valorDiaria, placa, tipo);
        return null; 
    
    }
}


