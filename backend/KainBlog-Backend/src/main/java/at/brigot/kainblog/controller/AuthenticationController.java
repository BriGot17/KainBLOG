package at.brigot.kainblog.controller;

import at.brigot.kainblog.config.TokenProvider;
import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.UserRepository;
import at.brigot.kainblog.pojos.*;
import at.brigot.kainblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @RequestMapping(value = "/token/generate-token", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        User user = userService.findOne(loginUser.getUsername());

        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(r -> {
            roles.add(r.getName());
        });
        JwtResponse res = new JwtResponse(token, loginUser.getUsername(), roles);
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping("/token/check")
    public String expirationCheck(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        String ok = "ok";
        String notok = "netok";
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails details = userDetailsService.loadUserByUsername(username);
        System.out.println("here");
        if(jwtTokenUtil.validateToken(token, details)){
            return ok;
        }else{
            return notok;
        }
    }
    @Autowired
    private ArticleRepository articleRepo;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PUBLISHER')")
    @RequestMapping("/users/current")
    public User getCurrent(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findOne(username);
        return user;
    }
    
}
