package com.locadora.locadoraapi.helpers;

import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;

public class TipoVeiculoClassHelper {

    public static Class<?>  getTipoVeiculoClass(int tipoVeiculo) {
        switch (tipoVeiculo) {
            case 1:
                return Moto.class;
            case 2:
                return Carro.class;
            case 3:
                return Caminhao.class;
            case 4:
                return Onibus.class;
            default:
                return null;
        }
    }
    
}
