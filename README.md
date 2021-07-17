# bank-hexagonal-architecture

<h6>CAMADA 1, DOMÍNIO:</h6>

    module: bank-domain
    app.projetaria.bank.domain.Account

<h6>CAMADA 2, SERVIÇOS DE DOMÍNIO:</h6>

    module: bank-domain-service
    app.projetaria.bank.domainservices.Transfer

<h6>CAMADA 3, CASOS DE USO:</h6>

    module: bank-service
    app.projetaria.bank.domainservices.Transfer

<h6>CAMADA 4, SERVIÇOS:</h6>

    module: bank-service
    app.projetaria.bank.domainservices.Transfer

Referências: <br>

<h6>Hexagonal</h6>
https://www.udemy.com/course/arquitetura-hexagonal-com-java-1/ <br>
https://reflectoring.io/spring-hexagonal/

<h6>Beans</h6>
https://reflectoring.io/spring-boot-conditionals/