package app.data;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Order Detail entity bean.
 */
@Entity
@Table(name="o_order_detail")
public class OrderDetail {

    @Id
    Integer id;

    Integer orderQty;

    Integer shipQty;

    Timestamp cretime;

    @Version
    Timestamp updtime;

    @ManyToOne(cascade=CascadeType.ALL)
    Order order;

    @ManyToOne
    Product product;

    public OrderDetail() {
    }
    
    public OrderDetail(Product product, Integer orderQty){
    	this.product = product;
    	this.orderQty = orderQty;
    }

    /**
     * Return id.
     */    
    public Integer getId() {
  	    return id;
    }

    /**
     * Set id.
     */    
    public void setId(Integer id) {
  	    this.id = id;
    }

    /**
     * Return order qty.
     */    
    public Integer getOrderQty() {
  	    return orderQty;
    }

    /**
     * Set order qty.
     */    
    public void setOrderQty(Integer orderQty) {
  	    this.orderQty = orderQty;
    }

    /**
     * Return ship qty.
     */    
    public Integer getShipQty() {
  	    return shipQty;
    }

    /**
     * Set ship qty.
     */    
    public void setShipQty(Integer shipQty) {
  	    this.shipQty = shipQty;
    }

    /**
     * Return cretime.
     */    
    public Timestamp getCretime() {
  	    return cretime;
    }

    /**
     * Set cretime.
     */    
    public void setCretime(Timestamp cretime) {
  	    this.cretime = cretime;
    }

    /**
     * Return updtime.
     */    
    public Timestamp getUpdtime() {
  	    return updtime;
    }

    /**
     * Set updtime.
     */    
    public void setUpdtime(Timestamp updtime) {
  	    this.updtime = updtime;
    }

    /**
     * Return order.
     */    
    public Order getOrder() {
  	    return order;
    }

    /**
     * Set order.
     */    
    public void setOrder(Order order) {
  	    this.order = order;
    }

    /**
     * Return product.
     */    
    public Product getProduct() {
  	    return product;
    }

    /**
     * Set product.
     */    
    public void setProduct(Product product) {
  	    this.product = product;
    }


}
