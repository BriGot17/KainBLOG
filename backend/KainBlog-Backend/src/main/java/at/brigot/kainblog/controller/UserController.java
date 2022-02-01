package at.brigot.kainblog.controller;

import at.brigot.kainblog.data.UserRepository;
import at.brigot.kainblog.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @PostMapping("/auth/signin")
    public ResponseEntity<User> signIn(@RequestBody User user){
        userRepo.save(user);

        return ResponseEntity.of(Optional.of(userRepo.findByIdentity(user.getIdentity())));
    }
    @PostMapping("/auth/login")
    public void logIn(){

    }
    @PostMapping("/{identity}")
    public String getUserData(@PathVariable("identity") String identity){
        System.out.println(identity);
        return "";
    }


}
