package at.brigot.kainblog.data;

import at.brigot.kainblog.pojos.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

}
