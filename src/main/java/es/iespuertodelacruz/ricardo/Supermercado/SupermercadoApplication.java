package es.iespuertodelacruz.ricardo.Supermercado;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.cors.CorsUtils;

import es.iespuertodelacruz.ricardo.Supermercado.security.FiltroJWT;

@SpringBootApplication
public class SupermercadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermercadoApplication.class, args);
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		public void configure(WebSecurity webSecurity) throws Exception
		{
			webSecurity
			.ignoring()
			.antMatchers(HttpMethod.POST, "/api/login")
            .antMatchers("/api/v1/**")
            //.antMatchers("/api/**")
            ;
           
    }	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http 
        .addFilterBefore(new CustomCorsFilter(), WebAsyncManagerIntegrationFilter.class)
		.csrf().disable()
		.addFilterBefore(new FiltroJWT(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.requestMatchers(CorsUtils::isCorsRequest).permitAll()
		.antMatchers(HttpMethod.OPTIONS, "**").permitAll()				
	//	.antMatchers("/api/v3/**").hasRole("ADMIN")
		.antMatchers("/api/v2/**").authenticated()
		.anyRequest().authenticated();	   
 
    	//excepción para no autenticado ( token no válido )
    	//esta excepción no se desencadena para roles incorrectos
    	//la usamos únicamente para personalizar el mensaje
    	//e informar de por qué no se ha dejado pasar la request
    	http
    	 .exceptionHandling()
    	 .authenticationEntryPoint((request, response, e) ->
    	 {

    	response.setContentType("application/json;charset=UTF-8");
    	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    	 response.getWriter().write(new JSONObject()
    	 .put("timestamp", LocalDateTime.now())
    	 .put("message", "token no correctamente autenticado")
    	 .toString());
    	 });
    	}
    }
}