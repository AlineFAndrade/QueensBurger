package br.com.queensburger.queensBurger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queensburger.queensBurger.model.Product;
import br.com.queensburger.queensBurger.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
  
  @Autowired
	private ProductRepository repository;
	
	@Override
	public Product include(Product product) {
		
		 Optional<Product> prodWithSameId = repository.findByName(product.getName());
		
		 if(prodWithSameId.isEmpty()) {
			 return repository.save(product); 
		 } else {
			 return null;
		 }
	}

	@Override
	public Product update(Integer id, Product product) throws duplicatedProductException {
		
		Optional<Product> resultFromDB = repository.findById(id);
		
		if(resultFromDB.isPresent()) {
			Product productFromDB = resultFromDB.get();
			
			int idFounded = product.getId();
			Optional<Product> productWithSameId = repository.findById(idFounded);
			
			if(productWithSameId != null && productWithSameId.get().getId() != productFromDB.getId()) {
				throw new duplicatedProductException();
			}
			
			productFromDB.setId(product.getId());
      productFromDB.setName(product.getName());
      productFromDB.setFlavor(product.getFlavor());
      productFromDB.setComplement(product.getComplement());
      productFromDB.setPrice(product.getPrice());
      productFromDB.setImage(product.getImage());
      productFromDB.setType(product.getType());
      productFromDB.setSubtype(product.getSubtype());
      productFromDB.setCreatedAt(product.getCreatedAt());
      productFromDB.setUpdatedAt(product.getUpdatedAt());
			
			return repository.save(productFromDB);
		} else {
			return null;
		}
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> resultFromDB = repository.findById(id);
		
		if(resultFromDB.isPresent()) {
			return resultFromDB.get();
		} else {
			return null;
		}
	}
}
