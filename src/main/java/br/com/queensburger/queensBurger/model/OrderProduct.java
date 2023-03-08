package br.com.queensburger.queensBurger.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orderproducts")
public class OrderProduct {

    @EmbeddedId
    private OrderProductsKey id; // <-- Essa é a chave composta.

    @ManyToOne
    @MapsId("orderId") // <-- Esse aqui mapea o atributo da chave composta
    @JoinColumn(name = "orderid") // <-- Esse aqui mapea o nome da coluna no banco
    private Order order;

    @ManyToOne
    @MapsId("productId") // <-- Esse aqui mapea o atributo da chave composta
    @JoinColumn(name = "productid") // <--  Esse aqui mapea o nome da coluna no banco
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

