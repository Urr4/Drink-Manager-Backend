package de.urr4.drinkmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.urr4.drinkmanager.exceptions.DrinkManagerException;
import de.urr4.drinkmanager.exceptions.ExceptionType;
import de.urr4.drinkmanager.repositories.WineRepository;
import de.urr4.wine.entities.User;
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
		logger.debug("Found " + wines.size() + " Wines");
		return wines;
	}


	public Wine getWineById(Long id) {
		logger.debug("Getting Wine " + id);
		Optional<Wine> wine = wineRepository.findById(id);
		if (wine.isPresent()) {
			logger.debug("Found Wine " + wine.get().getName());
			return wine.get();
		} else {
			logger.error("Wine with id " + id + " doe not exists");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
	}


	public void addWine(Wine wine) {
		logger.debug("Adding Wine " + wine.getName());
		if (wine.getId() != null) {
			logger.error("Wine already exists");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
		wineRepository.save(wine);
		logger.debug("Added Wine " + wine.getId());
	}


	public Wine updateWine(Wine wine) {
		logger.debug("Updating Wine " + wine.getId());
		if (wine.getId() == null) {
			logger.error("Wine does not exist");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
		Wine updatedWine = wineRepository.save(wine);
		logger.debug("Updated Wine " + updatedWine.getId());
		return updatedWine;
	}


	public void deactivateWine(Long id) {
		logger.debug("Deactivating Wine " + id);
		Wine wine = getWineById(id);
		wine.setActive(false);
		updateWine(wine);
		logger.debug("Deactivated Wine " + wine.getId());
	}


	public List<User> getUsersLikingWine(Long id) {
		logger.debug("Getting Users by Wine " + id);
		List<User> users = new ArrayList<>();
		wineRepository.getUsersLikingWine(id).forEach(user -> users.add(user));
		logger.debug("Found " + users.size() + " Users");
		return users;
	}

}
