package de.urr4.drinkmanager.repositories;

import de.urr4.wine.entities.Wine;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends Neo4jRepository<Wine, Long> {

}
