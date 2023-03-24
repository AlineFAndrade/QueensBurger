package br.com.queensburger.queensBurger.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "orders")
public class Order {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String table;

  @Column
  private Integer waiter;


  @CreatedDate
  @Column(name = "created_at")
  private Date createdAt = new Date();

  @LastModifiedDate
  @Column(name = "updated_at")
  private Date updatedAt = new Date();

  @Column(name = "preparedOrder")
  private Date preparedOrder = new Date();

  @Column(name = "servedOrder")
  private Date servedOrder = new Date();

  @OneToMany(mappedBy = "order")
  private List<OrderProduct> products;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public Integer getWaiter() {
    return waiter;
  }

  public void setWaiter(Integer waiter) {
    this.waiter = waiter;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date getPreparedOrder() {
    return preparedOrder;
  }

  public void setPreparedOrder(Date preparedOrder) {
    this.preparedOrder = preparedOrder;
  }

  public Date getServedOrder() {
    return preparedOrder;
  }

  public void setServedOrder(Date servedOrder) {
    this.servedOrder = servedOrder;
  }
}
