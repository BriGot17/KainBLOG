package at.brigot.kainblog.service;

import at.brigot.kainblog.data.UserRepository;
import at.brigot.kainblog.pojos.User;
import at.brigot.kainblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<String> roles = userRepo.findByUsername(username).getRoles();
        List<GrantedAuthority> grantedAuthorities = roles.stream().map(r -> {
            return new SimpleGrantedAuthority(r);
        }).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }
    public UserVo getUserVoByUsername(String username){
        User user = userRepo.findByUsername(username);
        if(user != null){
            Set<String> roles = new HashSet<>();
            roles.addAll(user.getRoles().stream().map(r -> r).collect(Collectors.toSet()));
            UserVo vo = new UserVo(user.getUsername(), user.getPassword(), roles);
            return vo;
        }
        System.out.println("uservo null");
        return null;

    }
    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
