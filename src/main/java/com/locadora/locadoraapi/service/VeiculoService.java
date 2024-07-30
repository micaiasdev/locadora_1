package com.locadora.locadoraapi.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.TaxaAumentoForaIntervalo;
import com.locadora.locadoraapi.exception.TipoVeiculoDesconhecido;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.helpers.TipoCarroEnum;
import com.locadora.locadoraapi.helpers.TipoVeiculoClassHelper;
import com.locadora.locadoraapi.model.Aluguel;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.repository.AluguelRepository;
import com.locadora.locadoraapi.repository.ClienteRepository;
import com.locadora.locadoraapi.repository.VeiculoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;
    private AluguelRepository aluguelRepository;

    public Veiculo inserir(Veiculo veiculo) throws VeiculoJaCadastrado {

        if (veiculoRepository.existsByPlaca(veiculo.getPlaca()))
            throw new VeiculoJaCadastrado();

        return veiculoRepository.save(veiculo);
    }

    public Veiculo getVeiculoByPlaca(String placa) {

        return Optional.ofNullable(veiculoRepository.findByPlaca(placa))
                .orElseThrow(() -> new VeiculoNaoCadastrado());
    }

    public Collection<? extends Veiculo> getVeiculoByCilindrada(int cilindrada) {
        return veiculoRepository.findByCilindrada(cilindrada);
    }

    public Collection<? extends Veiculo> getVeiculoByTipoCarro(int tipoCarro) {
        return veiculoRepository.findByTipo(TipoCarroEnum.values()[tipoCarro - 1]); // 1 - passeio, 2 - SUV, 3 - pickup,
                                                                                    // subtrai-se um pois o indice
                                                                                    // inicia em zero
    }

    public Collection<? extends Veiculo> getVeiculoByCarga(int carga) {
        return veiculoRepository.findByCarga(carga);
    }

    public Collection<? extends Veiculo> getVeiculoByPassageiros(int passageiros) {
        return veiculoRepository.findByCapacidadePassageiros(passageiros);
    }

    public double getValorTotalAluguel(String placa, int dias) {
        Veiculo veiculo = Optional.ofNullable(this.veiculoRepository.findByPlaca(placa))
                .orElseThrow(() -> new VeiculoNaoCadastrado());
        return veiculo.aluguel(dias);
    }

    public Aluguel registrarDevolucao(String placa, Date data, Long cpf) {

        Optional.ofNullable(this.veiculoRepository.findByPlaca(placa))
                .orElseThrow(() -> new VeiculoNaoCadastrado());

        // verificar se o veiculo esta alugado
        Aluguel aluguel = Optional.ofNullable(this.aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(placa))
                .orElseThrow(() -> new VeiculoNaoAlugado());

        Optional.ofNullable(this.clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new ClienteNaoCadastrado());

        LocalDateTime devolucao = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        aluguel.setDataDevolucaoReal(devolucao);
        aluguel.setBaixo(true);

        this.aluguelRepository.save(aluguel);

        return aluguel;
    }

    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
        if (taxaDepreciacao < 0 || taxaDepreciacao > 100)
            throw new IllegalArgumentException("Taxa de depreciacao deve ser entre 0 e 100");
        if (tipo < 0 || tipo > 4)
            throw new IllegalArgumentException("Tipo deve ser entre 0 e 4");

        if (tipo == 0) {
            this.veiculoRepository.findAll().forEach((veiculo) -> {
                veiculo.depreciar(taxaDepreciacao);
                this.veiculoRepository.save(veiculo);
            });

        } else {
            this.veiculoRepository.findAllVeiculosByType(TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))
                    .forEach((veiculo) -> {
                        veiculo.depreciar(taxaDepreciacao);
                        this.veiculoRepository.save(veiculo);
                    });
        }

    }

    public double faturamentoTotal(int tipo, Date inicio, Date fim) {
        LocalDateTime inicioLocalDateTime = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fimLocalDateTime = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (tipo == 0) {
            return this.aluguelRepository
                    .findByDataInicioBetweenAndDataDevolucaoRealNotNullAndBaixoIsTrue(inicioLocalDateTime,
                            fimLocalDateTime)
                    .stream()
                    .mapToDouble((aluguel) -> aluguel.getTotalAluguel())
                    .sum();

        } else {
            return this.aluguelRepository
                    .findByDataInicioBetweenAndVeiculoTipo(inicioLocalDateTime, fimLocalDateTime,
                            TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))
                    .stream()
                    .mapToDouble((aluguel) -> aluguel.getTotalAluguel())
                    .sum();
        }

    }

    public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
        LocalDateTime inicioLocalDateTime = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime fimLocalDateTime = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (tipo == 0) {
            return this.aluguelRepository.findByDataInicioBetween(inicioLocalDateTime, fimLocalDateTime)
                    .stream()
                    .mapToInt((aluguel) -> aluguel.getDias())
                    .sum();

        } else {
            return this.aluguelRepository
                    .findByDataInicioBetweenAndVeiculoTipo(inicioLocalDateTime, fimLocalDateTime,
                            TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))
                    .stream()
                    .mapToInt((aluguel) -> aluguel.getDias())
                    .sum();
        }
    }

    // Este é o problema 1
    public Aluguel registrarAluguel(String placa, Date dataInicio, int dias, Long cpf)
            throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
        Aluguel aluguel = null;
        // Verifica se o cliente foi cadastado
        Cliente cliente = Optional.ofNullable(this.clienteRepository.findByCpf(cpf))
                .orElseThrow(() -> new ClienteNaoCadastrado());
        // Pesquisa o veiculo
        Veiculo veic = this.getVeiculoByPlaca(placa);
        try {
            // Verifica se o Veiculo já está Alugado
            Optional.ofNullable(this.aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(placa))
                    .orElseThrow(() -> new VeiculoNaoAlugado());
        } catch (VeiculoNaoAlugado e) {
            LocalDateTime dataInicioLocal = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            Double totalAluguel = veic.aluguel(dias);

            aluguel = new Aluguel(cliente, veic, dataInicioLocal, dataInicioLocal.plusDays(dias), totalAluguel);

            this.aluguelRepository.save(aluguel);
        }
        return aluguel;
    }

    // Este é o problema 2
    public List<Veiculo> aumentarDiaria(int tipo, double taxaAumento)
            throws TaxaAumentoForaIntervalo, TipoVeiculoDesconhecido {
        return null;
    }

}
