package de.urr4.drinkmanager.repositories;

import java.util.List;

import de.urr4.wine.entities.Seller;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends Neo4jRepository<Seller, Long> {

    List<Seller> getSellersByName(String name);

}
