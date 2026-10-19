## Tabela de Conteúdos

- [Visão Geral](#visão-geral)
- [Decisões de Arquitetura e POO](#decisões-de-arquitetura-e-poo)
- [Regras de Negócio](#regras-de-negócio)

---

## Visão Geral

Este projeto implementa uma solução para controle de transações bancárias (saques, depósitos e pagamento de boletos). O objetivo principal é demonstrar o uso correto de modificadores de acesso (`private`, `final`) e o controle rigoroso das alterações de estado do objeto, impedindo inconsistências e acessos não autorizados aos atributos financeiros.

---

## Decisões de Arquitetura e POO

A classe de domínio `Conta` foi desenhada sob os seguintes pilares de engenharia de software:

- **Encapsulamento Estrito (Defensive Encapsulation):**
    - Todos os atributos de estado (`saldo`, `cheque`, `usandoCheque`, `contaAberta`) possuem modificador `private`.
    - Os métodos modificadores (*setters*) são privados (`private void setSaldo(...)`), forçando todas as mutações de saldo a passarem exclusivamente pelas regras de negócio expostas nos métodos públicos (`depositar`, `sacar`, `pagarBoleto`).

- **Imutabilidade de Identificador:**
    - O atributo `numConta` é definido como `private final`, garantindo a imutabilidade do identificador do objeto após a sua instanciação.

- **Proteção contra Estados Inválidos (Invariantes de Domínio):**
    - Pré-condições de segurança no topo de cada operação bloqueiam saques e pagamentos zerados ou negativos (`<= 0`) e impedem movimentações em contas fechadas.

- **Encapsulamento de Métodos Auxiliares:**
    - Métodos como `getLimiteDisponivel()` são privados, ocultando os detalhes de cálculo de margem operacional para chamadas externas.

---

## Regras de Negócio

1. **Abertura de Conta e Cheque Especial Dinâmico:**
    - Depósito inicial até R$ 500,00: limite de cheque especial concedido fixado em R$ 50,00.
    - Depósito inicial acima de R$ 500,00: limite de cheque especial proporcional a 50% do valor do depósito.
2. **Utilização de Cheque Especial e Cobrança de Taxa:**
    - Permite saques e boletos que ultrapassem o saldo positivo, desde que estejam cobertos pelo limite de cheque especial disponível.
    - A cada operação que faz uso do cheque especial, incide uma taxa automática de 20% sobre o montante negativo gerado na transação.
3. **Restauração Automatizada de Estado:**
    - A flag `usandoCheque` permanece como `true` durante amortizações parciais de dívida e retorna para `false` no exato momento em que um depósito restaura o saldo para valor nulo ou positivo (`>= 0`).