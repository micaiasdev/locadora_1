package com.locadora.locadoraapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.locadora.locadoraapi.exception.ClienteNaoCadastrado;
import com.locadora.locadoraapi.exception.TaxaAumentoForaIntervalo;
import com.locadora.locadoraapi.exception.TipoVeiculoDesconhecido;
import com.locadora.locadoraapi.exception.VeiculoAlugado;
import com.locadora.locadoraapi.exception.VeiculoJaCadastrado;
import com.locadora.locadoraapi.exception.VeiculoNaoAlugado;
import com.locadora.locadoraapi.exception.VeiculoNaoCadastrado;
import com.locadora.locadoraapi.model.Aluguel;
import com.locadora.locadoraapi.model.Carro;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.model.Veiculo;
import com.locadora.locadoraapi.repository.AluguelRepository;
import com.locadora.locadoraapi.repository.ClienteRepository;
import com.locadora.locadoraapi.repository.VeiculoRepository;

@ExtendWith(SpringExtension.class)
public class VeiculoServiceTest {

    @InjectMocks  private VeiculoService veiculoService;
    @Mock private VeiculoRepository veiculoRepository;
    @Mock private AluguelRepository aluguelRepository; 
    @Mock private ClienteRepository clienteRepository;

    @Test
    public void inserir_deveRetornarVeiculoJaCadastrado_quandoVeiculoJaExistir() {
        
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");

        when(veiculoRepository.existsByPlaca(anyString())).thenReturn(true);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoJaCadastrado.class, () -> {
            veiculoService.inserir(veiculo);
        });
    }

    @Test
    public void inserir_deveRetornarSucesso() {
       
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");

        // Configura o mock para retornar false quando o existsByCpf for chamado
        when(veiculoRepository.existsByPlaca(anyString())).thenReturn(false);

        // Chama o método inserir
        veiculoService.inserir(veiculo);

        // Verifica se o método save foi chamado com o cliente correto
        verify(veiculoRepository).save(veiculo);
    } 
     

    @Test
    public void getValorTotalAluguel_deveLancarVeiculoNaoCadastrado_quandoVeiculoNaoExistir() {
        // Configura o mock para retornar null quando o findByPlaca for chamado
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoNaoCadastrado.class, () -> {
            veiculoService.getValorTotalAluguel("ABC1234", 5);
        });
    }

    @Test
    public void getValorTotalAluguel_deveRetornarValorCorreto_quandoVeiculoExistir() {
        // Configura o mock para retornar um veículo quando o findByPlaca for chamado
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        veiculo.setValorDiaria(100);
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);

        // Chama o método getValorTotalAluguel
        double valor = veiculoService.getValorTotalAluguel("ABC1234", 5);

        // Verifica se o valor retornado é o esperado
        assertEquals(veiculo.aluguel(5), valor);
    }

    @Test
    public void registrarAluguel_deveRetornarAluguel_quandoVeiculoEClienteExistiremEVeiculoNaoEstiverAlugado() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(null);
        Cliente cliente = new Cliente();
        cliente.setCpf(123L);
        when(clienteRepository.findByCpf(anyLong())).thenReturn(cliente);

        // Chama o método registrarAluguel
        Aluguel aluguel = veiculoService.registrarAluguel("ABC1234", new Date(), 5, 123L);

        // Verifica se o aluguel retornado é o esperado
        assertEquals(cliente, aluguel.getCliente());
        assertEquals(veiculo, aluguel.getVeiculo());
        assertEquals(veiculo.aluguel(5), aluguel.getTotalAluguel());
    }

    @Test
    public void registrarAluguel_deveLancarVeiculoNaoCadastrado_quandoVeiculoNaoExistir() {
        // Configura o mock para retornar null quando o findByPlaca for chamado
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoNaoCadastrado.class, () -> {
            veiculoService.registrarAluguel("ABC1234", new Date(), 5, 123L);
        });
    }

    @Test
    public void registrarAluguel_deveLancarVeiculoAlugado_quandoVeiculoJaEstiverAlugado() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        Aluguel aluguelExistente = new Aluguel();
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(aluguelExistente);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoAlugado.class, () -> {
            veiculoService.registrarAluguel("ABC1234", new Date(), 5, 123L);
        });
    }

    @Test
    public void registrarAluguel_deveLancarClienteNaoCadastrado_quandoClienteNaoExistir() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(null);
        when(clienteRepository.findByCpf(anyLong())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(ClienteNaoCadastrado.class, () -> {
            veiculoService.registrarAluguel("ABC1234", new Date(), 5, 123L);
        });
    }


    @Test
    public void registrarDevolucao_deveRetornarAluguel_quandoVeiculoAluguelEClienteExistirem() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        Aluguel aluguel = new Aluguel();
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(aluguel);
        Cliente cliente = new Cliente();
        cliente.setCpf(123L);
        when(clienteRepository.findByCpf(anyLong())).thenReturn(cliente);

        // Chama o método registrarDevolucao
        Aluguel aluguelRetornado = veiculoService.registrarDevolucao("ABC1234", new Date(), 123L);

        // Verifica se o aluguel retornado é o esperado
        assertEquals(aluguel, aluguelRetornado);
        assertTrue(aluguelRetornado.isBaixo());
    }

    @Test
    public void registrarDevolucao_deveLancarVeiculoNaoCadastrado_quandoVeiculoNaoExistir() {
        // Configura o mock para retornar null quando o findByPlaca for chamado
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoNaoCadastrado.class, () -> {
            veiculoService.registrarDevolucao("ABC1234", new Date(), 123L);
        });
    }

    @Test
    public void registrarDevolucao_deveLancarVeiculoNaoAlugado_quandoVeiculoNaoEstiverAlugado() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(VeiculoNaoAlugado.class, () -> {
            veiculoService.registrarDevolucao("ABC1234", new Date(), 123L);
        });
    }

    @Test
    public void registrarDevolucao_deveLancarClienteNaoCadastrado_quandoClienteNaoExistir() {
        // Configura os mocks
        Veiculo veiculo = new Carro();
        veiculo.setPlaca("ABC1234");
        when(veiculoRepository.findByPlaca(anyString())).thenReturn(veiculo);
        Aluguel aluguel = new Aluguel();
        when(aluguelRepository.findFirstByVeiculoPlacaAndBaixoFalse(anyString())).thenReturn(aluguel);
        when(clienteRepository.findByCpf(anyLong())).thenReturn(null);

        // Verifica se a exceção é lançada
        assertThrows(ClienteNaoCadastrado.class, () -> {
            veiculoService.registrarDevolucao("ABC1234", new Date(), 123L);
        });
    }

    @Test
    public void depreciarVeiculos_deveDepreciarTodosOsVeiculos_quandoTipoForZero() {
        // Configura o mock para retornar uma lista de veículos quando o findAll for chamado
        List<Veiculo> veiculos = Arrays.asList(spy(new Carro()), spy(new Carro()));
        when(veiculoRepository.findAll()).thenReturn(veiculos);

        // Chama o método depreciarVeiculos
        veiculoService.depreciarVeiculos(0, 0.1);

        // Verifica se o método depreciar foi chamado para cada veículo
        veiculos.forEach(veiculo -> verify(veiculo).depreciar(0.1));

        // Verifica se o método save foi chamado para cada veículo
        veiculos.forEach(veiculo -> {
            if (veiculo != null) {
                verify(veiculoRepository).save(veiculo);
            }
        });
    }

    @Test
    public void depreciarVeiculos_deveDepreciarVeiculosDoTipoEspecificado_quandoTipoNaoForZero() {
        // Configura o mock para retornar uma lista de veículos quando o findAllVeiculosByType for chamado
        List<Veiculo> veiculos = Arrays.asList(spy(new Carro()), spy(new Carro()));
        when(veiculoRepository.findAllVeiculosByType(any())).thenReturn(veiculos);

        // Chama o método depreciarVeiculos
        veiculoService.depreciarVeiculos(1, 0.1);

        // Verifica se o método depreciar foi chamado para cada veículo
        veiculos.forEach(veiculo -> verify(veiculo).depreciar(0.1));

        // Verifica se o método save foi chamado para cada veículo
        veiculos.forEach(veiculo -> {
            if (veiculo != null) {
                verify(veiculoRepository).save(veiculo);
            }
        });
    }

    @Test
    public void aumentarDiaria_deveLancarTaxaAumentoForaIntervalo_quandoTaxaAumentoForaIntervaloAumento() {
        // Verifica se a exceção é lançada
        assertThrows(TaxaAumentoForaIntervalo.class, () -> {
            veiculoService.aumentarDiaria(0, -0.1);
        });
    }

    @Test
    public void aumentarDiaria_deveLancarTipoVeiculoDesconhecido_quandoTipoForDesconhecido() {
        // Verifica se a exceção é lançada
        assertThrows(TipoVeiculoDesconhecido.class, () -> {
            veiculoService.aumentarDiaria(5, 50);
        });
    }

}
