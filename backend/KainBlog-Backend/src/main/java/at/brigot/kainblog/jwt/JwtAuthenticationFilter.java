package at.brigot.kainblog.jwt;

import at.brigot.kainblog.jwt.exception.JwtTokenMissingException;
import at.brigot.kainblog.service.UserAuthService;
import at.brigot.kainblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserAuthService userAuthService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("authorization");
        if(header == null){
            throw new NullPointerException("No headers");
        }
        if (!header.startsWith("Bearer")) {
            throw new JwtTokenMissingException("No JWT token found in the request headers");
        }
        String token = header.substring(7);
        // Optional - verification
        jwtUtil.validateToken(token);
        UserVo userVo = jwtUtil.getUser(token);

        UserDetails userDetails = userAuthService.loadUserByUsername(userVo.getUsername());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        filterChain.doFilter(request, response);
        System.out.println("After filterchain" + userDetails.getAuthorities());
    }
}
