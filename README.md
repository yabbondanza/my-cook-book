# myCookBook - Documentação do Projeto

## Equipe

- Bárbara Ferreira Rodrigues
- Michel Alexandrino de Souza
- Yasmim Danzieri Abbondanza Laurentino

## Visão Geral

- [Descrição do Projeto](#descrição-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Uso de Branches](#uso-de-branches)
- [Regras de Commit](#regras-de-commit)
- [Convenções Adotadas](#convenções-adotadas)
- [Funcionalidades](#funcionalidades)

## Descrição do Projeto

O projeto "myCookBook" é uma aplicação web que tem como objetivo conectar entusiastas da culinária, permitindo que eles compartilhem, salvem e curtam receitas. Inspirado no conceito de uma rede social, esta plataforma oferece aos usuários a capacidade de criar, experimentar, aprimorar e compartilhar suas receitas favoritas. Além disso, o sistema facilita a criação e compartilhamento de versões personalizadas de receitas, como versões vegetarianas, sem lactose e muito mais.

## Tecnologias Utilizadas

- **Backend**: Java 17 e Spring Boot 3.1.2
- **Frontend**: JavaScript, ReactJS 18.2.0, Bootstrap 5.2.3, React-Bootstrap 2.7.4
- **Banco de Dados**: MySQL 5.0.12
- **Servidor**: Free SQL Database

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **api**: Contém o backend da aplicação.
- **app**: Contém o frontend da aplicação.
- **Padrões Adotados**: Contém documentos de padrões adotados pelo projeto.
- **Requisitos**: Contém documentos de requisitos do projeto.
- **Diagramas**: Contém diagramas pertinentes ao projeto.

```plaintext
my-cook-book/
│
├── api/
│
├── app/
│
├── Padrões Adotados/
│   └── Regras de Verificação e Análise de Requisitos.md
│
├── Requisitos/
│   └── Documento de Requisitos - myCookBook.doc
│   └── Documento de Requisitos - myCookBook.pdf
│
├── Diagramas/
│   └── Diagrama de Classes.png
│   └── Diagrama de Implantação.png
│   └── Diagramas de Sequência/
│       └── ...
│   └── Diagrama de Pacotes.png
```

## Uso de Branches

- **Feat**: Esse padrão é usado para criar novas funcionalidades. O nome da branch deve iniciar com o sufixo feat/ seguido de um título conciso, com no máximo 4 palavras.

Exemplo:
```plaintext
feat/novo-botão-de-salvar
```

- **Fix**: Esse padrão é usado para corrigir bugs ou problemas. O nome da branch deve iniciar com o sufixo fix/ seguido de um título conciso, com no máximo 4 palavras.

Exemplo:
```plaintext
fix/erro-de-conversão-de-data
```

## Regras de Commit

Padrões de commit adotados: **fix** e **feature** no início da mensagem de commit.

- **Fix**: Esse padrão é usado para corrigir bugs ou problemas. A descrição deve fornecer mais detalhes sobre o problema que foi corrigido e como ele foi resolvido.

- **Feature**: Esse padrão é usado para adicionar novas funcionalidades ao projeto. A descrição deve fornecer mais detalhes sobre a funcionalidade que foi adicionada.

## Convenções Adotadas

- **CamelCase para Nomes de Variáveis e Funções**: Utilização de camelCase para nomes de variáveis e funções. Por exemplo: nomeDaVariavel, nomeDaFuncao(). 

- **Padrão de Nomenclatura Descritivo**: Nomes descritivos e significativos para variáveis, funções e arquivos, evitando abreviações desnecessárias.

- **Documentação Clara e Concisa**: Documentação detalhada, especialmente para funções e métodos. Comentários explicativos para destacar partes do código que podem não ser imediatamente óbvias.

- **Estrutura de Diretórios Organizada**: Estrutura de diretórios organizada e lógica. 

- **Utilização de .gitignore**: Um arquivo .gitignore para evitar que arquivos desnecessários, como arquivos de compilação, arquivos temporários e dependências, sejam enviados ao repositório. Mantendo o repositório mais limpo e evitando problemas relacionados a arquivos indesejados.

## Funcionalidades

O "myCookBook" oferece uma variedade de funcionalidades para os usuários:

1. **Cadastro de Usuário**: Os usuários podem criar uma conta para acessar a plataforma e aproveitar todas as funcionalidades.

2. **Compartilhamento de Receitas**: Os usuários podem criar e compartilhar suas receitas com a comunidade. Eles podem incluir detalhes sobre os ingredientes, instruções de preparo, fotos e outras informações relevantes.

3. **Salvamento de Receitas**: Os usuários podem salvar suas receitas favoritas em um livro de receitas pessoal, para acesso rápido e fácil no futuro.

4. **Curtidas**: Os usuários podem expressar seu apreço por uma receita curtindo-a, criando uma interação social na plataforma.

5. **Versões Personalizadas**: A plataforma permite que os usuários criem versões personalizadas de receitas. Por exemplo, eles podem compartilhar uma versão vegetariana de uma receita existente.

6. **Exploração de Receitas**: Os usuários podem explorar receitas compartilhadas por outros membros da comunidade com base em categorias, ingredientes e preferências.

7. **Comentários**: Os usuários podem deixar comentários nas receitas, compartilhando dicas, truques e feedback com outros membros.

---

Projeto desenvolvido para a disciplina Engenharia de Software, realizada na Universidade Federal de Lavras.