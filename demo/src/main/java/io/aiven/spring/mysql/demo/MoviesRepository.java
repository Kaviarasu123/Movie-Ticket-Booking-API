package io.aiven.spring.mysql.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MoviesRepository extends CrudRepository<Movies,Integer> {
    Optional<Movies> findById(Integer id);
}
