package com.locadora.locadoraapi.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locadora.locadoraapi.model.Aluguel;

public interface AluguelRepository  extends JpaRepository<Aluguel, Long>{
    //método que retorna um aluguel ativo a partir da placa do veículo
    Aluguel findFirstByVeiculoPlacaAndBaixoFalse(String placa);
    List<Aluguel> findByDataInicioBetweenAndDataDevolucaoRealNotNullAndBaixoIsTrue(LocalDateTime inicioLocalDateTime, LocalDateTime fimLocalDateTime);
    @Query(value ="SELECT a FROM Aluguel a join a.veiculo v  where a.dataInicio between ?1 and ?2  and v.class =?3")
    List<Aluguel> findByDataInicioBetweenAndVeiculoTipo(LocalDateTime inicioLocalDateTime, LocalDateTime fimLocalDateTime,
            Class<?> tipoVeiculoClass);
    List<Aluguel> findByDataInicioBetween(LocalDateTime inicioLocalDateTime, LocalDateTime fimLocalDateTime);
    
}
