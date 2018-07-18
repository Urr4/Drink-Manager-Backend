package de.urr4.drinkmanager.resources;

import java.util.List;

import de.urr4.drinkmanager.services.UserService;
import de.urr4.wine.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.urr4.drinkmanager.services.WineService;
import de.urr4.wine.entities.Wine;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/drinkmanager/users")
public class UserResource {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserResource.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        logger.info("Loading all Users");
        return userService.getAllUsers();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id){
        logger.info("Loading User with id "+id);
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user){
        logger.info("Updating User "+user);
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveUser(@RequestBody User user){
        logger.info("Creating User "+user);
        userService.updateUser(user);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public void deactivateWine(@PathVariable("id") Long id){
        logger.info("Deactivating User with id "+id);
        userService.deactivateUser(id);
    }
}
