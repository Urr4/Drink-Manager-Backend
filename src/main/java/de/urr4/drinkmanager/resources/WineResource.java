package de.urr4.drinkmanager.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.urr4.drinkmanager.services.WineService;
import de.urr4.wine.entities.User;
import de.urr4.wine.entities.Wine;


@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping(path = "/drinkmanager/wines")
public class WineResource {

	@Autowired
	private WineService wineService;

	private Logger logger = LoggerFactory.getLogger(WineResource.class);


	@RequestMapping(method = RequestMethod.GET)
	public List<Wine> getAllWines() {
		logger.info("Loading all Wines");
		return wineService.getAllWines();
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Wine getWineById(@PathVariable("id") Long id) {
		logger.info("Loading Wine with id " + id);
		return wineService.getWineById(id);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public Wine updateWine(@RequestBody Wine wine) {
		logger.info("Updating Wine " + wine);
		return wineService.updateWine(wine);
	}


	@RequestMapping(method = RequestMethod.POST)
	public void saveWine(@RequestBody Wine wine) {
		logger.info("Creating Wine " + wine);
		wineService.updateWine(wine);
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deactivateWine(@PathVariable("id") Long id) {
		logger.info("Deactivating Wine with id " + id);
		wineService.deactivateWine(id);
	}


	@RequestMapping(path = "/{id}/users", method = RequestMethod.GET)
	public List<User> getUserLikingWine(@PathVariable("id") Long id) {
		logger.info("Getting Users liking Wine " + id);
		return wineService.getUsersLikingWine(id);
	}
}
