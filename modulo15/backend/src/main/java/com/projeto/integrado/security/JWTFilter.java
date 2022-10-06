package com.projeto.integrado.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Classe anotada como Componente. A partir dessa anotacao o Spring gerenciara a instancia desse Bean
//  e voce podera injeta-lo no codigo, aonde precisar
@Component 
public class JWTFilter extends OncePerRequestFilter {

    // Injecting Dependencies
    @Autowired 
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired 
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Extrai, a partir do header da requisicao, o "Authorization header"
        String authHeader = request.getHeader("Authorization");

        // Checa se o "Auth header" contem um Bearer token
        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            // Extrai informacoes do JWT para valida-lo
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank()){
                // Se o JWT for invalido, aborta a requisicao
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Foi passado um Token JWT inválido no Bearer Header");
            }else {
                try{
                    // Verifica o token recebido e extrai o e-mail
                    String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);

                    // Coleta os dados do usuario a partir do e-mail
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    // Cria o Authentication Token
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

                    // Configura o Contexto de Seguranca com o token acima
                    if(SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }catch(JWTVerificationException exc){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Token JWT Inválido");
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Não foi possível obter os dados do Usuario a partir do Token - "+e);
                }
            }
        }

        // Continua a execucao da cadeia de filtros (caso exista outros filtros definidos)
        filterChain.doFilter(request, response);
    }
}
