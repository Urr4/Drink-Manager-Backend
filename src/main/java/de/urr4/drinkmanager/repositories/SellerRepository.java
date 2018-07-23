package de.urr4.drinkmanager.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.urr4.wine.entities.Seller;


@Repository
public interface SellerRepository extends Neo4jRepository<Seller, Long> {

	List<Seller> getSellersByName(String name);


	@Query("MATCH (s:Seller) WHERE id(s)={id} CALL apoc.spatial.geocodeOnce(s.address) YIELD location SET s.lat=location.latitude SET s.lng=location.longitude")
	void setSellerLatLng(@Param("id") Long id);

}
