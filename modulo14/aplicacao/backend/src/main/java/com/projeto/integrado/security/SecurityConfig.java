package com.projeto.integrado.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig{

    @Autowired 
    private JWTFilter filter;
    @Autowired 
    private UserDetailsServiceImpl uds;

    //Metodo encarregado de configurar a seguranca da API
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desabilitando o csrf
                .httpBasic().disable() // Desabilitando o http basico
                .cors() // Habilitando o cors
                .and()
                .authorizeHttpRequests() // Autorizando requisicoes de entrada
                .antMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/**").permitAll() // Autorizando requisicoes autenticadas
                .antMatchers("/user/**").hasRole("USER") // Autorizando apenas usuarios com o perfil "USER" a utilizar esse endpoint
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(uds) // Setando o servico "user details" para essa implementacao customizada
                .exceptionHandling()
                .authenticationEntryPoint(
                        // Rejeitando requisicoes nao autorizadas que chegam ate esse ponto.
                        //  Logo, isso significa que essa requisicao requer autenticacao e um JWT valido
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configurando a Sessao para que cada requisicao seja independente (stateless)

        // Adicionando o filtro JWT
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean (tipo de Service) que sera responsavel por encriptar a senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expondo o bean responsavel pelo gerenciamento do processo de autenticacao
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}