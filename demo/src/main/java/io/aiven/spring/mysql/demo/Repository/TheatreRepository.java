package io.aiven.spring.mysql.demo.Repository;
import io.aiven.spring.mysql.demo.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    Optional<Theatre>findById(Integer id);
}
