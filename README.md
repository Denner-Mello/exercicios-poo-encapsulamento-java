## Tabela de Conteúdos

- [Visão Geral](#visão-geral)
- [Decisões de Arquitetura e POO](#decisões-de-arquitetura-e-poo)
- [Regras de Negócio](#regras-de-negócio)

---

## Visão Geral

Este repositório reúne soluções para o estudo e aplicação prática de Programação Orientada a Objetos em Java, organizadas por pacotes:

- **Conta Bancária (`package conta`):** Sistema para controle de transações bancárias (saques, depósitos e pagamento de boletos) com limite dinâmico de cheque especial e proteção de estado.
- **Controle de Veículo (`package carro`):** Simulação das funções operacionais de um carro (partida, aceleração, troca sequencial de marchas e manobras) com controle estrito de limites de velocidade e estados mecânicos.
- **Máquina de Banho de Pets (`package petshop.maquina` e `package petshop.main`):** Sistema para controle operacional automatizado de banho e higienização de pets, com gerenciamento de insumos (água e shampoo), capacidade por animal e travas de segurança de estado.

---

## Decisões de Arquitetura e POO

### Classe `Conta` (`package conta`)

- **Encapsulamento Estrito (Defensive Encapsulation):**
    - Todos os atributos de estado (`saldo`, `cheque`, `usandoCheque`, `contaAberta`) possuem modificador `private`.
    - Os métodos modificadores (*setters*) são privados (`private void setSaldo(...)`), forçando todas as mutações de saldo a passarem exclusivamente pelas regras de negócio expostas nos métodos públicos (`depositar`, `sacar`, `pagarBoleto`).

- **Imutabilidade de Identificador:**
    - O atributo `numConta` é definido como `private final`, garantindo a imutabilidade do identificador do objeto após a sua instanciação.

- **Proteção contra Estados Inválidos (Invariantes de Domínio):**
    - Pré-condições de segurança no topo de cada operação bloqueiam saques e pagamentos zerados ou negativos (`<= 0`) e impedem movimentações em contas fechadas.

- **Encapsulamento de Métodos Auxiliares:**
    - Métodos como `getLimiteDisponivel()` são privados, ocultando os detalhes de cálculo de margem operacional para chamadas externas.

### Classe `Carro` (`package carro`)

- **Encapsulamento de Atributos e Limites:**
    - Atributos de estado (`marca`, `modelo`, `marcha`, `velocidade`, `ligado`, `velocidadeMinima`, `velocidadeMaxima`) possuem modificador `private`.
    - Métodos modificadores de estado e limites (`setLigado`, `setMarcha`, `setVelocidade`, `setVelocidadeMinima`, `setVelocidadeMaxima`) são estritamente privados (`private`), impedindo que classes externas alterem os atributos sem passar pelas validações.

- **Transição Consistente em Troca de Marchas:**
    - Ao subir a marcha, a velocidade é ajustada para o piso da nova marcha.
    - Ao descer a marcha, a velocidade é ajustada para o teto da marcha inferior, evitando estados inconsistentes de velocidade.

- **Verificação de Pré-condições:**
    - Operações operacionais verificam o estado do motor (`!isLigado()`) e do câmbio (`marcha == 0`) no topo da execução antes de aplicar alterações.

- **Método de Inspeção (`status()`):**
    - Permite visualizar as informações atuais do veículo sem expor ou permitir mutações diretas nos atributos.

### Classes `Maquina` e `Pet` (`package petshop.maquina`)

- **Controle de Acesso Inter-Pacotes (`protected`):**
    - O método `setLimpo(boolean limpo)` da classe `Pet` utiliza a visibilidade `protected`. Isso permite que a classe `Maquina` (no mesmo pacote) altere o estado de limpeza do animal após o banho, impedindo que a classe `Main` (localizada no pacote `petshop.main`) modifique o estado do pet de forma direta.

- **Associação de Objetos e Desvinculamento Seguro:**
    - A classe `Maquina` mantém uma referência interna privada ao objeto `Pet` (`private Pet pet`). A atribuição (`this.pet = p1`) ocorre apenas quando o pet é efetivamente colocado na máquina. Ao retirar o pet, a referência é limpa com `this.pet = null`, garantindo o desvinculamento na memória.

- **Encapsulamento e Modulação Interna de Insumos:**
    - Os métodos modificadores dos níveis de insumos (`setNivelAgua`, `setNivelShampoo`, `setPetBanhando`, `setLimpa`) são estritamente privados, garantindo que o consumo e os abastecimentos ocorram apenas por meio dos métodos de negócio públicos.

- **Validação Rigorosa de Pré-condições de Operação:**
    - Métodos de ação (`banharPet`, `colocarPet`, `limparMaquina`) validam no topo da execução a presença de insumos mínimos, a desocupação da máquina e o estado de higienização do sistema antes de aplicar alterações.

---

## Regras de Negócio

### 1. Conta Bancária (`package conta`)

1. **Abertura de Conta e Cheque Especial Dinâmico:**
    - Depósito inicial até R$ 500,00: limite de cheque especial concedido fixado em R$ 50,00.
    - Depósito inicial acima de R$ 500,00: limite de cheque especial proporcional a 50% do valor do depósito.
2. **Utilização de Cheque Especial e Cobrança de Taxa:**
    - Permite saques e boletos que ultrapassem o saldo positivo, desde que estejam cobertos pelo limite de cheque especial disponível.
    - A cada operação que faz uso do cheque especial, incide uma taxa automática de 20% sobre o montante negativo gerado na transação.
3. **Restauração Automatizada de Estado:**
    - A flag `usandoCheque` permanece como `true` durante amortizações parciais de dívida e retorna para `false` no exato momento em que um depósito restaura o saldo para valor nulo ou positivo (`>= 0`).

### 2. Controle de Veículo (`package carro`)

1. **Estado Inicial:**
    - O veículo é instanciado desligado (`ligado = false`), em ponto morto (`marcha = 0`) e parado (`velocidade = 0 km/h`).
2. **Bloqueio Geral de Funções:**
    - Nenhuma função operacional (acelerar, desacelerar, trocar marcha ou virar) pode ser realizada com o carro desligado.
3. **Controle de Aceleração e Desaceleração:**
    - Acelerar incrementa 1 km/h respeitando o teto da marcha atual (máximo global de 120 km/h).
    - Desacelerar decrementa 1 km/h respeitando o piso da marcha atual (mínimo global de 0 km/h).
    - Em ponto morto (`marcha 0`), a aceleração é bloqueada.
4. **Faixas Rígidas de Velocidade por Marcha:**
    - `Marcha 0 (Ponto Morto)`: 0 km/h
    - `1ª Marcha`: 0 a 20 km/h
    - `2ª Marcha`: 21 a 40 km/h
    - `3ª Marcha`: 41 a 60 km/h
    - `4ª Marcha`: 61 a 80 km/h
    - `5ª Marcha`: 81 a 100 km/h
    - `6ª Marcha`: 101 a 120 km/h
5. **Troca Sequencial de Marchas:**
    - O câmbio opera de forma estritamente sequencial (0 a 6), impedindo saltar marchas.
6. **Segurança em Manobras:**
    - A manobra de virar (esquerda/direita) só é permitida se o veículo estiver com velocidade entre 1 km/h e 40 km/h.
7. **Desligamento Seguro:**
    - O motor só pode ser desligado se o carro estiver parado (0 km/h) e em ponto morto (marcha 0).

### 3. Máquina de Banho de Pets (`package petshop.maquina`)

1. **Capacidade Máxima e Incremento de Insumos:**
    - A máquina suporta a capacidade máxima de 30 litros de água e 10 litros de shampoo.
    - O abastecimento é feito em incrementos de 2 litros por acionamento, ajustando-se para 1 litro caso o reservatório esteja a 1 litro do limite máximo (29 L de água ou 9 L de shampoo).
2. **Capacidade por Atendimento e Bloqueios de Entrada:**
    - A máquina permite estritamente 1 pet por vez.
    - A entrada de um novo animal é bloqueada se a máquina já estiver ocupada ou se o estado da máquina indicar que ela precisa ser limpa (`limpa = false`).
3. **Consumo de Insumos no Banho:**
    - O banho consome 10 litros de água e 2 litros de shampoo por execução, alterando o estado do pet interno para limpo (`limpo = true`).
    - A operação de banho exige a presença do pet na máquina e níveis suficientes de água (mínimo 10 L) e shampoo (mínimo 2 L).
4. **Trava de Segurança e Retirada de Pet Sujo:**
    - Se o pet for retirado da máquina sem ter concluído o banho (`limpo = false`), a máquina altera automaticamente seu estado para suja (`limpa = false`), impedindo o ingresso de qualquer outro animal até que a higienização seja realizada.
5. **Higienização Obrigatória da Máquina:**
    - A limpeza da máquina consome 3 litros de água e 1 litro de shampoo.
    - A operação só pode ser realizada com a máquina desocupada (`petBanhando = false`) e com insumos suficientes. Ao final do procedimento, o estado é restaurado para limpo (`limpa = true`), liberando a entrada do próximo pet.