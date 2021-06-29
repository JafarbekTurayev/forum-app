package uz.pdp.forum.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.forum.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtProvider {

    String secretKey = "extrimalSecretkey";
    long expireTime = 1000 * 60 * 60 * 24;

    public String generateToken(String username, Role role){
        String json = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new java.sql.Date(System.currentTimeMillis() + expireTime))
                .claim("roles",role)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return json;
    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception r) {
            r.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromToken(String token) {

        String username = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }


}
