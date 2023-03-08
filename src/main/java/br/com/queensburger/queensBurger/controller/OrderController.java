package br.com.queensburger.queensBurger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.queensburger.queensBurger.model.Order;
import br.com.queensburger.queensBurger.service.OrderService;
import br.com.queensburger.queensBurger.service.duplicatedProductException;

@RestController
public class OrderController {
  
  @Autowired
  private OrderService service;

  @RequestMapping(value = "/order", method = RequestMethod.POST)
  public ResponseEntity<Object> Post(@RequestBody Order order) {
    Order includedOrder = service.include(order);

    if(includedOrder != null) {
      return new ResponseEntity<Object>(includedOrder, HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Pedido já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/order/{idUrl}", method = RequestMethod.PUT)
  public ResponseEntity<Object> Put(@PathVariable(value = "idUrl") int id, 
                                    @RequestBody Order newOrder) {
    try {
      Order updatedOrder = service.update(id, newOrder);
      if (updatedOrder != null) {
        return new ResponseEntity<Object>(updatedOrder, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>("Pedido não encontrado", HttpStatus.BAD_REQUEST);
      }
    } catch ( duplicatedProductException e) {
      e.printStackTrace();
      return new ResponseEntity<Object>("Pedido já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/order/{idUrl}", method = RequestMethod.GET)
	public ResponseEntity<Order> Get(@PathVariable(value = "idUrl") int id){
		Order receivedOrder = service.findById(id);
		if(receivedOrder != null) {
			return new ResponseEntity<Order>(receivedOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}


