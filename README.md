# 🛒 API de Gerenciamento de Produtos

API RESTful desenvolvida com Spring Boot para gerenciamento completo de produtos de uma loja, oferecendo operações CRUD, controle de estoque e gerenciamento de status de produtos.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Data JPA**
- **MySQL 8**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**
- **Maven**

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- Java JDK 21 ou superior
- Maven 3.6+
- MySQL 8+

## 🔧 Configuração do Ambiente

### 1. Clone o repositório

```bash
git clone https://github.com/WyldSLA/api-produtos.git
cd api-produtos
```

### 2. Configure o Banco de Dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE loja CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configure as credenciais

Edite o arquivo `src/main/resources/application.yml` com suas credenciais do MySQL:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/loja?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: seu_usuario
    password: sua_senha
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:4050/api/`

## 🏗️ Estrutura do Projeto

```
api-produtos/
├── src/
│   ├── main/
│   │   ├── java/com/wyldSLA/produtos/
│   │   │   ├── controller/
│   │   │   │   └── ProdutoController.java       # Endpoints REST
│   │   │   ├── dto/
│   │   │   │   ├── ProdutoCreateDto.java        # DTO de entrada
│   │   │   │   └── ProdutoResponseDto.java      # DTO de resposta
│   │   │   ├── model/
│   │   │   │   └── ProdutoModel.java            # Entidade JPA
│   │   │   ├── repository/
│   │   │   │   └── ProdutoRepository.java       # Repositório JPA
│   │   │   ├── service/
│   │   │   │   ├── ProdutoService.java          # Interface
│   │   │   │   └── impl/
│   │   │   │       └── ProdutoServiceImpl.java  # Implementação
│   │   │   └── mapper/
│   │   │       └── ProdutoMapper.java           # Mapper
│   │   └── resources/
│   │       └── application.yml                  # Configurações
│   └── test/
├── pom.xml                                      # Dependências Maven
└── README.md                                    # Documentação
```

### Arquitetura em Camadas

A aplicação segue o padrão de arquitetura em camadas:

1. **Controller**: Recebe requisições HTTP e retorna respostas
2. **Service**: Contém a lógica de negócio
3. **Repository**: Gerencia o acesso aos dados
4. **Model**: Representa as entidades do banco de dados
5. **DTO**: Define os objetos de transferência de dados
6. **Mapper**: Converte entre entidades e DTOs

## 📡 Endpoints da API

### Base URL
```
http://localhost:4050/api
```

### Operações Disponíveis

| Método | Endpoint | Descrição | Status Code |
|--------|----------|-----------|-------------|
| POST | `/produtos` | Criar um novo produto | 201 Created |
| GET | `/produtos` | Listar todos os produtos | 200 OK |
| GET | `/produtos/{id}` | Buscar produto por ID | 200 OK |
| PUT | `/produtos/{id}` | Atualizar produto completo | 200 OK |
| PATCH | `/produtos/{id}/status` | Atualizar status do produto | 200 OK |
| DELETE | `/produtos/{id}` | Deletar um produto | 204 No Content |

---

## 📝 Detalhamento dos Endpoints

### 1. Criar Produto

**Requisição:**
```http
POST /api/produtos
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Notebook Dell Inspiron",
  "descricao": "Notebook com processador Intel i7, 16GB RAM, 512GB SSD",
  "preco": 3499.99,
  "quantidadeEstoque": 25,
  "categoria": "Eletrônicos"
}
```

**Validações:**
- `nome`: obrigatório, não pode estar vazio
- `preco`: obrigatório, deve ser um valor positivo
- `quantidadeEstoque`: obrigatório, deve ser um número não negativo
- `descricao`: opcional
- `categoria`: opcional
- `ativo`: definido automaticamente como `true`

**Resposta de Sucesso (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Notebook Dell Inspiron",
  "descricao": "Notebook com processador Intel i7, 16GB RAM, 512GB SSD",
  "preco": 3499.99,
  "quantidadeEstoque": 25,
  "categoria": "Eletrônicos",
  "ativo": true,
  "dataCriacao": "2024-12-01T10:30:00",
  "dataAtualizacao": "2024-12-01T10:30:00"
}
```

---

### 2. Listar Todos os Produtos

