# Literalura - Sistema de Gerenciamento de Livros

O Literalura é uma aplicação desenvolvida em Java com Spring Boot, destinada a consumir dados de uma API externa (Gutenberg) e gerenciar informações sobre livros.

- Buscar e armazenar dados de livros.  
- Listar livros e autores registrados em um repositório local.  
- Consultar informações como título, idioma, autores e contagem de downloads.

## Ferramentas
- Java Development Kit (JDK) - Versão 11 ou superior.  
- Maven - Ferramenta de gerenciamento de dependências.

## Configuração
- **Banco de dados:** MySQL  
- **API externa:** Gutenberg  

### Como Rodar o Projeto:

* Passo 1: Clone o repositório
git clone https://github.com/seu-usuario/literalura.git

* Passo 2: Configure o projeto
Verifique o arquivo application.properties para ajustes de configuração da API e do banco de dados:

* Passo 3: Compile e execute o projeto

* Passo 4: Uso da aplicação
Após a execução, você verá um menu com as seguintes opções:
Escolha o número da sua opção:
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos por determinado ano
5 - Listar livros em um determinado idioma
0 - Sair

Digite o número correspondente para a ação desejada.
Exemplo: Para buscar um livro, escolha a opção 1 e insira o título.

## Dependências Utilizadas:
Spring Boot - Framework para desenvolvimento ágil e robusto.
Jackson - Biblioteca para deserialização de JSON.
Maven - Gerenciador de dependências.


## Contribuições
Quer contribuir com o projeto? Veja como:
1. **Fork** este repositório.
2. **Crie uma nova branch:** `git checkout -b nova-funcionalidade`
3. **Faça suas alterações.**
4. **Faça um commit:** `git commit -m "Nova funcionalidade"`
5. **Envie um Pull Request.**

