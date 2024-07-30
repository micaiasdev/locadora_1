package com.locadora.locadoraapi.exception;

public class TipoVeiculoDesconhecido  extends RuntimeException{
    public TipoVeiculoDesconhecido() {
        super("Valores aceitos para tipo de veiculo: 0 (todos os veículos), " + //
                        "- 1 (moto), 2 (carro), " + //
                        "- 3 (caminhão) " + //
                        "- 4 (ônibus)");
    }   

}
