package br.com.descomplica.projeto.seguranca.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.descomplica.projeto.seguranca.repository.UserRepository;

//Define que essa classe e uma configuracao para o Spring
@Configuration 
//Habilita a seguranca para a api
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private
    UserRepository userRepo;
    @Autowired private
    JWTFilter filter;
    @Autowired private
    UserDetailsServiceImpl uds;

    //Metodo encarregado de configurar a seguranca da API
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http.csrf().disable() // Desabilitando o csrf
                .httpBasic().disable() // Desabilitando a autenticacao por http basico(usuario e senha)
                .cors() // Habilitando o cors
                .and()
                .authorizeHttpRequests() // Autorizando requisicoes de entrada
                .antMatchers("/auth/**", "/h2-console/**").permitAll() // Autorizando requisicoes sem autenticacao para esse endpoint (nesse caso, path /auth/ e uri´s subsequentes
                .antMatchers("/user/**").hasRole("USER") // Autorizando apenas usuarios com o perfil "USER" a utilizar esse endpoint (nesse caso, path /user/ e uri´s subsequentes
                .anyRequest().authenticated() // Todas as requisicoes, exceto para as definidas acima, precisarao ser autenticadas
                .and()
                .userDetailsService(uds) // Setando o servico "user details" (do modulo Security do Spring) para essa implementacao customizada
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
    }

    // Bean (tipo de Service) que sera responsavel por encriptar a senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expondo o bean responsavel pelo gerenciamento do processo de autenticacao
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}