package io.aiven.spring.mysql.demo.Repository;
import org.springframework.data.repository.CrudRepository;
import io.aiven.spring.mysql.demo.Model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {


    Optional<User> findByEmail(String email);
}
