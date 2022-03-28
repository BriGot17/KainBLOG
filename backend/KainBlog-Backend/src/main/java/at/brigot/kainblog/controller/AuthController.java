package at.brigot.kainblog.controller;


import at.brigot.kainblog.data.ArticleRepository;
import at.brigot.kainblog.data.RSSParser;
import at.brigot.kainblog.jwt.JwtRequest;
import at.brigot.kainblog.jwt.JwtResponse;
import at.brigot.kainblog.jwt.JwtUtil;
import at.brigot.kainblog.jwt.exception.DisabledUserException;
import at.brigot.kainblog.jwt.exception.InvalidUserCredentialsException;
import at.brigot.kainblog.pojos.User;
import at.brigot.kainblog.service.UserAuthService;
import at.brigot.kainblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT})
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> generateJwtToken(@RequestBody JwtRequest jwtRequest) {
        System.out.println("Signin request");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Signin failed1");
            return ResponseEntity.status(401).build();
            //throw new InvalidUserCredentialsException("Invalid Credentials");

        } catch (DisabledException e) {
            System.out.println("Signin failed2");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            //throw new DisabledUserException("User Inactive");
        }
        UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getUsername());
        String username = userDetails.getUsername();
        String userpwd = userDetails.getPassword();
        Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        UserVo user = new UserVo();
        user.setUsername(username);
        user.setPassword(userpwd);
        user.setRoles(roles);
        String token = jwtUtil.generateToken(user);
        System.out.println("Signin success");
        return new ResponseEntity(new JwtResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")

    public ResponseEntity<String> signup(@RequestBody User user) {
        System.out.println("Signup request");
        UserVo u = userAuthService.getUserVoByUsername(user.getUsername());

        if (u == null) {
            userAuthService.saveUser(user);
            return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
        }
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER', 'USER')")
    @GetMapping("/users/{username}")
    public User userByUsername(@PathVariable("username") String username){
        System.out.println("OneUserRequest");
        return userAuthService.getUserByUsername(username);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PUBLISHER', 'USER')")
    @GetMapping("/users/current")
    public User getCurrent(@RequestBody String token){
        System.out.println("Current user request");
        return userAuthService.getUserByUsername(jwtUtil.getUser(token).getUsername());
    }
}
