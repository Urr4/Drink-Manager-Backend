package de.urr4.drinkmanager.repositories;

import java.util.List;

import de.urr4.wine.entities.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User getUserById(Long id);

    List<User> getUsersByName(String name);

}
