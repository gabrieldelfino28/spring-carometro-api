# Projeto Final: Carometro - Laboratório de Engenharia de Software

## Descrição

Um projeto web, feito usando o string boot e hibernate. O projeto visa a apresentação de carômetros de maneira simples. 
É feita a distinção de administrador e um aluno qualquer, cabendo ao aluno o preenchimento do formulário que será enviado com status pendente para o administrador.
Este pode aprovar ou reprovar os carômetros conforme o necessário. Apenas carômetros aprovados poderão ser expostos para todos os alunos.

* Java Development Kit (JDK).
* (Opcional) IDE, como o VSCode, IntelliJ ou Eclipse (Recomendado).

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

### Executando o Projeto
* Para executar o projeto, é necessário importa-lo numa IDE (Preferêncialmente Eclipse) e Executar a aplicação no ApiApplication.java
  * Detalhe importante: Importante ter Ambiente Maven atualizado e preparado, com lombok instalando no Eclipse, além da JDK Atualizada.
  
* Para que as imagens apareçam de maneira automática no front, recomendamos que os usuários da IDE eclipse alterem a seguinte configuração:
Window -> Preferences -> WorkSpace -> Selecionar "Refresh using native hooks or polling". E depois reiniciar o Eclipse.

* Importante criar o banco de dados mySQL (api_fatec_carometro) antes de Iniciar o Projeto, e trocar a senha no application.properties (src/main/resources/application.properties).
  * Alternativamente, pode-se também mudar o nome do banco, o usuário e a porta do servidor no mesmo arquivo application.properties.
  * As tabelas sempre serão geradas automaticamente por conta do Hibernate.

* Também é possível executar este projeto num ambiente IntelliJ, porém é necessário reiniciar o projeto para o Maven reconhecer as imagens a cada upload.
  Mas é possível configurar um build automático no IntelliJ também.
  * Para mais detalhes: 
    * [StackOverFlow](https://stackoverflow.com/questions/33349456/how-to-make-auto-reload-with-spring-boot-on-idea-intellij)
    * [YouTrack](https://youtrack.jetbrains.com/issue/IDEA-274903/In-IntelliJ-2021.2-compiler.automake.allow.when.app.running-disappear.-Unable-to-enable-live-reload-under-Spring-boot#focus=Comments-27-5089338.0-0)
    * [JetBrains - Documentação Oficial](https://www.jetbrains.com/help/idea/spring-support.html)

## Autores

 - Delfino, Gabriel Cavalcante
 - dos Santos, Rafael Bezerra
 - Severiano, Bianca
 - Silva, Pedro H. Barros
