💻 Sobre o projeto
Sistema Console importar pets, listar pets, cadastrar abrigo e listar abrigo que consome uma Api rest Adopet que persiste em banco dados;

⚙️ Funcionalidades
Cadastro de abrigos;
Cadastro de pets do abrigo;
Listagem de pets;
Listagem de pets;

🛠 Tecnologias
As seguintes tecnologias foram utilizadas no desenvolvimento deste sistema de console:
Java 17

⚙️ Start na aplicação
Clonar o projeto
Na pasta rais do projeto
java -jar api.jar - esse comando starta a aplicaçã em rest responsável por cadastrar, listar e importar as funcionalidades;

Após subir a aplicação apresenta as opções abaixo.
DIGITE O NÚMERO DA OPERAÇÃO DESEJADA:
1 -> Listar abrigos cadastrados
2 -> Cadastrar novo abrigo
3 -> Listar pets do abrigo
4 -> Importar pets do abrigo
5 -> Sair

Exemplos de requisições em rest
cadastrar abrigo
http://localhost:8080/abrigos
{
  "nome": "Abrigo Esperança",
  "endereco": "Rua das Flores, 123",
  "email": "contato@abrigoesperanca.com",
  "telefone": "1112345678"
}

listar abrigo
http://localhost:8080/abrigos

importar pet
http://localhost:8080/abrigos/1/pets
{
  "tipo": "CACHORRO",
  "nome": "Rex",
  "raca": "Poodle",
  "idade": 5,
	"cor": "Marron",
	"peso": 8.7
}

listar pets
http://localhost:8080/abrigos/1/pets