**Requisição:**
```http
GET /api/produtos
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "nome": "Notebook Dell Inspiron",
    "descricao": "Notebook com processador Intel i7",
    "preco": 3499.99,
    "quantidadeEstoque": 25,
    "categoria": "Eletrônicos",
    "ativo": true,
    "dataCriacao": "2024-12-01T10:30:00",
    "dataAtualizacao": "2024-12-01T10:30:00"
  },
  {
    "id": "660e8400-e29b-41d4-a716-446655440001",
    "nome": "Mouse Logitech MX Master",
    "descricao": "Mouse ergonômico sem fio",
    "preco": 299.90,
    "quantidadeEstoque": 50,
    "categoria": "Periféricos",
    "ativo": true,
    "dataCriacao": "2024-12-01T11:00:00",
    "dataAtualizacao": "2024-12-01T11:00:00"
  }
]
```

---

### 3. Buscar Produto por ID

**Requisição:**
```http
GET /api/produtos/{id}
```

**Exemplo:**
```http
GET /api/produtos/550e8400-e29b-41d4-a716-446655440000
```

**Resposta de Sucesso (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Notebook Dell Inspiron",
  "descricao": "Notebook com processador Intel i7, 16GB RAM, 512GB SSD",
  "preco": 3499.99,
  "quantidadeEstoque": 25,
  "categoria": "Eletrônicos",
  "ativo": true,
  "dataCriacao": "2024-12-01T10:30:00",
  "dataAtualizacao": "2024-12-01T10:30:00"
}
```

**Resposta de Erro (404 Not Found):**
```json
{
  "timestamp": "2024-12-01T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Produto não encontrado",
  "path": "/api/produtos/550e8400-e29b-41d4-a716-446655440000"
}
```

---

### 4. Atualizar Produto

**Requisição:**
```http
PUT /api/produtos/{id}
Content-Type: application/json
```

**Exemplo:**
```http
PUT /api/produtos/550e8400-e29b-41d4-a716-446655440000
```

**Body:**
```json
{
  "nome": "Notebook Dell Inspiron 15",
  "descricao": "Notebook atualizado com 32GB RAM",
  "preco": 3999.99,
  "quantidadeEstoque": 20,
  "categoria": "Eletrônicos"
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Notebook Dell Inspiron 15",
  "descricao": "Notebook atualizado com 32GB RAM",
  "preco": 3999.99,
  "quantidadeEstoque": 20,
  "categoria": "Eletrônicos",
  "ativo": true,
  "dataCriacao": "2024-12-01T10:30:00",
  "dataAtualizacao": "2024-12-01T14:00:00"
}
```

**Observações:**
- Todos os campos devem ser enviados
- O campo `dataAtualizacao` é automaticamente atualizado
- O campo `dataCriacao` permanece inalterado

---

### 5. Atualizar Status do Produto

**Requisição:**
```http
PATCH /api/produtos/{id}/status?ativo=false
```

**Exemplo:**
```http
PATCH /api/produtos/550e8400-e29b-41d4-a716-446655440000/status?ativo=false
```

**Parâmetros de Query:**
- `ativo`: boolean (`true` ou `false`)

**Resposta de Sucesso (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "nome": "Notebook Dell Inspiron 15",
  "descricao": "Notebook atualizado com 32GB RAM",
  "preco": 3999.99,
  "quantidadeEstoque": 20,
  "categoria": "Eletrônicos",
  "ativo": false,
  "dataCriacao": "2024-12-01T10:30:00",
  "dataAtualizacao": "2024-12-01T15:00:00"
}
```

**Casos de Uso:**
- Ativar produto: `?ativo=true`
- Desativar produto: `?ativo=false`
- Útil para soft delete ou controle de disponibilidade

---

### 6. Deletar Produto

**Requisição:**
```http
DELETE /api/produtos/{id}
```

**Exemplo:**
```http
DELETE /api/produtos/550e8400-e29b-41d4-a716-446655440000
```

**Resposta de Sucesso (204 No Content):**
```
(Sem corpo na resposta)
```

**Observações:**
- Remove permanentemente o produto do banco de dados
- Para desativar temporariamente, use o endpoint de atualização de status

---

