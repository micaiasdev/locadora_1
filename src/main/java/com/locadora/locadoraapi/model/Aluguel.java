package com.locadora.locadoraapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Aluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Veiculo veiculo;

    @Column
    private LocalDateTime dataInicio;
    @Column
    private LocalDateTime dataFim;
    @Column
    private LocalDateTime dataDevolucaoReal;
    @Column
    private Double totalAluguel;
    @Column(columnDefinition = "boolean default false")
    private boolean baixo = false;
    @Column(columnDefinition = "boolean default true")
    private boolean ativo = true;

    
    public Aluguel() {
    }

    public Aluguel(Cliente cliente, Veiculo veiculo, LocalDateTime dataInicio, LocalDateTime dataFim, Double totalAluguel) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.totalAluguel = totalAluguel;
    }


    public int getDias() {
        if (this.dataDevolucaoReal == null) {
            return 0;
        }
        return this.dataDevolucaoReal.getDayOfMonth() - this.dataInicio.getDayOfMonth();
    }
}
