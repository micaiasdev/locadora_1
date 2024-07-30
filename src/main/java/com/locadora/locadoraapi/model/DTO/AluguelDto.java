package com.locadora.locadoraapi.model.DTO;

import java.time.LocalDateTime;

public record AluguelDto(String placaVeiculo, 
Long cpfCliente,
String nomeClinte,
LocalDateTime dataInicio,
LocalDateTime dataFim,
Double totalAluguel
) { }
