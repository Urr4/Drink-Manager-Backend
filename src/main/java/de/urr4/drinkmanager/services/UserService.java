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
        return users;
    }

    public User getUserById(Long id){
        logger.debug("Getting User by id "+id);
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else{
            logger.error("User "+id+" does not exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
    }

    public List<User> getUsersByName(String name){
        logger.debug("Getting Users named "+name);
        return userRepository.getUsersByName(name);
    }

    public void addUser(User user){
        logger.debug("Adding User "+user.getName());
        if(user.getId()!=null){
            logger.error("User "+user.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        userRepository.save(user);
    }

    public User updateUser(User user){
        logger.debug("Updating user "+user.getId());
        if(user.getId()==null){
            logger.error("User "+user.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        return userRepository.save(user);
    }

}
