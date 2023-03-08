package br.com.queensburger.queensBurger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queensburger.queensBurger.model.Order;
import br.com.queensburger.queensBurger.repository.OrderRepository;

@Service
public class OrdersServiceImpl implements OrderService {
  
  @Autowired
	private OrderRepository repository;
	
	@Override
	public Order include(Order order) {
		
		 Optional<Order> orderWithSameId = repository.findById(order.getId());
		
		 if(orderWithSameId.isEmpty()) {
			 return repository.save(order); 
		 } else {
			 return null;
		 }
	}

	@Override
	public Order update(Integer id, Order order) throws duplicatedProductException {
		
		Optional<Order> resultFromDB = repository.findById(id);
		
		if(resultFromDB.isPresent()) {
			Order orderFromDB = resultFromDB.get();
			
			int idFounded = order.getId();
			Optional<Order> orderWithSameId = repository.findById(idFounded);
			
			if(orderWithSameId != null && orderWithSameId.get().getId() != orderFromDB.getId()) {
				throw new duplicatedProductException();
			}
			
			orderFromDB.setId(order.getId());
      orderFromDB.setWaiter(order.getWaiter());
      orderFromDB.setTable(order.getTable());
      orderFromDB.setCreatedAt(order.getCreatedAt());
      orderFromDB.setUpdatedAt(order.getUpdatedAt());
			orderFromDB.setPreparedOrder(order.getPreparedOrder());
			orderFromDB.setServedOrder(order.getServedOrder());
			
			return repository.save(orderFromDB);
		} else {
			return null;
		}
	}

	@Override
	public Order findById(Integer id) {
		Optional<Order> resultFromDB = repository.findById(id);
		
		if(resultFromDB.isPresent()) {
			return resultFromDB.get();
		} else {
			return null;
		}
	}
}

