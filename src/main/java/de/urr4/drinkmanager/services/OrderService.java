package de.urr4.drinkmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.urr4.drinkmanager.exceptions.DrinkManagerException;
import de.urr4.drinkmanager.exceptions.ExceptionType;
import de.urr4.drinkmanager.repositories.OrderRepository;
import de.urr4.wine.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<Order> getAllOrders(){
        logger.debug("Getting all Orders");
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(
                order -> orders.add(order)
        );
        logger.debug("Found "+orders.size()+" Orders");
        return orders;
    }

    public Order getOrderById(Long id){
        logger.debug("Getting order "+id);
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            logger.debug("Found Order for "+order.get().getFullPrice()+"€");
            return order.get();
        }else{
            logger.error("Order "+id+" does not exist");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
    }

    public void addOrder(Order order){
        logger.debug("Adding Order to "+order.getSeller().getName());
        if(order.getId()!=null){
            logger.error("Order "+order.getId()+" already exists");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        orderRepository.save(order);
        logger.debug("Added Order "+order.getId());
    }

    public Order updateOrder(Order order){
        if(order.getId()==null){
            logger.error("Order for "+order.getSeller().getName()+" does not exist");
            throw new DrinkManagerException(ExceptionType.TECHNICAL);
        }
        Order updatedOrder = orderRepository.save(order);
        logger.debug("Updated Order "+order.getId());
        return updatedOrder;
    }

    public void deactivateOrder(Long id){
        Order order = getOrderById(id);
        order.setActive(false);
        updateOrder(order);
        logger.debug("Deactivated Order "+order.getId());
    }
}
