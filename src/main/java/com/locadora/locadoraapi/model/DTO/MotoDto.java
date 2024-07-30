package com.locadora.locadoraapi.model.DTO;

public record MotoDto(String marca, String modelo, Integer anoDeFabricacao, Double valorDoBem, 
Double valorDiaria, String placa, Integer cilindrada,
Double valorDiarioAntigo) {}