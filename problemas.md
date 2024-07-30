

### Problemas
**ID Questão = 001**

*Antes de Iniciar, dê início à gravação da tela*

1. Implementar o método `VeiculoService.registrarAluguel(placa, data, dias, cpf)` que persiste um `Aluguel`.
Esse método deve disparar as exceções VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado, caso alguma dessas situações ocorram. 

O método recebe como parâmetros placa, data de inicio do aluguel, o número de dias e o CPF do cliente que está realizando o aluguel. 

O método já consta na classe `VeiculoService`, conforme abaixo:

```java
public Aluguel registrarAluguel(String placa, Date data, int dias, Long cpf) throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado{}

```
Observações: 
  - A classe `Aluguel` é composta pelos seguintes atributos: `cliente`, `veiculo`,`dataInicio`, `dataFim` e `totalAluguel(valor total devido pelo aluguel)`. Inclusive essa classe possui um construtor com esses campos nessa ordem.

  - `dataFim` é simplesmente `dataInicio` somado ao número de `dias`.

**Lembre-se de exportar o chat, caso esteja usando Copilot `Ctrl+Shift+P | Chat: Exportar Chat...`**

### Próximo
**ID Questão = 002**

*Antes de Iniciar, dê início à gravação da tela*

2. Implementar o método `VeiculoService.aumentarDiaria(int tipo, double taxaAumento)` que registra o aumento das diárias dos veículos, usando os códigos para o tipo (parâmetro do método):  
- 0 (todos os veículos), 
- 1 (moto), 2 (carro), 
- 3 (caminhão) ou
- 4 (ônibus) 
- Se não, lançar a exceção `TipoVeiculoDesconhecido`

**Para buscar pelo tipo na base de dados na classe VeiculoRepository foi disponibilizado o método findAllVeiculosByType(Class<?> classe), que retorna os veículos pelo tipo, para usá-la chame o Helper criado para retornar a classe quando o tipo é passado TipoVeiculoClassHelper.getTipoVeiculoClass(tipo), isto é veiculoRepository.findAllVeiculosByType(TipoVeiculoClassHelper.getTipoVeiculoClass(tipo))**

A `taxaAumento` é um valor maior que 0 e menor ou igual a 100 (dentro da classe `Veiculo.java` já existe o método que calcula o aumento da diário), que representa a porcentagem de aumento da diária, se não lançar a exceção `TaxaAumentoForaIntervalo`.

Na classe veículo deve deve-se registrar `valorDiaria` com o novo valor e `valorDiariaAntigo`, com o valor anterior ao reajuste.

O método já consta na classe `VeiculoService`, conforme abaixo:
```java
 
public List<Veiculo> aumentarDiaria(int tipo, double taxaAumento){}
```

**Lembre-se de exportar o chat, caso esteja usando Copilot `Ctrl+Shift+P | Chat: Exportar Chat...`**
