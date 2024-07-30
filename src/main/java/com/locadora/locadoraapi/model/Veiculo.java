package com.locadora.locadoraapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    protected String marca;
    @Column
    protected String modelo;
    @Column
    protected int anoDeFabricacao;
    @Column
    protected double valorDoBem;
    @Column
    protected double valorDiaria;
    @Column
    protected String placa;
    @Column
    protected Double valorDiariaAntigo;

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, int anoDeFabricacao, double valorDoBem, double valorDiaria,
            String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.valorDoBem = valorDoBem;
        this.valorDiaria = valorDiaria;
        this.placa = placa;
    }

    public abstract double seguro();

    // Retornar o valor total do aluguel do veículo
    public double aluguel(int dias) {
        return (this.valorDiaria + this.seguro()) * dias;
    }

    public void aumentarDiaria(double taxaAumento) {
        this.valorDiariaAntigo = this.valorDiaria;
        this.valorDiaria = this.valorDiaria + (this.valorDiaria * taxaAumento / 100);
    }

    public void depreciar(double taxaDepreciacao) {
        this.valorDoBem = this.valorDoBem - (this.valorDoBem * taxaDepreciacao / 100);
    }

}
