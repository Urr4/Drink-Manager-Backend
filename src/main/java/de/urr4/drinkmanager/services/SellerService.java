package de.urr4.drinkmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.urr4.drinkmanager.exceptions.DrinkManagerException;
import de.urr4.drinkmanager.exceptions.ExceptionType;
import de.urr4.drinkmanager.repositories.SellerRepository;
import de.urr4.wine.entities.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    private Logger logger = LoggerFactory.getLogger(SellerService.class);

    public List<Seller> getAllSellers(){
        logger.debug("Getting all Sellers");
        List<Seller> sellers = new ArrayList<>();
        sellerRepository.findAll().forEach(
                seller -> sellers.add(seller)
        );
        return sellers;
    }

    public Seller getSellerById(Long id){
        logger.debug("Getting Seller "+id);
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isPresent()){
            return seller.get();
        }else{
            logger.error("Seller "+id+" doe not exist");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
    }

    public List<Seller> getSellersByName(String name){
        logger.debug("Getting Sellers named "+name);
        return sellerRepository.getSellersByName(name);
    }

    public void addSeller(Seller seller){
        logger.debug("Adding Seller "+seller.getName());
        if(seller.getId()!=null){
            logger.error("Seller "+seller.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        sellerRepository.save(seller);
    }

    public Seller updateSeller(Seller seller){
        logger.debug("Updating Seller "+seller.getName());
        if(seller.getId()==null){
            logger.error("Seller "+seller.getName()+" does not exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        return sellerRepository.save(seller);
    }

}
