package com.locadora.locadoraapi.helpers;

import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.CaminhaoDto;
import com.locadora.locadoraapi.model.DTO.CarroDto;
import com.locadora.locadoraapi.model.DTO.MotoDto;
import com.locadora.locadoraapi.model.DTO.OnibusDto;

public class VeiculoConverter {
    public static Object toDto(Veiculo veiculo) {

        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;
            return new CarroDto(carro.getMarca(), carro.getModelo(), carro.getAnoDeFabricacao(), carro.getValorDoBem(), carro.getValorDiaria(), carro.getPlaca(), carro.getTipo(), carro.getValorDiariaAntigo());
        } else if (veiculo instanceof Onibus) {
            Onibus onibus = (Onibus) veiculo;
            return new OnibusDto(onibus.getMarca(), onibus.getModelo(), onibus.getAnoDeFabricacao(), onibus.getValorDoBem(), onibus.getValorDiaria(), onibus.getPlaca(), onibus.getCapacidadePassageiros(), onibus.getValorDiariaAntigo());
        } else if (veiculo instanceof Moto) {
            Moto moto = (Moto) veiculo;
            return new MotoDto(moto.getMarca(), moto.getModelo(), moto.getAnoDeFabricacao(), moto.getValorDoBem(), moto.getValorDiaria(), moto.getPlaca(), moto.getCilindrada(), moto.getValorDiariaAntigo());
        } else if (veiculo instanceof Caminhao) {
            Caminhao caminhao = (Caminhao) veiculo;
            return new CaminhaoDto(caminhao.getMarca(), caminhao.getModelo(), caminhao.getAnoDeFabricacao(), caminhao.getValorDoBem(), caminhao.getValorDiaria(), caminhao.getPlaca(), caminhao.getCarga(), caminhao.getValorDiariaAntigo());
        }else {
            throw new IllegalArgumentException("Tipo de veículo desconhecido: " + veiculo.getClass().getName());
        }
    }
}