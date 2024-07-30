package com.locadora.locadoraapi.model.DTO;

import com.locadora.locadoraapi.helpers.TipoCarroEnum;

public record CarroDto(String marca, 
String modelo,
Integer anoDeFabricacao, 
Double valorDoBem, 
Double valorDiaria, 
String placa, 
TipoCarroEnum tipo,
Double valorDiariaAntigo) {}
