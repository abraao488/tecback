# 🚀 API CRUD com Spring Boot

> 🎓 **Projeto Acadêmico** – Faculdade UNIESP  
> Desenvolvido para consolidar conhecimentos em desenvolvimento de APIs RESTful com Java e Spring Boot.

---

## 📖 Sobre o Projeto
Esta aplicação consiste em um sistema backend completo com operações de **CRUD** (Create, Read, Update, Delete), estruturado seguindo boas práticas de arquitetura, separação de responsabilidades e padrões de projeto. O foco foi a qualidade do código, organização em camadas e utilização de ferramentas modernas do ecossistema Java.

---

## ✨ Funcionalidades
- ✅ Criação, listagem, atualização e exclusão de recursos via API REST
- ✅ Validação de dados e tratamento padronizado de erros
- ✅ Persistência em banco de dados em memória (H2)
- ✅ Estrutura em camadas (`Controller` → `Service` → `Repository` → `Model/Entity`)
- ✅ Configuração pronta para desenvolvimento local e testes rápidos

---

## 🛠️ Tecnologias Utilizadas
| Tecnologia        | Função                                  |
|-------------------|------------------------------------------|
| ☕ Java 17+        | Linguagem base                           |
| 🌿 Spring Boot    | Framework para criação da aplicação      |
| 💾 Spring Data JPA| Mapeamento objeto-relacional e repositórios |
| 📦 Maven          | Gerenciamento de dependências e build    |
| 🗄️ H2 Database    | Banco de dados em memória para desenvolvimento |

---

## 👥 Integrantes do Grupo
| Nome     |
|----------|
| Abraão   |
| Joalis   |
| Brenner  |
| Hrasam   |
| Gustavo  |

---

## 🚀 Como Executar o Projeto

### 📋 Pré-requisitos
- JDK 17 ou superior
- Maven instalado (ou use o `mvnw` incluso)
- Git (para clonar o repositório)

### ▶️ Passo a Passo
```bash
# 1. Clone o repositório
git clone [URL_DO_SEU_REPOSITORIO]

# 2. Acesse a pasta do projeto
cd [NOME_DA_PASTA]

# 3. Compile e execute a aplicação
./mvnw spring-boot:run   # Linux/macOS
mvnw.cmd spring-boot:run # Windows