## 📖 Documentação da API (Swagger)

Acesse a documentação interativa através do Swagger UI para testar os endpoints diretamente no navegador:

**Swagger UI:**
```
http://localhost:4050/api/swagger-ui/index.html
```

**OpenAPI JSON:**
```
http://localhost:4050/api/v1/api-docs
```

O Swagger UI oferece:
- Interface interativa para testar todos os endpoints
- Documentação automática de todos os DTOs e modelos
- Exemplos de requisições e respostas
- Validações e constraints de cada campo
- Schemas completos dos objetos

---

## 📊 Modelo de Dados

### Entidade Produto

| Campo | Tipo | Descrição | Constraints |
|-------|------|-----------|-------------|
| id | UUID | Identificador único do produto | PK, auto-gerado |
| nome | String | Nome do produto | NOT NULL |
| descricao | Text | Descrição detalhada | Opcional |
| preco | BigDecimal | Preço do produto | NOT NULL, positivo |
| quantidadeEstoque | Integer | Quantidade disponível | NOT NULL, >= 0 |
| categoria | String | Categoria do produto | Opcional |
| ativo | Boolean | Status do produto | Default: true |
| dataCriacao | LocalDateTime | Data de criação | Auto-gerado |
| dataAtualizacao | LocalDateTime | Data da última atualização | Auto-atualizado |

### Diagrama de Tabela

```sql
CREATE TABLE produtos (
    id BINARY(16) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    categoria VARCHAR(100),
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL
);
```

---

## 🛠️ Funcionalidades

- ✅ CRUD completo de produtos
- ✅ Validação de dados com Bean Validation
- ✅ Controle de estoque
- ✅ Gerenciamento de status (ativo/inativo)
- ✅ Categorização de produtos
- ✅ Persistência com JPA/Hibernate
- ✅ IDs únicos com UUID
- ✅ Logs SQL formatados
- ✅ Hot reload com DevTools
- ✅ Documentação automática com Swagger
- ✅ Timestamps automáticos (criação/atualização)

---

## 🔍 Recursos Adicionais

### Controle de Estoque
A API mantém controle automático do estoque através do campo `quantidadeEstoque`, permitindo:
- Verificação de disponibilidade
- Atualização de quantidades
- Alertas de estoque baixo (implementável)

### Sistema de Status
O campo `ativo` permite:
- Soft delete de produtos
- Controle de visibilidade no catálogo
- Desativação temporária sem perder dados históricos

### Auditoria
Campos de auditoria automática:
- `dataCriacao`: Registra quando o produto foi criado
- `dataAtualizacao`: Atualiza automaticamente em cada modificação

---

## 🧪 Testes

Execute os testes com:

```bash
# Testes unitários
mvn test

# Testes com cobertura
mvn test jacoco:report

# Executar aplicação para testes manuais
mvn spring-boot:run
```

---

## 📦 Build para Produção

Para gerar o arquivo JAR executável:

```bash
mvn clean package
```

O arquivo será gerado em: `target/produtos-0.0.1-SNAPSHOT.jar`

Para executar:

```bash
java -jar target/produtos-0.0.1-SNAPSHOT.jar
```

---

## 🚀 Melhorias Futuras

Sugestões de funcionalidades para expandir a API:

- [ ] Paginação e filtros avançados
- [ ] Busca por nome e categoria
- [ ] Ordenação por preço, nome, data
- [ ] Sistema de imagens de produtos
- [ ] Histórico de alterações de preço
- [ ] Alertas de estoque baixo
- [ ] Categorias hierárquicas
- [ ] Desconto e promoções
- [ ] Integração com gateway de pagamento
- [ ] Autenticação e autorização
- [ ] Rate limiting
- [ ] Cache com Redis

---

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`)
3. Commit suas mudanças (`git commit -m 'Add: nova feature'`)
4. Push para a branch (`git push origin feature/NovaFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto é de código aberto e está disponível sob a licença MIT.

---

## 👤 Autor

**WyldSLA**

- GitHub: [@WyldSLA](https://github.com/WyldSLA)

---

## 📞 Suporte

Para reportar bugs ou solicitar novas funcionalidades, abra uma [issue](https://github.com/WyldSLA/api-produtos/issues).

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!
