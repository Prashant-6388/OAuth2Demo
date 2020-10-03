**Spring Boot OAuth2 Auth Server and Resources Server Demo**

This projects aim to give sample template for using oauth2 for Authorization
and Resource server.

**Technologies used** 

-Spring boot 2
-OAuth2 
-Java 11
-MySql
-Postman(Optional, only for testing purpose)

**Description:**

For implemented OAuth as your authorization server we need to use dependency 

`<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.2.9.RELEASE</version> 
 </dependency>`

AuthorizationServerConfig and BasicSecurityConfig implements OAuth Authorization server.

Here we are using MySql DB to store details for client id and secret.
We have configured db based authentication by implementing UserDetailsService of spring security in our class UserDetailsServiceImpl.

You might want to enable `spring.datasource.initialization-mode` in application.properties to execute queries data.sql which will setup all required tables and data for executing post requests.
 
etc folder contains json exported from Postman, you can import it into you postman app and execute request.

For implementating resources server configuration you will need to look at ResourceServerConfig.java

Form based login is disabled so you will be able to access authorization and resource server only by post requests.

Have Fun :) !!!! 


