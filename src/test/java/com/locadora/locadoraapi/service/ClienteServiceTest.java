package com.locadora.locadoraapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.locadora.locadoraapi.exception.ClienteJaCadastrado;
import com.locadora.locadoraapi.model.Cliente;
import com.locadora.locadoraapi.repository.ClienteRepository;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void inserir_deveRetornarClienteJaCadastrado_quandoClienteJaExistir() {
        // Cria um cliente
        Cliente cliente = new Cliente();
        cliente.setCpf(123L); 

        // Configura o mock para retornar true quando o existsByCpf for chamado
        when(clienteRepository.existsByCpf(anyLong())).thenReturn(true);

        // Verifica se a exceção é lançada
        assertThrows(ClienteJaCadastrado.class, () -> {
            clienteService.inserir(cliente);
        });
    }

    @Test
    public void inserir_deveRetornarSucesso() {
        // Cria um cliente
        Cliente cliente = new Cliente();
        cliente.setCpf(123L); 

        // Configura o mock para retornar false quando o existsByCpf for chamado
        when(clienteRepository.existsByCpf(anyLong())).thenReturn(false);

        // Chama o método inserir
        clienteService.inserir(cliente);

        // Verifica se o método save foi chamado com o cliente correto
        verify(clienteRepository).save(cliente);
}
}
