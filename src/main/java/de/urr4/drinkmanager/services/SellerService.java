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
import de.urr4.drinkmanager.repositories.SellerRepository;
import de.urr4.wine.entities.Seller;


@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	private Logger logger = LoggerFactory.getLogger(SellerService.class);


	public List<Seller> getAllSellers() {
		logger.debug("Getting all Sellers");
		List<Seller> sellers = new ArrayList<>();
		sellerRepository.findAll().forEach(
				seller -> sellers.add(seller));
		logger.debug("Found " + sellers.size() + " Sellers");
		return sellers;
	}


	public Seller getSellerById(Long id) {
		logger.debug("Getting Seller " + id);
		Optional<Seller> sellerOptional = sellerRepository.findById(id);
		if (sellerOptional.isPresent()) {
			logger.debug("Found Seller " + sellerOptional.get().getName());
			return sellerOptional.get();
		} else {
			logger.error("Seller " + id + " doe not exist");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
	}


	public List<Seller> getSellersByName(String name) {
		logger.debug("Getting Sellers named " + name);
		List<Seller> sellers = sellerRepository.getSellersByName(name);
		logger.debug("Found " + sellers.size() + " Sellers");
		return sellers;
	}


	public void addSeller(Seller seller) {
		logger.debug("Adding Seller " + seller.getName());
		if (seller.getId() != null) {
			logger.error("Seller " + seller.getId() + " already exists");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
		sellerRepository.save(seller);
		addSellerLatLng(seller);
		logger.debug("Added Seller " + seller.getId());
	}


	public Seller updateSeller(Seller seller) {
		logger.debug("Updating Seller " + seller.getName());
		if (seller.getId() == null) {
			logger.error("Seller " + seller.getName() + " does not exists");
			throw new DrinkManagerException(ExceptionType.TECHNICAL);
		}
		Seller updatedSeller = sellerRepository.save(seller);
		addSellerLatLng(updatedSeller);
		logger.debug("Updated Seller " + seller.getId());
		return updatedSeller;
	}


	public void deactivateSeller(Long id) {
		logger.debug("Deactivating Seller " + id);
		Seller seller = getSellerById(id);
		seller.setActive(false);
		updateSeller(seller);
		logger.debug("Deactivated Seller " + seller.getId());
	}


	private void addSellerLatLng(Seller seller) {
		sellerRepository.setSellerLatLng(seller.getId());
	}

}
