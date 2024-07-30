package com.locadora.locadoraapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.locadoraapi.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.model.Caminhao;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Moto;
import com.locadora.locadoraapi.model.Onibus;
import com.locadora.locadoraapi.model.Veiculo;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

    Veiculo findByPlaca(String placa);
    boolean existsByPlaca(String placa);
    List<Moto> findByCilindrada(int cilindrada);
    List<Carro> findByTipo(TipoCarroEnum tipoCarro);
    List<Caminhao> findByCarga(int carga);
    List<Onibus>  findByCapacidadePassageiros(int passageiros);
    @Query(value ="SELECT v FROM Veiculo v where v.class =?1")
    List<Veiculo> findAllVeiculosByType(Class<?> classe);
    //verifica se um veículo está alugado
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aluguel a WHERE a.veiculo.placa = :placa AND a.baixo = false AND a.dataDevolucaoReal IS NULL")
    boolean isAlugado(@Param("placa") String placa);
    
}
