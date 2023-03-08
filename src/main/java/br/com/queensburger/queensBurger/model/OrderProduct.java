package br.com.queensburger.queensBurger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderProduct")
public class OrderProduct {
  
  @ManyToOne
  @JoinTable(name = "orderProduct",
    joinColumns = {@JoinColumn(name = "orderId")}, 
    inverseJoinColumns = {@JoinColumn(name = "id")} 
  )
  private Order orderId;
  
  @ManyToOne
  @JoinTable(name = "orderProduct",
    joinColumns = {@JoinColumn(name = "productId")}, 
    inverseJoinColumns = {@JoinColumn(name = "id")} 
  )
  private Product product;

  @Column
  private Integer qtd;

}

