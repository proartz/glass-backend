package pl.oncode.glass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Attachment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Path;
    @JsonIgnore
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;

    public Attachment() {
    }

    public Attachment(String Path) {
        this.Path = Path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String String) {
        this.Path = String;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
