package io.aiven.spring.mysql.demo.Repository;

import io.aiven.spring.mysql.demo.Model.Movies;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MoviesRepository extends CrudRepository<Movies,Integer> {
    Optional<Movies> findById(Integer id);
}
