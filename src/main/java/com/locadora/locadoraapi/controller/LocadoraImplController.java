package com.locadora.locadoraapi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.TaxaAumentoForaIntervalo;
import com.locadora.locadoraapi.exception.TipoVeiculoDesconhecido;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.helpers.VeiculoConverter;
import com.locadora.locadoraapi.model.Aluguel;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.model.DTO.AluguelDto;
import com.locadora.locadoraapi.model.DTO.ClienteDto;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;
import com.locadora.locadoraapi.service.ClienteService;
import com.locadora.locadoraapi.service.VeiculoService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class LocadoraImplController implements Locadora {
    private VeiculoService veiculoService;
    private ClienteService clienteService;

    @Override
    public ResponseEntity<?> inserir(@RequestBody VeiculoDto veiculoDto) throws VeiculoJaCadastrado, SQLException {
        var veiculo = this.veiculoService.inserir(veiculoDto.toVeiculo());
        Object veiculoResponse = VeiculoConverter.toDto(veiculo);
        return ResponseEntity.ok ( veiculoResponse );
    }

    @Override
    public ResponseEntity<ClienteDto> inserir(@RequestBody  ClienteDto clienteDto) throws ClienteJaCadastrado, SQLException {
        var cliente = this.clienteService.inserir(clienteDto.toCliente());
        return ResponseEntity.ok ( clienteDto.toDto(cliente) );
    }

    @Override
    public ResponseEntity<?> pesquisar(String placa) throws VeiculoNaoCadastrado {
        Veiculo  veiculo = Optional.ofNullable( this.veiculoService.getVeiculoByPlaca(placa) )
            .orElseThrow( () -> new VeiculoNaoCadastrado() );
        
        Object veiculoResponse = VeiculoConverter.toDto(veiculo);
        return ResponseEntity.ok ( veiculoResponse  );
    }

    @Override
    public ResponseEntity< ArrayList<?> > pesquisarMoto(int cilindrada) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByCilindrada(cilindrada) );
        ArrayList<Object> veiculosResponse = new ArrayList<Object>();
        veiculos.forEach(v -> veiculosResponse.add(VeiculoConverter.toDto(v))); 
        
        return ResponseEntity.ok (veiculosResponse );

    }

    @Override
    public ResponseEntity< ArrayList<?> > pesquisarCarro(int tipoCarro) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByTipoCarro(tipoCarro) );
        ArrayList<Object> veiculosResponse = new ArrayList<Object>();
        veiculos.forEach(v -> veiculosResponse.add(VeiculoConverter.toDto(v))); 
        
        return ResponseEntity.ok (veiculosResponse );
    }

    @Override
    public ResponseEntity< ArrayList<?> > pesquisarCaminhao(int carga) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByCarga(carga) );
        ArrayList<Object> veiculosResponse = new ArrayList<Object>();
        veiculos.forEach(v -> veiculosResponse.add(VeiculoConverter.toDto(v))); 
        
        return ResponseEntity.ok (veiculosResponse );
    }

    @Override
    public ResponseEntity<ArrayList<?>> pesquisarOnibus(int passageiros) {
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
        veiculos.addAll( this.veiculoService.getVeiculoByPassageiros(passageiros) );
        ArrayList<Object> veiculosResponse = new ArrayList<Object>();
        veiculos.forEach(v -> veiculosResponse.add(VeiculoConverter.toDto(v))); 
        
        return ResponseEntity.ok (veiculosResponse );
    }

    @Override
    public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
       return this.veiculoService.getValorTotalAluguel(placa, dias);
    }

    @Override
    public ResponseEntity<AluguelDto> registrarAluguel(String placa, Date dataInicio, int dias, Long cpf)
            throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
        
        Aluguel  aluguel = this.veiculoService.registrarAluguel(placa, dataInicio, dias, cpf);
        AluguelDto aluguelDto = new AluguelDto(aluguel.getVeiculo().getPlaca(), aluguel.getCliente().getCpf(), aluguel.getCliente().getNome(), aluguel.getDataInicio(), aluguel.getDataFim(), aluguel.getTotalAluguel());
        return ResponseEntity.ok(aluguelDto);
    }

    @Override
    public ResponseEntity<AluguelDto> registrarDevolucao(String placa, Date data, Long cpf)
            throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado {
       Aluguel aluguel =  this.veiculoService.registrarDevolucao(placa, data, cpf);
       return ResponseEntity.ok(new AluguelDto(aluguel.getVeiculo().getPlaca(), aluguel.getCliente().getCpf(), aluguel.getCliente().getNome(), aluguel.getDataInicio(), aluguel.getDataFim(), aluguel.getTotalAluguel()));
    }

    @Override
    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
        this.veiculoService.depreciarVeiculos(tipo,taxaDepreciacao);
    }

    @Override
    public ResponseEntity<Object> aumentarDiaria(int tipo, double taxaAumento) throws TaxaAumentoForaIntervalo, TipoVeiculoDesconhecido{
        List<Veiculo> veiculoAlterados = this.veiculoService.aumentarDiaria(tipo,taxaAumento);
        var retorno = veiculoAlterados.stream()
            .map( (veiculo) -> VeiculoConverter.toDto(veiculo) )
            .toList();
            
        return ResponseEntity.ok(retorno);
    }

    @Override
    public double faturamentoTotal(int tipo, Date inicio, Date fim) {
        if ( tipo < 0 || tipo > 4)
            throw new IllegalArgumentException("Tipo deve ser entre 0 e 4");
        return this.veiculoService.faturamentoTotal(tipo, inicio, fim);

    }

    @Override
    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
        if ( tipo < 0 || tipo > 4)
            throw new IllegalArgumentException("Tipo deve ser entre 0 e 4");
        return this.veiculoService.quantidadeTotalDeDiarias(tipo, inicio, fim);
        
    }
    
}
