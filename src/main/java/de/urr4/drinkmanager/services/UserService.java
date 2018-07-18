package de.urr4.drinkmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.urr4.drinkmanager.exceptions.DrinkManagerException;
import de.urr4.drinkmanager.exceptions.ExceptionType;
import de.urr4.drinkmanager.repositories.UserRepository;
import de.urr4.wine.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers(){
        logger.debug("Getting all Users");
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(
                user -> users.add(user)
        );
        logger.debug("Found "+users.size()+" Users");
        return users;
    }

    public User getUserById(Long id){
        logger.debug("Getting User by id "+id);
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            logger.debug("Found "+user.get().getName());
            return user.get();
        }else{
            logger.error("User "+id+" does not exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
    }

    public List<User> getUsersByName(String name){
        logger.debug("Getting Users named "+name);
        List<User> users = userRepository.getUsersByName(name);
        logger.debug("Found "+users.size()+" Users");
        return users;
    }

    public void addUser(User user){
        logger.debug("Adding User "+user.getName());
        if(user.getId()!=null){
            logger.error("User "+user.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        userRepository.save(user);
        logger.debug("Added User "+user.getId());
    }

    public User updateUser(User user){
        logger.debug("Updating user "+user.getId());
        if(user.getId()==null){
            logger.error("User "+user.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        User updatedUser = userRepository.save(user);
        logger.debug("Updated User "+updatedUser.getId());
        return updatedUser;
    }

    public void deactivateUser(Long id){
        logger.debug("Deactivating User "+id);
        User user = getUserById(id);
        user.setActive(false);
        updateUser(user);
        logger.debug("Deactivated User "+user.getId());
    }

}
