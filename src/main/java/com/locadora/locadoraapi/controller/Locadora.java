package com.locadora.locadoraapi.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.TaxaAumentoForaIntervalo;
import com.locadora.locadoraapi.exception.TipoVeiculoDesconhecido;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.DTO.AluguelDto;
import com.locadora.locadoraapi.model.DTO.ClienteDto;
import com.locadora.locadoraapi.model.DTO.VeiculoDto;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/locadora")
public interface Locadora {

    @PostMapping("/create/veiculo")
    public ResponseEntity<?> inserir(@RequestBody VeiculoDto v) throws VeiculoJaCadastrado, SQLException;

    @PostMapping("/create/cliente")
    public ResponseEntity<ClienteDto> inserir(@RequestBody ClienteDto c) throws ClienteJaCadastrado, SQLException;

    @GetMapping("/pesquisar/veiculo-placa/{placa}")
    public ResponseEntity<?> pesquisar(@PathVariable String placa) throws VeiculoNaoCadastrado;

    @GetMapping("/pesquisar/moto-por-cilindrada/{cilindrada}")
    public ResponseEntity<ArrayList<?>> pesquisarMoto(@PathVariable int cilindrada);

    // tipo de carro 1 (passeio), 2 (SUV), 3 (pickup)
    @GetMapping("/pesquisar/carro-pelo-tipo/{tipoCarro}")
    public ResponseEntity<ArrayList<?>> pesquisarCarro(@PathVariable int tipoCarro);

    @GetMapping("/pesquisar/caminhao-por-carga/{carga}")
    public ResponseEntity<ArrayList<?>> pesquisarCaminhao(@PathVariable int carga);

    @GetMapping("/pesquisar/onibus-por-qtd-passageiros/{passageiros}")
    public ResponseEntity<ArrayList<?>> pesquisarOnibus(@PathVariable int passageiros);

    @GetMapping("/calcular-aluguel/{placa}/{dias}")
    public double calcularAluguel(@PathVariable String placa, @PathVariable int dias) throws VeiculoNaoCadastrado;

    @Operation(summary = "Registrar Aluguel", description = " Formato da data: dd-MM-yyyy HH:mm:ss")
    @PostMapping("registrar-aluguel/{placa}/{dataInicio}/{dias}/{cpf}")
    public ResponseEntity<AluguelDto> registrarAluguel(@PathVariable String placa,
            @PathVariable @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm:ss") Date dataInicio,
            @PathVariable int dias, @PathVariable Long cpf)
            throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado;

    @Operation(summary = "Registrar Devolução", description = " Formato da data: dd-MM-yyyy HH:mm:ss")
    @GetMapping("/registrar-devolucao/{placa}/{data}/{cpf}")
    public ResponseEntity<?> registrarDevolucao(@PathVariable String placa,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date data, @PathVariable Long cpf)
            throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado;

    // tipo de veiculo
    // 0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)
    @Operation(summary = "Calcula a depreciação de todos os veículos de um determinado tipo", description = "Calcula a depreciação de todos os veículos de um determinado tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)")
    @GetMapping("/calcular/depreciar-veiculos/{tipo}/{taxaDepreciacao}")
    public void depreciarVeiculos(@PathVariable int tipo, @PathVariable double taxaDepreciacao);

    @Operation(summary = "Calcula o aumento da diária de todos os veículos de um determinado tipo", description = "Calcula o aumento das diárias dos veículos, usando os códigos:  0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)"
            +
            " A taxaAumento é um valor entre 0 e 100, que representa a porcentagem de aumento da diária")
    @PostMapping("/calcular/aumentar-diaria/{tipo}/{taxaAumento}")
    public ResponseEntity<?> aumentarDiaria(@PathVariable int tipo, @PathVariable double taxaAumento)
            throws TaxaAumentoForaIntervalo, TipoVeiculoDesconhecido;

    @Operation(summary = "Calcula o faturamento total das diárias de todos os veículos de um determinado tipo, devem ser consideradas apenas os alugueis finalizados", description = "Tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus) \n"
            +
            " Formato da data: dd-MM-yyyy HH:mm:ss")
    @GetMapping("/calcular/faturamento-total/{tipo}/{inicio}/{fim}")
    public double faturamentoTotal(@PathVariable int tipo,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date inicio,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date fim);

    @Operation(summary = "Calcula o número de dias que determinado tipo de veículos esteve alugado, dentro de um período informado", description = "Tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus) \n"
            +
            " Formato da data: dd-MM-yyyy HH:mm:ss")
    @GetMapping("/calcular/quantidade-total-diarias/{tipo}/{inicio}/{fim}")
    public int quantidadeTotalDeDiarias(@PathVariable int tipo,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date inicio,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date fim);

}
