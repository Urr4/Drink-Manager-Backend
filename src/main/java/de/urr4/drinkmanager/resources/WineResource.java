package de.urr4.drinkmanager.resources;

import java.util.List;

import de.urr4.drinkmanager.services.WineService;
import de.urr4.wine.entities.Wine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "/wines")
public class WineResource {

    @Autowired
    private WineService wineService;

    private Logger logger = LoggerFactory.getLogger(WineResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<Wine> getAllWines(){
        logger.info("Loading all Wines");
        return wineService.getAllWines();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Wine getAllWines(@PathVariable("id") Long id){
        logger.info("Loading Wine with id "+id);
        return wineService.getWineById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Wine updateWine(@RequestBody Wine wine){
        logger.info("Updating Wine "+wine);
        return wineService.updateWine(wine);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveWine(@RequestBody Wine wine){
        logger.info("Creating Wine "+wine);
        wineService.updateWine(wine);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public void deactivateWine(@PathVariable("id") Long id){
        logger.info("Deactivating Wine with id "+id);
        wineService.deactivateWine(id);
    }
}
