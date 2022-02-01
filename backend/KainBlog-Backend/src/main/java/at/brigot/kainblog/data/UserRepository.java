package at.brigot.kainblog.data;

import at.brigot.kainblog.pojos.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByIdentity(String identity);
}
