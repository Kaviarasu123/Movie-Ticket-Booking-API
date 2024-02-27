package io.aiven.spring.mysql.demo;
import org.springframework.data.repository.CrudRepository;
import io.aiven.spring.mysql.demo.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {


    Optional<User> findByEmail(String email);
}
