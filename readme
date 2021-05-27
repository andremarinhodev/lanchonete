# Lanchone-API

## Resumo

API Restfull de uma aplicação de gerenciamento de pedidos de uma lanchonete.

## Tecnologias utilizadas:
	- Java Spring Boot; 
	- JWT para autenticação/autorização;
	- Banco de dados PostgreSQL e h2(apenas no profile dev com intuito de testar a aplicação);
	- Senhas criptografadas no banco de dados utilizando BCrypt;
	- API documentada utilizando Swagger2 com o auxílio do SpringFox

## Setup da aplicação

- Importe o projeto maven e espere baixar todas as dependências;
- Crie uma database no PostgreSQL com o nome "lanchonete_db";
- No arquivo "aplication-prod.properties" edite os campos "spring.datasource.username" e "spring.datasource.password" para ficar de acordo com o seu login de usuário do PostgreSQL.

## Documentação da API

Toda a documentação foi feita através do Swagger2 e pode ser visualizada e testada no caminho http://localhost:8080/swagger-ui.html.

## Perfis disponíveis

- Existem 2 perfis na aplicação, prod e dev. Por padrão a aplicação já vem no perfil prod, que é o perfil aonde se encontram todas as funcionalidades.
- No perfil dev a camada de segurança da aplicação é removida, não sendo necessário a passagem do token JWT, agilizando alguns testes, porém a aplicação real se encontra no perfil prod.
- Para alterar o perfil, deve-se apenas editar o campo "spring.profiles.active" no arquivo "aplication.properties".

## Instruções para Teste

- Ao cadastrar um cliente o campo dataNascimento deve ser enviado no padrão yyyy-MM-dd (ex: 1996-07-27)
- Caso esteja no perfil prod, ao enviar o Token JWT, lembre-se de colocar o prefixo "Bearer " antes do token (ex: Bearer mdsmqk12n3knsakdj1kj23klaskdjxqlnfghkl2...)
