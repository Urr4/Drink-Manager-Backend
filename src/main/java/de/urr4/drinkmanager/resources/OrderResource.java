package de.urr4.drinkmanager.resources;

import java.util.List;

import de.urr4.wine.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.urr4.drinkmanager.services.OrderService;


@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping(path = "/drinkmanager/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	private Logger logger = LoggerFactory.getLogger(OrderResource.class);


	@RequestMapping(method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		logger.info("Loading all Orders");
		return orderService.getAllOrders();
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable("id") Long id) {
		logger.info("Loading Order with id " + id);
		return orderService.getOrderById(id);
	}


	@RequestMapping(method = RequestMethod.PUT)
	public Order updateOrder(@RequestBody Order order) {
		logger.info("Updating Order " + order);
		return orderService.updateOrder(order);
	}


	@RequestMapping(method = RequestMethod.POST)
	public void saveOrder(@RequestBody Order order) {
		logger.info("Creating Order " + order);
		orderService.addOrder(order);
	}


	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deactivateOrder(@PathVariable("id") Long id) {
		logger.info("Deactivating Order with id " + id);
		orderService.deactivateOrder(id);
	}
}
