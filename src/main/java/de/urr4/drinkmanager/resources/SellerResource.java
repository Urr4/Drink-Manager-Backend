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

import de.urr4.drinkmanager.services.SellerService;
import de.urr4.wine.entities.Seller;


@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping(path = "/drinkmanager/sellers")
public class SellerResource {

	@Autowired
	private SellerService sellerService;

	private Logger logger = LoggerFactory.getLogger(SellerResource.class);


	@RequestMapping(method = RequestMethod.GET)
	public List<Seller> getAllSellers() {
		logger.info("Loading all Seller");
		return sellerService.getAllSellers();
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Seller getSellerById(@PathVariable("id") Long id) {
		logger.info("Loading Seller with id " + id);
		return sellerService.getSellerById(id);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public Seller updateSeller(@RequestBody Seller seller) {
		logger.info("Updating Seller " + seller);
		return sellerService.updateSeller(seller);
	}


	@RequestMapping(method = RequestMethod.POST)
	public void saveSeller(@RequestBody Seller seller) {
		logger.info("Creating Seller " + seller);
		sellerService.addSeller(seller);
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deactivateSeller(@PathVariable("id") Long id) {
		logger.info("Deactivating Seller with id " + id);
		sellerService.deactivateSeller(id);
	}
}
