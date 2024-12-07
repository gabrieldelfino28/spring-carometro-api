# Projeto Final: Carometro - Laboratório de Engenharia de Software

## Descrição

Um projeto web, feito usando o string boot e hibernate. O projeto visa a apresentação de carômetros de maneira simples. 
É feita a distinção de administrador e um aluno qualquer, cabendo ao aluno o preenchimento do formulário que será enviado com status pendente para o administrador.
Este pode aprovar ou reprovar os carômetros conforme o necessário. Apenas carômetros aprovados poderão ser expostos para todos os alunos.

* Java Development Kit (JDK).
* (Optional) IDE, such as VSCode, NetBeans or Eclipse.

### Estrutura do Projeto
````
Carometro
├── bin/
├── src/
│   ├── main/
│   │   ├── java/
│   |   |   ├── evento/ 
│   |   |   |   ├── fatec/ 
│   |   |   |   |   ├── api/
│   |   |   |   |   |   ├── aluno/
│   |   |   |   |   |   ├── comentario/
│   |   |   |   |   |   ├── curso/
│   |   |   |   |   |   ├── historico/
│   |   |   |   |   |   ├── link/
│   |   |   |   |   |   ├── turma/
│   |   |   |   |   |   ├── controller/
│   |   |   |   |   |   |   ├── admin/
│   |   |   |   |   ├── ApiApplication.java
│   │   ├── resources/
│   |   |   ├── static/ 
│   |   |   ├── templates/ 
│   |   |   ├── application.properties 
├── .gitignore
└── README.md
````

### Executando o Projeto!!!
* Para executar o projeto, é necessário importa-lo numa IDE (Preferêncialmente Eclipse) e Executar a aplicação no ApiApplication.java

* Detalhe importante: Importante ter Ambiente Maven atualizado e preparado, com lombok instalando.
  
* Para que as imagens apareçam de maneira automática no front, recomendamos que os usuários da IDE eclipse alterem a seguinte configuração:
window > preferences > WorkSpace > Selecionar "Refresh using native hooks or polling".

* Importante criar o banco de dados mySQL antes de Iniciar o Projeto, e trocar a senha no application.properties (src/main/resources/application.properties).

## Autores

 - Delfino, Gabriel Cavalcante
 - dos Santos, Rafael Bezerra
 - Severiano, Bianca
 - Silva, Pedro H. Barros
