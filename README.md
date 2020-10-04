# OAuth2Demo

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
 
For implementating resources server configuration you will need to look at ResourceServerConfig.java

Form based login is disabled so you will be able to access authorization and resource server only by post requests.

etc folder contains requests that can be imported directly into postman app.

**Branchs:**

oauthServer_and_resource_server branch contains running code for Authorization and Resource creation using OAuth2.
enable_cors branch is specifically for testing purpose and to allow CORS when request resources from localhost.
react_oauth2_demo contains code for basic sample request from React App. 

Have Fun :) !!!! 

