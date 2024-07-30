# Sistema para Controle de uma Locadora de Veículos

### Resquisitos

Suponha que você deseja implementar um **Sistema para Controle de uma Locadora de Veículos**. 
Essa locadora trabalha com 4 tipos de veículos: `moto`, `carro`, `ônibus` e `caminhão`. 
Todos os veículos possuem `marca`, `modelo`, `ano de fabricação`, `placa`, `valor da diária` e `valor avaliado do bem`. 

Os caminhões têm como informação adicional `capacidade de carga transportada`, as motos possuem uma informação adicional sobre sua `cilindrada`, os carros a `categoria (passeio, SUV, pick up)` e ônibus a `capacidade de passageiros`. 

Cada um dos tipos de veículos existentes na locadora possui uma forma de cálculo do seguro diário exigido em caso de locação. O seguro de uma moto é dado pela fórmula `(valor do bem*11%)/365`, onde o valor do bem corresponde ao valor avaliado do veículo em questão. Da mesma forma, o seguro de um caminhão é dado pela fórmula `(valor do bem*8%)/365`, o seguro de um carro é `(valor do bem*3%)/365` o seguro de um ônibus é `(valor do bem*20%)/365`. 

O aluguel de um determinado veículo é dado pela fórmula `(valor da diária + seguro) * quantidade de dias`.

Para se controlar o valor de cada veículo e das suas diárias, é possível realizar a depreciação nos valores dos bens (reduzindo um percentual no valor de cada veículo), além de possibilitar um acréscimo no valor de todas as diárias de um tipo de veículo (por exemplo, aumentar a diária das motos em 10%).

No sistema da locadora deverá existir uma série de consultas à frota, testando seus dados adicionais. Essas consultas permitem descobrir quais as motos com cilindrada maior ou igual a uma cilindrada desejada, encontrar caminhões que consigam transportar uma determinada quantidade de carga mínima e pesquisar carros que sejam de passeio ou SUV ou pick ups.

1. Foram criadas classes que representam veículos (carro, caminhão, ônibus e motos), levando em consideração as diversas características da linguagem Java para se construir programas com mais qualidade.  Essas classes devem possuir pelo menos os métodos para retornar o valor do seguro, retornar valor de aluguel, aumentar e reduzir preço de uma diária e aumentar e reduzir o valor avaliado do bem. 

2. Foi criada uma classe que implemente a classe abstrata Locadora, que tem como principal função controlar os veículos existentes. Essa locadora possui algumas estruturas de dados definidas, juntamente com a implementação de poucos métodos, porém com a definição do que você precisa implementar. Lembre-se mais uma vez das características de qualidade de software implementadas pela linguagem Java para alcançar esses objetivos. Um detalhe: pode ser que você precise criar classes extras para conseguir implementar tudo que foi solicitado. Não existe qualquer problema.  `No comentário dos métodos existe uma explicação adicional ao seu funcionamento. Usem essa definição para construir seus métodos. `

3. Demais métodos previstos na classe locadora. 

```java
@RequestMapping("/locadora")
public interface Locadora {

    @PostMapping("/create/veiculo") 
    public  ResponseEntity<?> inserir(@RequestBody  VeiculoDto v) throws VeiculoJaCadastrado, SQLException;

	@PostMapping("/create/cliente") 
    public ResponseEntity<ClienteDto> inserir(@RequestBody  ClienteDto c) throws ClienteJaCadastrado, SQLException;

	@GetMapping("/pesquisar/veiculo-placa/{placa}") 
    public ResponseEntity<?> pesquisar(@PathVariable String placa) throws VeiculoNaoCadastrado; 

    @GetMapping("/pesquisar/moto-por-cilindrada/{cilindrada}") 
    public ResponseEntity< ArrayList<?> > pesquisarMoto(@PathVariable int cilindrada);
	// tipo de carro 1 (passeio), 2 (SUV), 3 (pickup)
    @GetMapping("/pesquisar/carro-pelo-tipo/{tipoCarro}") 
    public ResponseEntity< ArrayList<?> > pesquisarCarro(@PathVariable int tipoCarro);

    @GetMapping("/pesquisar/caminhao-por-carga/{carga}") 
    public ResponseEntity< ArrayList<?> > pesquisarCaminhao(@PathVariable int carga);

    @GetMapping("/pesquisar/onibus-por-passageiros/{passageiros}") 
    public ResponseEntity< ArrayList<?> > pesquisarOnibus(@PathVariable int passageiros);
    
    @GetMapping("/calcular-aluguel/{placa}/{dias}") 
    public double calcularAluguel(@PathVariable String placa,@PathVariable int dias) throws VeiculoNaoCadastrado;
    
    @PostMapping("create/aluguel/{placa}/{data}/{dias}/{cpf}") 
    public void registrarAluguel(@PathVariable String placa, 
    @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date data,
    @PathVariable int dias,@PathVariable Long cpf) throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado;
   
    @GetMapping("/registrar-devolucao/{placa}/{data}/{cpf}") 
    public void registrarDevolucao(@PathVariable String placa, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date data,@PathVariable Long cpf) throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado;
   
	// tipo de veiculo
	// 0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)
    @Operation(summary = "Calcula a depreciação de todos os veículos de um determinado tipo", 
    description = "Calcula a depreciação de todos os veículos de um determinado tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)") 
    @GetMapping("/calcular/depreciar-veiculos/{tipo}/{taxaDepreciacao}") 
    public void depreciarVeiculos(@PathVariable int tipo,@PathVariable  double taxaDepreciacao);
    
    @Operation(summary = "Calcula o aumento da diária de todos os veículos de um determinado tipo", 
    description = "Calcula a depreciação de todos os veículos de um determinado tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus)" + 
    " A taxaAumento é um valor entre 0 e 100, que representa a porcentagem de aumento da diária"
    )
    @GetMapping("/calcular/aumentar-diaria/{tipo}/{taxaAumento}") 
    public void aumentarDiaria(@PathVariable int tipo, @PathVariable double taxaAumento);
    
    @Operation(summary = "Calcula o faturamento total das diárias de todos os veículos de um determinado tipo, devem ser consideradas apenas os alugueis finalizados", 
    description = 
     "Tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus) \n" + 
     " Formato da data: dd-MM-yyyy HH:mm:ss" )
    @GetMapping("/calcular/faturamento-total/{tipo}/{inicio}/{fim}") 
    public double faturamentoTotal(@PathVariable  int tipo,
    @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date inicio, 
    @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")Date fim);
    
    @Operation(summary = "Calcula o número de dias que determinado tipo de veículos esteve alugado, dentro de um período informado", 
    description = 
     "Tipo   0 (todos), 1 (moto), 2 (carro), 3 (caminhão), 4 (ônibus) \n" + 
     " Formato da data: dd-MM-yyyy HH:mm:ss" )
    @GetMapping("/calcular/quantidade-total-diarias/{tipo}/{inicio}/{fim}") 
    public int quantidadeTotalDeDiarias(@PathVariable int tipo, 
    @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date inicio, 
    @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date fim);
    
}

```

