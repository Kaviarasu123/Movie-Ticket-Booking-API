package io.aiven.spring.mysql.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<Credential,Integer> {
   Optional<Credential> findByEmail(String email);
   Optional<Credential> findById(Integer id);


}
