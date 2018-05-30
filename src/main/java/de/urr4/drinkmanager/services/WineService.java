package de.urr4.drinkmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.urr4.drinkmanager.exceptions.ExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.urr4.drinkmanager.exceptions.DrinkManagerException;
import de.urr4.drinkmanager.repositories.WineRepository;
import de.urr4.wine.entities.Wine;


@Service
public class WineService {

    Logger logger = LoggerFactory.getLogger(WineService.class);

	@Autowired
	private WineRepository wineRepository;


	public List<Wine> getAllWines() {
	    logger.debug("Getting all Wines");
		List<Wine> wines = new ArrayList<>();
		wineRepository.findAll().forEach(
				wine -> wines.add(wine));
		return wines;
	}


	public Wine getWineById(Long id) {
        logger.debug("Getting Wine "+id);
		Optional<Wine> wine = wineRepository.findById(id);
		if (wine.isPresent()) {
			return wine.get();
		} else {
            logger.error("Wine with id "+id+" doe not exists");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
	}


	public void addWine(Wine wine) {
        logger.debug("Adding Wine "+wine.getName());
        if(wine.getId() != null){
            logger.error("Wine already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        wineRepository.save(wine);
	}


	public Wine updateWine(Wine wine) {
	    logger.debug("Updating Wine "+wine.getId());
		if(wine.getId()==null){
            logger.error("Wine does not exist");
		    throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        return wineRepository.save(wine);
	}

}
