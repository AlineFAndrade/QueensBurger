package br.com.queensburger.queensBurger.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orderproducts")
public class OrderProduct {

    @EmbeddedId
    private OrderProductsKey id;

    @ManyToOne
    @MapsId("orderId") 
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne
    @MapsId("productId") 
    @JoinColumn(name = "productid") 
    private Product product;

    @Column
    private Integer qtd;

    public OrderProductsKey getId() {
        return id;
    }

    public void setId(OrderProductsKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}

