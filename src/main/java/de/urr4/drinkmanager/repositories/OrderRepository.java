package de.urr4.drinkmanager.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import de.urr4.wine.entities.Order;

@Repository
public interface OrderRepository extends Neo4jRepository<Order, Long> {

}
