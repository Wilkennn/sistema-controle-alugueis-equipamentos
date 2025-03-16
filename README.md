
# 🛠️ Sistema de Controle de Aluguéis de Equipamentos

O **Sistema de Controle de Aluguéis de Equipamentos** é uma aplicação desenvolvida para gerenciar o aluguel de equipamentos, permitindo o cadastro de clientes, equipamentos, tipos de equipamentos e o controle de aluguéis. O sistema facilita a gestão de inventário, o registro de transações e a geração de relatórios, otimizando o processo de aluguel de equipamentos.

---

## 🚀 Funcionalidades

- **Cadastro de Clientes**: Registre e gerencie informações dos clientes.
- **Cadastro de Equipamentos**: Adicione e atualize detalhes dos equipamentos disponíveis para aluguel.
- **Cadastro de Tipos de Equipamentos**: Defina categorias de equipamentos para melhor organização.
- **Controle de Aluguéis**: Registre e gerencie transações de aluguéis, incluindo datas de retirada e devolução.
- **Relatórios**: Gere relatórios de aluguéis, equipamentos disponíveis e clientes.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Armazenamento de Dados**: Arquivos de texto (`.txt`)
- **Ferramentas de Desenvolvimento**: Visual Studio Code
- **Controle de Versão**: Git/GitHub

---

## 📂 Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:

```
sistema-controle-alugueis-equipamentos
├─ codigo
│  ├─ resources
│  │  ├─ Aluguel.txt
│  │  ├─ Clientes.txt
│  │  ├─ Equipamento.txt
│  │  └─ TipoEquipamento.txt
│  └─ src
│     ├─ data
│     │  ├─ AluguelData.class
│     │  ├─ AluguelData.java
│     │  ├─ ClienteData.class
│     │  ├─ ClienteData.java
│     │  ├─ EquipamentoData.class
│     │  ├─ EquipamentoData.java
│     │  ├─ TipoEquipamentoData.class
│     │  └─ TipoEquipamentoData.java
│     ├─ entities
│     │  ├─ Aluguel.class
│     │  ├─ Aluguel.java
│     │  ├─ Cliente.class
│     │  ├─ Cliente.java
│     │  ├─ Constants.java
│     │  ├─ Equipamento.class
│     │  ├─ Equipamento.java
│     │  ├─ TipoEquipamento.class
│     │  └─ TipoEquipamento.java
│     ├─ Main.class
│     └─ Main.java
├─ docs
│  ├─ backlog.md
│  ├─ diagramas
│  │  ├─ Diagrama 0.2.pdf
│  │  ├─ Diagrama_Controle de Aluguéis de Equipamentos.jpeg
│  │  └─ README.md
│  ├─ instrucoes.md
│  ├─ README.md
│  └─ Relatório da Documentação do Projeto.md
├─ LICENSE
└─ README.md
```

---

## 🚀 Como Executar o Projeto

1. **Pré-requisitos**:
   - Java Development Kit (JDK) instalado.
   - Um editor de código como Visual Studio Code ou IntelliJ IDEA.

2. **Clone o Repositório**:
   ```bash
   git clone https://github.com/seu-usuario/sistema-controle-alugueis-equipamentos.git
   ```

3. **Abra o Projeto no Editor de Código**:
   Abra o diretório do projeto no seu editor de código favorito (Visual Studio Code ou IntelliJ IDEA).

4. **Compile e Execute o Projeto**:
   - No terminal do editor de código, navegue até o diretório `src` e compile os arquivos Java:
     ```bash
     javac *.java
     ```
   - Para executar o projeto:
     ```bash
     java Main
     ```

---

## 📜 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

## 📚 Documentação

- **Instruções de Uso**: [instrucoes.md](docs/instrucoes.md)
- **Backlog**: [backlog.md](docs/backlog.md)
