package es.ricardo.supermarket_manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ricardo.supermarket_manager.entities.Client;
import es.ricardo.supermarket_manager.security.GestorDeJWT;
import es.ricardo.supermarket_manager.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value= "LoginController", description = "Controller that allows users authentication")
@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ApiOperation(value = "Detect if user's token is invalid when usign Form of type UrlEncoded")
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> login(@RequestParam("name") String username, @RequestParam("password") String pwd) {
		String token = getJWTToken(username,pwd);
		
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user/pass invalid");
		
	}
	
	
	static class UserJsonLogin{
		String name;
		String password;
		public String getName() { return name;};
		public String getPassword() {return password;};
		public void setName(String name ) {this.name = name;};
		public void setPassword(String password ) {this.password = password;};
			
	}
	
	@ApiOperation(value = "Detect if user's token is invalid when usign Form of type Json")
	@PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UserJsonLogin userJson) {		
		String token = getJWTToken(userJson.name, userJson.password);
		
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user/pass invalid");
		
	}	

	@Autowired
	ClientService clientService;
	
	@ApiOperation(value = "Generates token in case login is valid")
	private String getJWTToken(String username, String passTextoPlanoRecibida) {
		
		String respuesta = null;
		
		GestorDeJWT gestorDeJWT = GestorDeJWT.getInstance();
		
		Client client = clientService.findByName(username);
		        
        String passwordUsuarioEnHash = "";
        boolean autenticado = false;
        
        if(client != null) { 
        	passwordUsuarioEnHash = client.getPassword();
        	
        	autenticado = BCrypt.checkpw(passTextoPlanoRecibida, passwordUsuarioEnHash);      	
        }  
      
		if(autenticado) {				
			String rol = "ROLE_USER";
			List<String> roles = new ArrayList<String>(); 
			roles.add(rol);
			logger.info("los roles obtenidos: " + roles);			

			int duracionMinutos = 600;
			
			String token = gestorDeJWT.generarToken(username, roles, duracionMinutos);
			
			respuesta = gestorDeJWT.BEARERPREFIX + token;			
		}
		
		return respuesta;
	}	
}
