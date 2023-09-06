package es.iespuertodelacruz.ricardo.Supermercado.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
@Scope(value = "singleton")
public class GestorDeJWT {
	public final String AUTHORIZATIONHEADER = "Authorization";
	public final String BEARERPREFIX = "Bearer ";
	public final String ROLSCLAIMS = "authorities";
	
    private static GestorDeJWT gestorDeJWT = null;

    private Key key;
    private long dateCreationKey;

    private GestorDeJWT() {
        if (key == null) {
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            dateCreationKey = System.currentTimeMillis();
        }
    }

    public long antiguedadDeLaClaveEnMillis() {
        long tiempo = -1;
        if (key != null) {
            tiempo = System.currentTimeMillis() - dateCreationKey;
        }
        return tiempo;
    }

    /**
     *
     * @return el tiempo de vida que ha tenido la clave anterior
     */
    public synchronized long reemplazarKey() {
        long vidaAnteriorKey = System.currentTimeMillis() - dateCreationKey;
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        dateCreationKey = System.currentTimeMillis();        
        
        return vidaAnteriorKey;
    }
    
    public static synchronized GestorDeJWT getInstance() {
        if (gestorDeJWT == null) {
            gestorDeJWT = new GestorDeJWT();
        }
        return gestorDeJWT;
    }    
    
    public String generarToken(String username, List<String> roles, int duracionMinutos) {

        String token = Jwts
                .builder()
                .claim(ROLSCLAIMS, roles)
                .setSubject(username)
                .setExpiration(getExpirationDate(duracionMinutos))
                .signWith(key)
                .compact();

        return token;
    }

    public Claims getClaims(String token) throws ExpiredJwtException, MalformedJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
                
                
    } 

    private Date getExpirationDate(int duracionMinutos) {
        return new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(duracionMinutos));

    }
}