package com.projeto.integrado.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.integrado.entity.User;

//Classe anotada como Componente. A partir dessa anotacao o Spring gerenciara a instancia desse Bean
//e voce podera injeta-lo no codigo, aonde precisar
@Component 
public class JWTUtil {

    // Le a propriedade jwt-secret a partir do application.properties
    @Value("${jwt-secret}")
    private String secret;

    // Le a propriedade jwt-subject a partir do application.properties
    @Value("${jwt-subject}")
    private String subject;

    // Le a propriedade jwt-company-project-name a partir do application.properties
    @Value("${jwt-company-project-name}")
    private String companyProjectName;

    // Metodo utilizado no login e para criar o JWT contendo alguns dados gerais e o e-mail do usuario
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(subject)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer(companyProjectName)
                .sign(Algorithm.HMAC256(secret));
    }

    // Metodo utilizado no login e para criar o JWT contendo todos os dados do usuario, exceto sua senha
    public String generateTokenWithUserData(User user) throws IllegalArgumentException, JWTCreationException {
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;
        try {
            userJson = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return JWT.create()
                .withSubject(subject)
                .withClaim("usuario", userJson)
                .withIssuedAt(new Date())
                .withIssuer(companyProjectName)
                .sign(Algorithm.HMAC256(secret));
    }

    // Metodo para verificar o JWT e extrair as informacoes contidas no mesmo (no payload)
    public String validateTokenAndRetrieveSubject(String token) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(companyProjectName)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        //Pegando o email dentro da chave usuario (string json que contem os dados do usuario)
        User user = new User();
        try {
            user = mapper.readValue(jwt.getClaim("usuario").asString(), User.class);
        } catch (JsonProcessingException e) {
            throw new Exception("Ocorreu um erro e nao foi possivel converter o usario a partir da string json - " + e);
        }

        //System.out.println("Email: " + user.getEmail());
        //return jwt.getClaim("email").asString();
        return user.getUserEmail();
    }

}
