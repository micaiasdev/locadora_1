package com.locadora.locadoraapi.model.DTO;

public record CaminhaoDto (String marca, 
String modelo,
Integer anoDeFabricacao, 
Double valorDoBem, 
Double valorDiaria, 
String placa, 
Integer carga,
Double valorDiarioAntigo) {}
