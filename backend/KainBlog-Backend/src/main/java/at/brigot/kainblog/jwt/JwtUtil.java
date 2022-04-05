package at.brigot.kainblog.jwt;

import at.brigot.kainblog.jwt.exception.JwtTokenMalformedException;
import at.brigot.kainblog.jwt.exception.JwtTokenMissingException;
import at.brigot.kainblog.vo.UserVo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;
    public UserVo getUser(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            UserVo user = new UserVo();
            user.setUsername(body.getSubject());

            Set<String> roles = Arrays.asList(body.get("roles").toString().split(",")).stream().map(r -> new String(r))
                .collect(Collectors.toSet());
            Set<String> r2 = new HashSet<>();
            roles.forEach(r -> {
                r2.add(r.replace("[", "")
                        .replace("]", "").trim());
                });
            user.setRoles(r2);
            //System.out.println("in getUser " + r2);
            //System.out.println("in getUser 2 " + body);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }
    public String generateToken(UserVo u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("roles", u.getRoles());

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    
    public void validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}
