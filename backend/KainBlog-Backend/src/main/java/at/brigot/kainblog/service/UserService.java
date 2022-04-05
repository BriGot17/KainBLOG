package at.brigot.kainblog.service;

import at.brigot.kainblog.pojos.User;


import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(String id);
    User findOne(String username);
    User findById(String id);
}
