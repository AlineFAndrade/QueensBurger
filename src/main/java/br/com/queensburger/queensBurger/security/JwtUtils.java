package br.com.queensburger.queensBurger.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import br.com.queensburger.queensBurger.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

	@Value("${jwtCookieName}")
	private String jwtCookie;
	
	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
	    if (cookie != null) {
	      return cookie.getValue();
	    } else {
	      return null;
	    }
	}
	
	public String generateTokenFromEmail(String email) {

	    return Jwts.builder()
	        .setSubject(email)
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	}
	
	public ResponseCookie generateJwtCookie(User userPrincipal) {
	    String jwt = generateTokenFromEmail(userPrincipal.getEmail());
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
	    return cookie;
	}

	public ResponseCookie getCleanJwtCookie() {
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, "").path("/api").build();
	    return cookie;
	}
	
	public String getEmailFromJwtToken(String token) {
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
	    return false;
	}
}
