# Projeto Hamburgueria

Este é um aplicativo Android simples, desenvolvido em Java no Android Studio, criado como meu primeiro app para aprender os conceitos básicos do desenvolvimento nativo Android. O app permite fazer pedidos de hambúrguer, incluindo seleção de adicionais, quantidade e envio do pedido por e-mail.

---

## Tecnologias e Conceitos Utilizados

### Activity

O app utiliza a classe `MainActivity` que estende `AppCompatActivity`. Uma **Activity** no Android representa uma única tela com interface gráfica. Ela é o ponto central onde o usuário interage com o app. No `MainActivity` foi implementada toda a lógica para capturar dados do usuário, atualizar a interface e enviar o pedido.

### Layout XML

A interface do usuário é definida no arquivo `activity_main.xml`, utilizando um `LinearLayout` para organizar os elementos verticalmente. O XML serve para descrever a estrutura visual do app separadamente do código Java, permitindo um design declarativo da UI.

Principais componentes:

- `TextView`: para exibir textos, títulos e informações dinâmicas como quantidade e preço.
- `EditText`: para entrada de texto (nome do cliente).
- `CheckBox`: para seleção de adicionais do hambúrguer (Bacon, Cheddar, Picles).
- `Button`: botões para aumentar/diminuir quantidade e para enviar o pedido.
- `ImageView`: para exibir a imagem/banner da hamburgueria.

### Intent

O app utiliza um **Intent** para enviar o pedido por e-mail. Intents são uma forma de comunicação entre componentes do Android ou entre apps. Aqui, foi usado o `Intent.ACTION_SEND` para abrir um app de e-mail externo e preencher automaticamente o destinatário, assunto e corpo da mensagem com os dados do pedido.

---

## Como o app funciona

1. O usuário informa o nome no campo `EditText`.
2. Seleciona adicionais pelo `CheckBox`.
3. Usa os botões "+" e "−" para ajustar a quantidade de hambúrgueres.
4. O preço é calculado dinamicamente considerando o preço base e os adicionais marcados.
5. Ao clicar em "Enviar Pedido", o app valida se o nome foi preenchido.
6. Se válido, monta um resumo do pedido com todos os dados e usa o Intent para abrir o app de e-mail para envio.

---

## Detalhes da implementação das interações

- Os botões de aumentar/diminuir quantidade (`btnMais` e `btnMenos`) têm listeners que incrementam ou decrementam a variável `quantidade` e chamam a função `atualizarInterface()`, que atualiza a quantidade e o preço na tela.
- Os `CheckBox` possuem listeners para detectar quando o usuário marca ou desmarca um adicional, também chamando `atualizarInterface()` para recalcular o preço.
- O botão `btnEnviar` verifica se o nome não está vazio antes de montar o resumo e disparar o Intent para envio por e-mail. Caso contrário, exibe um `Toast` informando para preencher o nome.
- O cálculo do preço é feito somando o preço base (R$10,00) com valores extras para cada adicional selecionado (Bacon +2, Cheddar +2, Picles +3) e multiplicando pela quantidade.

---

## Considerações finais

Este projeto tem caráter educacional, sendo o primeiro passo para entender a arquitetura básica de um app Android, manipulação de interface com XML, interação do usuário, uso de Intents para integração entre apps, e boas práticas de desenvolvimento Java no ambiente Android.

Apesar de simples, serve como base para projetos futuros mais complexos, além de fixar conceitos essenciais para quem está começando.

---

