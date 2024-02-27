package io.aiven.spring.mysql.demo.Repository;

import io.aiven.spring.mysql.demo.Model.Credential;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<Credential,Integer> {
   Optional<Credential> findByEmail(String email);
   Optional<Credential> findById(Integer id);


}
