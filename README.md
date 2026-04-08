# 🚀 API CRUD de Funcionários - Spring Boot
🎓 **Projeto Acadêmico – Faculdade UNIESP**  
*Atividade 2 (Unidade 1) + Exercício Prático CRUD*

---

## 📖 Sobre o Projeto
Este repositório contém o desenvolvimento de uma **API RESTful completa para gerenciamento de funcionários**, criada para atender às atividades acadêmicas da Unidade 1. O projeto consolida conhecimentos em Java, Spring Boot e boas práticas de desenvolvimento de software.

### 🎯 Objetivos
- ✅ **Atividade 2 (Unidade 1)**: Implementação de estrutura MVC com Spring Boot
- ✅ **Exercício CRUD**: Operações completas de Criar, Ler, Atualizar e Excluir funcionários
- ✅ Aplicação de validações, tratamento de erros e persistência de dados

---

## ✨ Funcionalidades
- 📝 **Cadastro de Funcionários**: Criação de novos registros com validação de dados
- 👁️ **Listagem**: Consulta de todos os funcionários ou por ID
- ✏️ **Atualização**: Edição de informações existentes
- 🗑️ **Exclusão**: Remoção de registros do sistema
- ⚠️ **Tratamento de Erros**: Respostas padronizadas para exceções
- 💾 **Persistência**: Banco de dados H2 em memória

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia        | Versão | Descrição                          |
|-------------------|--------|------------------------------------|
| ☕ Java           | 17+    | Linguagem de programação           |
| 🌿 Spring Boot    | 3.x    | Framework principal                |
| 💾 Spring Data JPA| -      | ORM e repositórios                 |
| 📦 Maven          | -      | Gerenciamento de dependências      |
| 🗄️ H2 Database    | -      | Banco em memória                   |
| 🔧 Spring Validation | -   | Validação de dados                 |

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/uniesp/funcionario/
│   │   ├── controller/       # Controladores REST
│   │   ├── service/          # Regras de negócio
│   │   ├── repository/       # Camada de dados
│   │   ├── model/            # Entidades/JPA
│   │   └── exception/        # Tratamento de erros
│   └── resources/
│       ├── application.properties
│       └── data.sql
└── test/                     # Testes unitários
```

---

## 🚀 Como Executar

### 📋 Pré-requisitos
- ✅ JDK 17 ou superior
- ✅ Maven 3.6+
- ✅ Git

### ▶️ Passo a Passo
```bash
# 1. Clone o repositório
git clone [URL_DO_SEU_REPOSITORIO]

# 2. Acesse o diretório
cd [NOME_DA_PASTA]

# 3. Compile e execute a aplicação
./mvnw spring-boot:run        # Linux/macOS
mvnw.cmd spring-boot:run      # Windows

# 4. Acesse a API no navegador ou Postman
http://localhost:8080/api/funcionarios
```

### 💡 Console H2
Acesse o banco de dados em:
```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:funcionarios
Usuário: sa
Senha: (deixe em branco)
```

---

## 📝 Endpoints da API

| Método   | Endpoint                  | Descrição                  |
|----------|---------------------------|----------------------------|
| `POST`   | `/api/funcionarios`       | Criar novo funcionário     |
| `GET`    | `/api/funcionarios`       | Listar todos               |
| `GET`    | `/api/funcionarios/{id}`  | Buscar por ID              |
| `PUT`    | `/api/funcionarios/{
