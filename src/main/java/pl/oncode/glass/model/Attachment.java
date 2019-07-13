package pl.oncode.glass.model;

import javax.persistence.*;

@Entity
public class Attachment {

    @Id @GeneratedValue
    private Integer id;
    private String String;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Integer orderId;

    public Attachment() {
    }

    public Attachment(String String, Integer orderId) {
        this.String = String;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString() {
        return String;
    }

    public void setString(String String) {
        this.String = String;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
