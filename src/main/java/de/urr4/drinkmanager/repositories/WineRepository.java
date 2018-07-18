package de.urr4.drinkmanager.repositories;

import de.urr4.wine.entities.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.urr4.wine.entities.Wine;


@Repository
public interface WineRepository extends Neo4jRepository<Wine, Long> {

    @Query(value = "MATCH (u:User)-[:LIKES]->(w:Wine) WHERE id(w)={wineId}")
    Iterable<User> getUsersLikingWine(@Param("wineId") Long wineId);

}
