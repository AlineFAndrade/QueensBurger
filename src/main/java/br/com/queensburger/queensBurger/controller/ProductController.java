package br.com.queensburger.queensBurger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.queensburger.queensBurger.model.Product;
import br.com.queensburger.queensBurger.service.ProductService;
import br.com.queensburger.queensBurger.service.duplicatedProductException;

@RestController
public class ProductController {
  
  @Autowired
  private ProductService service;

  @RequestMapping(value = "/product", method = RequestMethod.POST)
  public ResponseEntity<Object> Post(@RequestBody Product product) {
    Product includedProduct = service.include(product);

    if(includedProduct != null) {
      return new ResponseEntity<Object>(includedProduct, HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Produto já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/product/{idUrl}", method = RequestMethod.PUT)
  public ResponseEntity<Object> Put(@PathVariable(value = "idUrl") int id, 
                                @RequestBody Product newProduct) {
    try {
      Product updatedProduct = service.update(id, newProduct);
      if (updatedProduct != null) {
        return new ResponseEntity<Object>(updatedProduct, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>("Produto não encontrado", HttpStatus.BAD_REQUEST);
      }
    } catch ( duplicatedProductException e) {
      e.printStackTrace();
      return new ResponseEntity<Object>("Produto já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/product/{idUrl}", method = RequestMethod.GET)
	public ResponseEntity<Product> Get(@PathVariable(value = "idUrl") int id){
		Product receivedProduct = service.findById(id);
		if(receivedProduct != null) {
			return new ResponseEntity<Product>(receivedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

